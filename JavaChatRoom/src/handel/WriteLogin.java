package handel;

import java.io.IOException;

import model.DataBag;

public class WriteLogin implements Runnable{
	Client client;
	String id,pass;
	DataBag lg;
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Client getClient() {
		return client;
	}

	public void setId(String id) {
		this.id = id;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		String []lgs=new String[2];
		lgs[0]=id;
		lgs[1]=pass;
		lg=new DataBag();
		lg.setDatatype("login");
		lg.setStringdatas(lgs);
		try {
			client.getOos().writeObject(lg);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
