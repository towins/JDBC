package org.forten.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDemo02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

		Connection conn = DriverManager.getConnection(url, username, password);

		String sql = "SELECT count(employee_id) FROM employees WHERE department_id=90";

		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery(sql);

		// 因为本例中的查询逻辑一定会出现1行1列数据
		// 所以不用判断，直接移动数据指针即可
		rs.next();

		long count = rs.getLong(1);
		System.out.println("90部门共有" + count + "位领导");

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
