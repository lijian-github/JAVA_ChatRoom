package handle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import model.DataBag;
public class WriteLoginedInfm {//传输登录信息
	String name;
	ObjectOutputStream out;
	DataBag loginedinfm;
	public void setName(String name) {
		this.name = name;
	}
	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}
	public void writeTolg() {
		try {
			loginedinfm=new DataBag();
			loginedinfm.setDatatype("loginedname");
			loginedinfm.setStringdata(name);
			out.writeObject(loginedinfm);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 

	}

}
