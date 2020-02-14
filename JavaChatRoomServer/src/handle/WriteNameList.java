package handle;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import model.DataBag;

public class WriteNameList implements Runnable{
	HashMap<String, ObjectOutputStream> allOut;
	DataBag list;
	String namelist=null;
	public void setAllOut(HashMap<String, ObjectOutputStream> allOut) {
		this.allOut = allOut;
	}
	public void Write() {
		list=new DataBag();
		list.setDatatype("namelist");
		Collection<String> col=allOut.keySet();
		Iterator<String> iterator=col.iterator();
		while (iterator.hasNext()) {
			if (namelist==null)
				namelist =iterator.next();
			else
				namelist=namelist+','+iterator.next();
		}
		String []l=namelist.split(",");
		list.setStringdatas(l);
	}
	@Override
	public void run() {
		Collection<ObjectOutputStream> colout=allOut.values();
		Iterator<ObjectOutputStream> iterout=colout.iterator();
		while (iterout.hasNext()) {
			try {
				iterout.next().writeObject(list);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}

}
