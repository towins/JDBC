package org.forten.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

		Connection conn = DriverManager.getConnection(url, username, password);

		String sql = "UPDATE test_book SET price=88.0";

		Statement stat = conn.createStatement();

		int n = stat.executeUpdate(sql);
		System.out.println("更新了" + n + "条数据");

		if (stat != null) {
			stat.close();
		}
		if (conn != null) {
			conn.close();
		}
		System.out.println("数据库操作完成");

	}

}
