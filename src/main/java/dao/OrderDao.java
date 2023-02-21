package dao;

import pojo.Order;
import pojo.Waller;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {
    private Connection conn=null;
    private PreparedStatement stat=null;
    private ResultSet rs;


    /**
     * 查询订单
     *
     */
    public Order queryOrder(String id){
        conn = DBUtil.getConnection();
        Order order=new Order();
        String sql="select * from table_order where id=?";
        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,id);
            rs = stat.executeQuery();
            while (rs.next()) {
                order.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeResource(stat,rs);
        }
        return order;
    }

    /**
     * 删除订单
     *
     */
    public int deleteOrder(String id){
        conn = DBUtil.getConnection();
        String sql="delete from table_order  where id=?";
        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,id);
            return stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeStatement(stat);
        }
        return -1;
    }
}
