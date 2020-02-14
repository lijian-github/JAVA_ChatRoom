package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import handle.HandleIcon;
import handle.HandleLogin;
import handle.HandleRegister;
import handle.HandleRevisePass;
import handle.WriteFile;
import handle.WriteLoginedInfm;
import handle.WriteNameList;
import handle.WriteallMessg;
import handle.WritepriMessg;
import model.DataBag;
import model.Login;
import model.Register;

public class Server {
	ServerSocket serverSocket=null;
	Socket Ssocket=null;
	HashMap<String, ObjectOutputStream> allOut;
	public Server() {
		// TODO 自动生成的构造函数存根
		
		allOut=new HashMap<String, ObjectOutputStream>();//建立输出流链表，存储向每一个客户端的输出流
		while(true) {
			try {
				serverSocket=new ServerSocket(2018);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				System.out.println("正在监听");
			}
			try {
				System.out.println("等待客户端呼叫");
				Ssocket=serverSocket.accept();
				System.out.println("客户端ip"+Ssocket.getInetAddress());
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				System.out.println("等待客户端");
			}
			if (Ssocket!=null) {
				new Thread(new ServerThread(Ssocket)).start();
			}
			
		}
	}
	
	class ServerThread extends Thread{
		Socket socket;
		ObjectInputStream ois;
		ObjectOutputStream oos;
		FileOutputStream fos=null;
		String key=null;
		//String result=null;
		public ServerThread(Socket s) {
			// TODO 自动生成的构造函数存根
			socket=s;
			try {
				oos=new ObjectOutputStream(socket.getOutputStream());
				ois=new ObjectInputStream(socket.getInputStream());
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true) {
				DataBag result=null;
				try {
					result=(DataBag) ois.readObject();
					
				
				if (result.getDatatype().equals("register")) {
					Register register=new Register();
					String id=result.getStringdatas()[0];
					String pass=result.getStringdatas()[1];
					String name=result.getStringdatas()[2];
					String address=result.getStringdatas()[3];
					register.setId(id);
					register.setPass(pass);
					register.setName(name);
					register.setAddress(address);
					HandleRegister handelRegister= new HandleRegister();
					handelRegister.setOut(oos);
					handelRegister.writeRegister(register);
					
				}
				
				if (result.getDatatype().equals("login")) {
					Login login=new Login();
					String id=result.getStringdatas()[0];
					String pass=result.getStringdatas()[1];
					login.setId(id);
					login.setPass(pass);
					HandleLogin handleLogin=new HandleLogin();
					handleLogin.setOut(oos);
					handleLogin.Login(login);
					this.key=handleLogin.getName();
					if (allOut.containsKey(key)) {
						DataBag relogin=new DataBag();
						relogin.setDatatype("twologined");
						allOut.get(key).writeObject(relogin);
					}
					if (login.getLogined()) {
						allOut.put(key, oos);//登录成功加入列表
						WriteLoginedInfm infm=new WriteLoginedInfm();//传输用户信息
						infm.setOut(oos);
						infm.setName(handleLogin.getName());
						infm.writeTolg();
						WriteNameList list=new WriteNameList();
						list.setAllOut(allOut);
						list.Write();
						new Thread(list).start();
						String icpath=handleLogin.getIcpath();
						if (icpath!=null) {
							WriteFile writeFile=new WriteFile();
							writeFile.setFilepath(icpath);
							writeFile.setFiletype("fileicon");
							writeFile.setOut(oos);
							Thread.sleep(2000);
							new Thread(writeFile).start();
						}
					}
					
				}
				
				if (result.getDatatype().startsWith("file")) {
					File fm=new File("C:/chatroom/"+key);
					if (!fm.exists()) {
						fm.mkdirs();
					}
					File file=new File("C:/chatroom/"+key+'/'+key+"_"+result.getDatatype()+result.getStringdatas()[0]);
					if (fos==null) {
						fos=new FileOutputStream(file);
					}
					fos.write(result.getBytedata());//保存文件到服务端硬盘
					fos.close();
					fos=null;
					if (result.getDatatype().equals("fileicon")) {
						HandleIcon handleIcon=new HandleIcon();
						handleIcon.setOut(oos);
						handleIcon.setName(key);
						handleIcon.setIconpath(file.getAbsolutePath());
						handleIcon.writeicon();
					}
					if (result.getDatatype().equals("fileall")) {
						WriteFile writeFile=new WriteFile();
						writeFile.setAllOut(allOut);
						writeFile.setFilepath(file.getAbsolutePath());
						writeFile.setFiletype("fileall");
						writeFile.setSenter(key);
						new Thread(writeFile).start();
					}
					if (result.getDatatype().equals("filepri")) {
						WriteFile writeFile=new WriteFile();
						writeFile.setAllOut(allOut);
						writeFile.setFilepath(file.getAbsolutePath());
						writeFile.setFiletype("filepri");
						writeFile.setSenter(key);
						writeFile.setPri(result.getStringdata());
						new Thread(writeFile).start();
					}
					
				}
				
				if (result.getDatatype().equals("allmessage")) {
					String message=result.getStringdata();
					WriteallMessg writeallMessg=new WriteallMessg();
					writeallMessg.setAllOut(allOut);
					writeallMessg.setName(key);
					writeallMessg.setMessage(message);
					writeallMessg.WriteToAll();
					
				}
				
				if (result.getDatatype().equals("primessage")) {
					WritepriMessg writepriMessg=new WritepriMessg();
					writepriMessg.setMgdata(result);
					writepriMessg.setAllOut(allOut);
					writepriMessg.setName(key);
					writepriMessg.WriteToPri();
				}
				if (result.getDatatype().equals("revisePass")) {
					HandleRevisePass revisePass=new HandleRevisePass();
					revisePass.Revise(key, result.getStringdata(),oos);
					
				}

			} catch (IOException | ClassNotFoundException | InterruptedException e) {
					// TODO 自动生成的 catch 块
					System.out.println(key+" 断开");
					System.out.println(e);
					allOut.remove(key,oos);
					if (!allOut.isEmpty()) {
						WriteNameList list=new WriteNameList();
						list.setAllOut(allOut);
						list.Write();
						new Thread(list).start();
						
					}
					break;
				}
				
			}
				
		}
	}

	
}


