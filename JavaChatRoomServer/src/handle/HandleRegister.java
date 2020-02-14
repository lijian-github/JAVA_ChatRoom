package handle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DataBag;
import model.Register;
/*����ע����Ϣ*/
public class HandleRegister {
	
	Connection connection;
	PreparedStatement presql;
	ObjectOutputStream out=null;
	DataBag ifok;
	
	public void setOut(ObjectOutputStream  out) {
		this.out = out;
	}

	public HandleRegister() {
		// TODO �Զ����ɵĹ��캯�����
		connection=new HandleToLineJDBC().GetJDBCConnection();
	}
		
	public void writeRegister(Register register) {
		// TODO �Զ����ɵķ������
		String strsql="insert into register values(?,?,?,null,?)";
		ifok=new DataBag();
		ifok.setDatatype("ifregisted");
		int ok=0;
		try {
			presql=connection.prepareStatement(strsql);
			presql.setString(1, register.getId());
			presql.setString(2, register.getPass());
			presql.setString(3, register.getName());
			presql.setString(4, register.getAddress());
			ok=presql.executeUpdate();
			connection.close();
		} catch (SQLException e) {
		}
		if (ok!=0) {
			ifok.setB(true);
			try {
				out.writeObject(ifok);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if (ok==0) {
			ifok.setB(false);
			try {
				out.writeObject(ifok);
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
		
	}

}
