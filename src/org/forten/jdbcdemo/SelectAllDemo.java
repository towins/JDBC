package org.forten.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectAllDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

		Connection conn = DriverManager.getConnection(url, username, password);

		String sql = "SELECT * FROM countries";

		Statement stat = conn.createStatement();

		// executeQuery(selectSql)会执行sql语句的查询逻辑，得到数据结果集合(ResultSet)对象
		// rs中是若干数据记录组成的数据集合，每一行由若干列的值构成（单元格）
		// rs是可以被遍历的，最初数据指针会指向第一行之前
		ResultSet rs = stat.executeQuery(sql);

		// 遍历ResultSet中的数据，并打印
		// rs.next()可以和向下移动数据指针，同时返回是否存在下一行数据的boolean值
		while (rs.next()) {
			// rs.getXXX(int colIndex);或rs.getXXX(String colName);
			// 以上两种方法都可以得到某单元格中的数据，XXX是Java中的数据类型，如：String、Double、Int、Long、Date
			// colIndex参数是从1开始的，即第一个单元格的index是1，通常用在数据是由表达式运算得到的虚拟列上
			// price*discount或first_name || ' ' || last_name
			// colName是指单元格数据所对应的表里的列名，通常更常用，用于一般物理存在的列数据查询上
			String id = rs.getString(1);
			String name = rs.getString(2);
			int regionId = rs.getInt(3);
			System.out.println(id + "\t" + name + "\t" + regionId);
		}

		System.out.println("--------------------------------");

		// 因为rs此时已经指向最后一行数据之后的位置了
		// 所以以下循环不能进入
		while (rs.next()) {
			String id = rs.getString("country_id");
			String name = rs.getString("country_name");
			int regionId = rs.getInt("region_id");
			System.out.println(id + "\t" + name + "\t" + regionId);
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
