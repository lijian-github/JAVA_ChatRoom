package handle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import model.DataBag;

public class WritepriMessg {
	HashMap<String, ObjectOutputStream> allOut;
	DataBag mgdata;
	String name;
	public void setAllOut(HashMap<String, ObjectOutputStream> allOut) {
		this.allOut = allOut;
	}
	public void setMgdata(DataBag mgdata) {
		this.mgdata = mgdata;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void WriteToPri() {
		mgdata.setStringdata(name+":"+mgdata.getStringdata());
		Collection<ObjectOutputStream> colout=allOut.values();
		Iterator<ObjectOutputStream> iterout=colout.iterator();
		while (iterout.hasNext()) {
			ObjectOutputStream oos=iterout.next();
			if (oos==allOut.get(mgdata.getStringdatas()[0])) {
				try {
					oos.writeObject(mgdata);
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			
		}

	}

	}

}
