package controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author: wudagai
 * @date: 2022/10/10 17:17
 * @description:
 */
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取客户端请求路径(/处理器类名/处理器方法名)
        String url=request.getServletPath();
        System.out.println(url);//  /BusinessController/queryByTypeId
        //获取请求的处理器类名
        String className = url.substring(1,url.lastIndexOf("/"));
        String methodName = url.substring(url.lastIndexOf("/")+1);
        //判断请求路径，根据不同的请求，分发给不同的业务处理器

        try {
            //通过Controller类全名获取此类的所有信息
            Class controller=Class.forName("controller."+className);
            //创建Controller类的对象
            Object obj=controller.newInstance();

            //获取Controller类对象中的指定方法
            Method method=controller.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //执行方法,得到处理器方法执行的结果(model)
            Object object=method.invoke(obj,request,response);
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(response.getWriter(),object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
