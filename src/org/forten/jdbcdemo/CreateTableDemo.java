package org.forten.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 加载要连接的数据库主驱动类（JDBC驱动主类）
		// 通知Java要连接的数据库是MySQL
		Class.forName("com.mysql.jdbc.Driver");

		// 连接数据库的用户名
		String username = "root";
		// 连接数据库的密码
		String password = "123456";
		// 设置连接数据库的URL串，通知Java连接哪个数据库
		// jdbc:mysql://主机名或IP:端口号/数据库名称?characterEncoding=utf8&useSSL=true
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

		// 让Java程序获得对MySQL数据库的连接对象
		Connection conn = DriverManager.getConnection(url, username, password);

		// 写要执行的SQL语句
		String sql = "CREATE TABLE test_book("
				+ "id int(11) PRIMARY KEY AUTO_INCREMENT,"
				+ "name varchar(50) NOT NULL,"
				+ "author varchar(30) NOT NULL,"
				+ "price DECIMAL(6,1)"
				+ ")";

		// 创建一个JDBC的语句对象，用于执行某条SQL语句
		Statement stat = conn.createStatement();

		// 执行SQL语句
		// execute方法一般用于执行存储过程，返回boolean，true表示执行结果是一个ResultSet
		// executeQuery方法用于执行SELECT语句，返回的是ResultSet
		// executeUpdate方法用于执行INSERT INTO、UPDATE、DELETE写入操作，返回int，即影响的数据记录数量
		boolean type = stat.execute(sql);
		System.out.println(type);

		// 关闭连接，释放数据库资源
		if (stat != null) {
			stat.close();
		}
		if (conn != null) {
			conn.close();
		}
		System.out.println("数据库操作完成");
	}

}
