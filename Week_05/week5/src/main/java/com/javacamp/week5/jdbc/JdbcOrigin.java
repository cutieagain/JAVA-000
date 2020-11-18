package com.javacamp.week5.jdbc;

import com.javacamp.week5.entity.Student;

import java.sql.*;

public class JdbcOrigin {
    String driverClassName = "com.mysql.jdbc.Driver";	//启动驱动
    String url = "jdbc:mysql://localhost:3306/test";	//设置连接路径
    String username = "root";	//数据库用户名
    String password = "chenyang007";	//数据库连接密码

    public void addStudent(Student student) {
        Connection con = null;		//连接
        PreparedStatement pstmt = null;	//使用预编译语句
        ResultSet rs = null;	//获取的结果集
        Statement statement = null;
        try {
            Class.forName(driverClassName); //执行驱动
            con = DriverManager.getConnection(url, username, password); //获取连接

            //原生
            String sql = "INSERT INTO test.student(name) VALUES ('"+ student.getName() +"')"; //设置的预编译语句格式
            statement = con.createStatement();
            statement.execute(sql);

            //预编译
            sql = "INSERT INTO test.student(name) VALUES (?)"; //设置的预编译语句格式
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.execute();
            con.commit();//事务方式

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //关闭资源,倒关
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(con != null) con.close();  //必须要关
            } catch (Exception e) {
            }
        }
    }

    public void listInfo() throws SQLException {
        Connection con = null;		//连接
        PreparedStatement pstmt = null;	//使用预编译语句
        ResultSet rs = null;	//获取的结果集
        try {
            Class.forName(driverClassName); //执行驱动
            con = DriverManager.getConnection(url, username, password); //获取连接
            String sql = "select * from test.student"; //设置的预编译语句格式
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (null != rs){
                while(rs.next()) {
                    System.out.println("student name : " + rs.getString("name"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //关闭资源,倒关
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(con != null) con.close();  //必须要关
            } catch (Exception e) {
            }
        }

    }

    public static void main(String[] args) throws SQLException {
        new JdbcOrigin().addStudent(new Student("cutie2"));
        new JdbcOrigin().listInfo();
    }

}
