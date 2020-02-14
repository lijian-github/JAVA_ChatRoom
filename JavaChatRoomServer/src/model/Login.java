package model;

public class Login {
	String id;
	String pass;
	Boolean logined=false;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Boolean getLogined() {
		return logined;
	}
	public void setLogined(Boolean logined) {
		this.logined = logined;
	}
	

}
