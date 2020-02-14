package handel;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import model.DataBag;

public class WriteFile implements Runnable {
	File file;
	FileInputStream fi;
	byte [] bs;
	DataBag filedata;
	String filetype,filepath;
	String prifile=null;
	ObjectOutputStream out;

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public void setPrifile(String prifile) {
		this.prifile = prifile;
	}

	@Override
	public void run() {
		try {
			filedata=new DataBag();
			filedata.setDatatype(filetype);
			filedata.setStringdata(prifile);
			file=new File(filepath);
			String []pre=new String[1];
			pre[0]=file.getName();
			filedata.setStringdatas(pre);
			if (file.length()>2147483647) {
				JOptionPane.showMessageDialog(null, "选择文件太大！","警告",JOptionPane.WARNING_MESSAGE);
				return;
			}
			int length=(int)file.length();
			bs=new byte[length];
			fi=new FileInputStream(file);
			while(fi.read(bs, 0, length)!=-1) {
				filedata.setBytedata(bs);
				out.writeObject(filedata);
				out.flush();
			}
			fi.close();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}
	

}
