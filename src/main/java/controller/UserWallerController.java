package controller;

import dao.UserDao;
import pojo.ResponseVo;
import pojo.Waller;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserWallerController {
    UserService userService=new UserService();

    //根据用户名查询用户余额接口
    public ResponseVo<Waller> querybalance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        System.out.println(username);
        Waller waller=new Waller();
        waller.setUsername(username);
        System.out.println(waller);
        return userService.queryMessage(waller);
    };

    //用户消费100元
    public ResponseVo changewaller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String dd=request.getParameter("number");
        Waller waller=new Waller();
        waller.setUsername(username);
        return userService.changeWaller(waller,dd);
    };

    //用户退款20元
    public ResponseVo userrefund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String id=request.getParameter("id");
        Waller waller=new Waller();
        waller.setUsername(username);
        return userService.userrefund(id,waller);
    };
}
