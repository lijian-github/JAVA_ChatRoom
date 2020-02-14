package handle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HandleToLineJDBC {
	Connection connection;
	public Connection GetJDBCConnection() {
		// TODO 自动生成的构造函数存根
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			//e.printStackTrace();
			System.out.println("驱动加载失败！");
		}
		String uri="jdbc:mysql://localhost/chatroom?useSSL=true&characterEncoding=gb2312";
		try {
			connection=DriverManager.getConnection(uri,"root","");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			//e.printStackTrace();
			System.out.println("连接数据库失败！");
		}
		return connection;
	}
	

}
