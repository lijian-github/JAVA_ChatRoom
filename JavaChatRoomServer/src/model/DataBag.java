package model;

import java.io.Serializable;

public class DataBag implements Serializable{
	private String datatype;
	private String stringdata;
	private byte[] bytedata;
	private String[] stringdatas;
	private boolean b;
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getStringdata() {
		return stringdata;
	}
	public void setStringdata(String stringdata) {
		this.stringdata = stringdata;
	}
	public byte[] getBytedata() {
		return bytedata;
	}
	public void setBytedata(byte[] bytedata) {
		this.bytedata = bytedata;
	}
	public String[] getStringdatas() {
		return stringdatas;
	}
	public void setStringdatas(String[] stringdatas) {
		this.stringdatas = stringdatas;
	}
	public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}
}
