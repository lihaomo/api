package service;

import dao.OrderDao;
import dao.UserDao;
import pojo.ResponseVo;
import pojo.Waller;
import util.DBUtil;

import java.util.List;

public class UserService {
    private UserDao userDao=new UserDao();
    private OrderDao orderDao=new OrderDao();
    /**
     * 查询用户钱包余额
     * @return
     */
    public ResponseVo<Waller> queryMessage(Waller b){

        ResponseVo<Waller> model=new ResponseVo<>();
        model.setFlag(true);
        try {
            DBUtil.getConnection();
            //把查询结果赋值给结果集
            model.setResult(userDao.queryMessage(b));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return model;
    }


    /**
     * 用户消费100元
     */
    public ResponseVo changeWaller(Waller a,String d){
        ResponseVo model=new ResponseVo();
        try {
            DBUtil.getConnection();
            Double waller=userDao.queryMessage(a).getWaller();
            if (waller<100){
                model.setFlag(false);
                model.setMessage("钱包余额不足");
            }else {
                model.setFlag(true);
                waller -=Double.valueOf(d);
                System.out.println(waller);
                if (userDao.changeUserWaller(a,waller)!=-1){
                    model.setMessage("消费成功");
                }else {
                    model.setMessage("消费失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return model;
    }


    /**
     * 用户退款20元  根据订单编号，还有用户名退款
     */
    public ResponseVo userrefund(String id,Waller a){
        ResponseVo model=new ResponseVo();
        DBUtil.getConnection();
        Double price=orderDao.queryOrder(id).getPrice();
        Double waller=userDao.queryMessage(a).getWaller();
        try {
            //开启事务
            DBUtil.beginTransaction();
            if (userDao.changeUserWaller(a,price+waller)!=-1){
                model.setFlag(true);
                model.setMessage("退款成功");
                //删除订单
                orderDao.deleteOrder(id);
                //关闭事务
                DBUtil.commitTransaction();
            }else {

                model.setFlag(false);
                model.setMessage("退款失败");
                //回滚
                DBUtil.rollbackTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return model;
    }
}
