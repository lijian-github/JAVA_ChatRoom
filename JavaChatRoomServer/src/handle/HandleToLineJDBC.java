package handle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HandleToLineJDBC {
	Connection connection;
	public Connection GetJDBCConnection() {
		// TODO �Զ����ɵĹ��캯�����
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			//e.printStackTrace();
			System.out.println("��������ʧ�ܣ�");
		}
		String uri="jdbc:mysql://localhost/chatroom?useSSL=true&characterEncoding=gb2312";
		try {
			connection=DriverManager.getConnection(uri,"root","");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			//e.printStackTrace();
			System.out.println("�������ݿ�ʧ�ܣ�");
		}
		return connection;
	}
	

}
