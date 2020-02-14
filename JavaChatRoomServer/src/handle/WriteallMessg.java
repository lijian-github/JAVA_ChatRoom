package handle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import model.DataBag;

public class WriteallMessg {
	HashMap<String, ObjectOutputStream> allOut;
	DataBag mgdata;
	String message;
	String name;
	public void setAllOut(HashMap<String, ObjectOutputStream> allOut) {
		this.allOut = allOut;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public void setMessage(String message) {
		this.message = message;
	}

	public void WriteToAll() {
		// TODO 自动生成的方法存根
		mgdata=new DataBag();
		mgdata.setDatatype("allmessage");
		mgdata.setStringdata(name+":"+message);
		Collection<ObjectOutputStream> colout=allOut.values();
		Iterator<ObjectOutputStream> iterout=colout.iterator();
		while (iterout.hasNext()) {
			ObjectOutputStream oos=iterout.next();
			if (oos==allOut.get(name))
				continue;
			try {
				oos.writeObject(mgdata);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

	}

}
