package handel;

import java.io.IOException;

import model.DataBag;

public class WriteRevisePass {
	DataBag bag;
	String repass;
	Client client;
	public void setRepass(String repass) {
		this.repass = repass;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void write() {
		DataBag bg=new DataBag();
		bg.setDatatype("revisePass");
		bg.setStringdata(repass);
		try {
			client.getOos().writeObject(bg);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}

}
