package handel;

import java.io.IOException;
import java.io.ObjectOutputStream;

import model.DataBag;

public class WriteMessage {
	String message;
	String mtp;
	String []oto;
	DataBag mgdata;
	ObjectOutputStream out;
	public String[] getOto() {
		return oto;
	}
	public void setOto(String[] oto) {
		this.oto = oto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMtp() {
		return mtp;
	}
	public void setMtp(String mtp) {
		this.mtp = mtp;
	}
	public ObjectOutputStream getOut() {
		return out;
	}
	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}
	
	public void write() {
		mgdata=new DataBag();
		mgdata.setDatatype(mtp);
		mgdata.setStringdata(message);
		mgdata.setStringdatas(oto);
		try {
			out.writeObject(mgdata);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
	

}
