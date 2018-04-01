package org.forten.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDemo04 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

		Connection conn = DriverManager.getConnection(url, username, password);

		String sql = "SELECT e.employee_id,e.first_name,e.last_name,e.department_id,d.department_name "
				+ "FROM employees e JOIN departments d " + "ON (e.department_id=d.department_id) "
				+ "WHERE salary>=100000";

		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery(sql);

		if (rs.next()) {
			do {
				int eId = rs.getInt(1);
				String fn = rs.getString(2);
				String ln = rs.getString(3);
				int dId = rs.getInt(4);
				String dName = rs.getString(5);
				System.out.println(eId + "\t" + fn + "\t" + ln + "\t" + dId + "\t" + dName);
			} while (rs.next());
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
