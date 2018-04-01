package org.forten.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDemo03 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

		Connection conn = DriverManager.getConnection(url, username, password);

		String sql = "SELECT employee_id,first_name,last_name,phone_number FROM employees WHERE employee_id=10000";

		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery(sql);

		// 本例中的查询是按照唯一约束列进行条件查询
		// 所以数据结果集中是0或1条数据
		if (rs.next()) {
			int id = rs.getInt(1);
			String fn = rs.getString(2);
			String ln = rs.getString(3);
			String pn = rs.getString(4);
			System.out.println(id + "\t" + fn + "\t" + ln + "\t" + pn);
		} else {
			System.out.println("没有查询到符合条件的数据");
		}

		if (rs != null) {
			rs.close();
		}
		if (stat != null) {
			stat.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
