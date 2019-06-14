package model;

import entity.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberModel {
    public Member checkLogin(String username, String password){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sql = "select * from member where username = ? and password = ?";
            PreparedStatement prep = DBHelper.getConnection().prepareStatement(sql);
            prep.setString(1,username);
            prep.setString(2,password);
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                String rsUsername = rs.getString("username");
                String rsPassword = rs.getString("password");
                Member member = new Member();
                member.setUsername(rsUsername);
                member.setPassword(rsPassword);
                return member;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
