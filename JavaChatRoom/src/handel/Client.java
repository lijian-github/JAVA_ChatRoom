package handel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	Socket clientsocket=null;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	InetAddress address=null;
	public Client() {
		// TODO 自动生成的方法存根
		String ip="127.0.0.1";
		int port=2018;
		try {
			clientsocket=new Socket();
			if (clientsocket.isConnected()) {}
			else {
				address=InetAddress.getByName(ip);
				InetSocketAddress socketAddress=new InetSocketAddress(ip, port);
				clientsocket.connect(socketAddress);
				ois=new ObjectInputStream(clientsocket.getInputStream());
				oos=new ObjectOutputStream(clientsocket.getOutputStream());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("服务器断开！");
		}
		
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

}

