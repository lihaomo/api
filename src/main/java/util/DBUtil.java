package util;

import java.sql.*;
import java.util.Properties;

/**
 * @author: wudagai
 * @date: 2022/8/22 17:02
 * @description:数据库工具类
 */
public class DBUtil {
    private static final  ThreadLocal<Connection> TL=new ThreadLocal<>();
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    //驱动类只需要加载一次即可
    static{
        try {
            Properties p=new Properties();
            //加载.properties属性文件,将属性文件中的内容加载进内存存在p对象中
            p.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            DRIVER=p.getProperty("DriverClass");
            URL=p.getProperty("Url");
            USERNAME=p.getProperty("UserName");
            PASSWORD=p.getProperty("Password");
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接
    public static Connection getConnection(){
        Connection con = null;
        con = TL.get();
        if (con==null) {
            con = createConnection();
            TL.set(con);
        }
        return con;
    }
    //创建连接
    public static Connection createConnection(){
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //关闭资源
    public static void close() {
        Connection con = TL.get();
        try {
            if (con != null) {
                con.close();
            }
            //至关重要，否则容易造成内存溢出等问题。
            TL.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResulSet(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeStatement(Statement stat){
        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResource(Statement stat,ResultSet rs){
        closeResulSet(rs);
        closeStatement(stat);
    }

    //开启事务
    // 开启一个事务
    public static void beginTransaction() throws Exception {
        Connection connection=DBUtil.getConnection();
        connection.setAutoCommit(false);
    }

    // 提交一个事务
    public static void commitTransaction()  {
        Connection con = TL.get();
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 回滚一个事务
    public static void rollbackTransaction(){
        Connection con = TL.get();
        try {
            con.rollback();
        }catch (Exception e){

        }

    }
}
