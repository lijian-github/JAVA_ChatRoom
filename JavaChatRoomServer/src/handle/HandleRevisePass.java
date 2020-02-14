package handle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DataBag;

public class HandleRevisePass {
	Connection connection;
	PreparedStatement presql;
	ObjectOutputStream out=null;
	DataBag ifok;
	public HandleRevisePass() {
		connection=new HandleToLineJDBC().GetJDBCConnection();
	}
	public void Revise(String id,String pass,ObjectOutputStream out) {
		ifok=new DataBag();
		ifok.setDatatype("revised");
		String strsql="update register set pass=? where mail=?";
		int ok=0;
		try {
			presql=connection.prepareStatement(strsql);
			presql.setString(1, pass);
			presql.setString(2, id);
			ok=presql.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (ok!=0) {
			try {
				out.writeObject(ifok);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
