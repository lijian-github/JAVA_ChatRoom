package handle;

import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DataBag;

public class HandleIcon {//修改头像
	String iconpath;
	String name;
	Connection connection;
	PreparedStatement presql;
	ObjectOutputStream out=null;
	DataBag ifok;
	
	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}

	public void setName(String n) {
		this.name = n;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public HandleIcon() {
		connection=new HandleToLineJDBC().GetJDBCConnection();
	}
	
	public void writeicon() {
		String strsql="update register set icon=? where name=?";
		int ok=0;
		try {
			presql=connection.prepareStatement(strsql);
			presql.setString(1, iconpath);
			presql.setString(2, name);
			ok=presql.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (ok!=0) {
			WriteFile writeFile=new WriteFile();
			writeFile.setFilepath(iconpath);
			writeFile.setFiletype("fileicon");
			writeFile.setOut(out);
			new Thread(writeFile).start();
		}

	}

}
