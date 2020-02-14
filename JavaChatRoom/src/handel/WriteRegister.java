package handel;

import java.io.IOException;

import model.DataBag;

public class WriteRegister implements Runnable{
	Client client;
	String id,pass,name,adrress;
	DataBag wrdata;
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


	public void setName(String name) {
		this.name = name;
	}


	public void setAdrress(String adrress) {
		this.adrress = adrress;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			wrdata=new DataBag();
			wrdata.setDatatype("register");
			String []reg=new String[4];
			reg[0]=id;
			reg[1]=pass;
			reg[2]=name;
			reg[3]=adrress;
			wrdata.setStringdatas(reg);
			client.getOos().writeObject(wrdata);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			System.out.println("连接断开");
		}
	}

}
