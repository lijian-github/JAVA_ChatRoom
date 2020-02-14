package handle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DataBag;
import model.Login;
/*处理登录信息*/
public class HandleLogin {
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	ObjectOutputStream out;
	String name=null;
	String icpath=null;
	DataBag iflogined;
	public HandleLogin() {
		// TODO 自动生成的构造函数存根
		connection=new HandleToLineJDBC().GetJDBCConnection();
	}
	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIcpath() {
		return icpath;
	}
	public void Login(Login login) {
		iflogined=new DataBag();
		iflogined.setDatatype("iflogined");
		String id=login.getId();
		String pw=login.getPass();
		String sql="select * from register where mail=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet=preparedStatement.executeQuery();
			if (resultSet.next()==false)
				try {
					iflogined.setB(false);
					out.writeObject(iflogined);
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					return;
				}
			else {
				if (pw.equals(resultSet.getString(2))) {
					try {
						iflogined.setB(true);
						out.writeObject(iflogined);
						name=resultSet.getString(3);
						icpath=resultSet.getString(4);
						login.setLogined(true);
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
						return;
					}
				} else
					try {
						iflogined.setB(false);
						out.writeObject(iflogined);
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
						return;
					}
					
			}
		}		catch (SQLException e) {
			System.out.println("服务中断！");
			return ;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}