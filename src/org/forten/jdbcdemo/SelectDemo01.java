package org.forten.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class SelectDemo01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

		Connection conn = DriverManager.getConnection(url, username, password);

		String sql = "SELECT concat(first_name,' ',last_name) full_name,email,phone_number,hire_date "
				+ "FROM employees " + "WHERE department_id=90 " + "ORDER BY hire_date";

		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery(sql);

		// 因为本例的数据查询逻辑执行的结果可以是0到多条数据
		// 可以使用以下的if块进行是否无数据结果的判断处理
		// 如果没有数据，是没问题的，但如果有数据，则必须使用do-while结构进行rs的数据处理
		// 因为在if处已经将数据指针移到了第一条数据位置，不能直接使用while结构（不能先判断后执行）
		if (!rs.next()) {
			System.out.println("No Data!!!!!!!!");
		} else {
			do {
				String name = rs.getString(1);
				// String name = rs.getString("full_name");
				String email = rs.getString("email");
				String pn = rs.getString("phone_number");
				Date hireDate = rs.getDate("hire_date");
				System.out.println(name + "\t" + email + "\t" + pn + "\t" + hireDate);
			} while (rs.next());
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
