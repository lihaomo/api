package dao;

import pojo.ResponseVo;
import pojo.Waller;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn=null;
    private PreparedStatement stat=null;
    private ResultSet rs;


    //查询余额
    public Waller queryMessage(Waller waller) {
        List<Object> condition = new ArrayList<>();
        Waller b = new Waller();
        conn = DBUtil.getConnection();
        StringBuilder sql = new StringBuilder("select * from table_waller where 1=1");
        if (waller !=null){
            if (waller.getUsername() !=null){
                sql.append(" and username= ? ");
                condition.add(waller.getUsername());
            }
        }
        try {
            stat = conn.prepareStatement(sql.toString());
            if (condition.size() > 0) {
                for (int i = 0; i < condition.size(); i++) {
                    stat.setObject(i + 1, condition.get(i));
                }
            }
            rs = stat.executeQuery();
            while (rs.next()) {

                b.setWaller(rs.getDouble("waller"));

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            DBUtil.closeResource(stat,rs);
        }

        return b;
    }

    //消费100元
    public int changeUserWaller(Waller waller,Double d){
        conn = DBUtil.getConnection();
        String sql="update table_waller set waller=? where username=?";
        try {
            stat=conn.prepareStatement(sql);
            stat.setDouble(1,d);
            stat.setString(2,waller.getUsername());
            return stat.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }finally {
            DBUtil.closeStatement(stat);
        }
        return -1;
    }


}
