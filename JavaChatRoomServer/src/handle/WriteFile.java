package handle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import model.DataBag;

public class WriteFile implements Runnable {
	File file;
	FileInputStream fi;
	byte [] bs;
	DataBag filedata;
	String filetype,filepath;
	ObjectOutputStream out;
	String senter,pri;
	HashMap<String, ObjectOutputStream> allOut;
	public void setPri(String pri) {
		this.pri = pri;
	}

	public void setSenter(String senter) {
		this.senter = senter;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	public void setAllOut(HashMap<String, ObjectOutputStream> allOut) {
		this.allOut = allOut;
	}

	@Override
	public void run() {
		try {
			filedata=new DataBag();
			filedata.setDatatype(filetype);
			file=new File(filepath);
			String []pre=new String[1];
			pre[0]=file.getName();
			filedata.setStringdatas(pre);
			if (file.length()>2147483647) {
				return;
			}
			int length=(int)file.length();
			bs=new byte[length];
			fi=new FileInputStream(file);
			while(fi.read(bs, 0, length)!=-1)
				filedata.setBytedata(bs);
			
			if (filetype.equals("fileicon")) {
				out.writeObject(filedata);
				fi.close();
			}
			
			if (filetype.equals("fileall")) {
				Collection<ObjectOutputStream> colout=allOut.values();
				Iterator<ObjectOutputStream> iterout=colout.iterator();
				filedata.setStringdata(senter);
				while (iterout.hasNext()) {
					ObjectOutputStream oos=iterout.next();
					if (oos==allOut.get(senter))
						continue;
					try {
						oos.writeObject(filedata);
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
			
			if (filetype.equals("filepri")) {
				Collection<ObjectOutputStream> colout=allOut.values();
				Iterator<ObjectOutputStream> iterout=colout.iterator();
				filedata.setStringdata(senter);
				while (iterout.hasNext()) {
					ObjectOutputStream oos=iterout.next();
					if (oos==allOut.get(pri)) {
						try {
							oos.writeObject(filedata);
							} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
							}
						}
					}
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}
	

}
