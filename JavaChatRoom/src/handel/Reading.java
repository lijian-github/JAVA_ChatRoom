package handel;

import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.DataBag;
import model.ListDataModel;
import panel.Chatroom;
import panel.PriChatPanel;

public class Reading implements Runnable{
	ObjectInputStream in;
	Client client;
	DataBag result;
	Bellmusic bmc;
	JList<String> menblist;
	JLabel count;
	int lastn=0,n=0;
	Chatroom chatroom;
	JLabel icon;
	JLabel imform;
	
	JTextArea mesgare;
	
	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}

	public void setMenblist(JList<String> menblist) {
		this.menblist = menblist;
	}
	public void setCount(JLabel count) {
		this.count = count;
	}

	public void setIcon(JLabel icon) {
		this.icon = icon;
	}

	public void setMesgare(JTextArea mesgare) {
		this.mesgare = mesgare;
	}
	
	public void setImfor(JLabel imform) {
		this.imform = imform;
	}

	public void setBmc(Bellmusic bmc) {
		this.bmc = bmc;
	}
	
	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}

	@Override
	public void run() {
		while (true) {
			try {
				result=(DataBag) in.readObject();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				System.out.println("服务器断开！");
				break;
			}
			
			if (result.getDatatype().equals("namelist")) {
				lastn=n;
				String []list=result.getStringdatas();
				n=list.length;
				if (lastn>0)
					bmc.newonebell();
				ListDataModel ldm=new ListDataModel();
				ldm.setMlist(list);
				menblist.setModel(ldm);
				menblist.setListData(list);
				count.setText("在线人数："+n);
			}
			
			if (result.getDatatype().equals("loginedname")) {
				bmc.loginedbell();
				String name=result.getStringdata();
				imform.setText(name);
			}
			
			if (result.getDatatype().startsWith("file")) {
				FileOutputStream fos=null;
				File fm=new File("E:/chat/"+imform.getText());
				if (!fm.exists()) {
					fm.mkdirs();
				}
				File file=new File("E:/chat/"+imform.getText()+'/'+imform.getText()+"_"+result.getDatatype()+result.getStringdatas()[0]);
				try {
					fos=new FileOutputStream(file);
					fos.write(result.getBytedata());
					fos.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				fos=null;
				if (result.getDatatype().equals("fileicon")) {
					ImageIcon ic = new ImageIcon(file.getAbsolutePath());
					ic.setImage(ic.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));//设置图片大小与显示头像的组件大小一样
					icon.setIcon(ic);
				}
				if (result.getDatatype().equals("fileall")) {
					bmc.noticebell();
					String message=result.getStringdata()+":发来一个文件->"+result.getStringdatas()[0];
					Date now=new Date();
					String time=String.format("%tr", now)+'\n';
					mesgare.append(time+message+'\n');
					int k=JOptionPane.showConfirmDialog(chatroom, "是否打开文件？", 
							"收到"+result.getStringdata()+"文件"+result.getStringdatas()[0], 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.INFORMATION_MESSAGE,null);
					if (k==JOptionPane.YES_OPTION) {
						try {
							Desktop.getDesktop().open(file);//打开文件
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}

				}
				if (result.getDatatype().equals("filepri")) {
					bmc.noticebell();
					String message=result.getStringdata()+":给你发来一个文件->"+result.getStringdatas()[0];
					Date now=new Date();
					String time=String.format("%tr", now)+'\n';
					mesgare.append("（私聊信息）"+time+message+'\n');
					int k=JOptionPane.showConfirmDialog(chatroom, "是否打开文件？", 
							"收到"+result.getStringdata()+"私发文件"+result.getStringdatas()[0], 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.INFORMATION_MESSAGE,null);
					if (k==JOptionPane.YES_OPTION) {
						try {
							Desktop.getDesktop().open(file);//打开文件
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				}
			}
			
			
			if (result.getDatatype().equals("allmessage")) {
				bmc.noticebell();
				String message=result.getStringdata();
				Date now=new Date();
				String time=String.format("%tr", now)+'\n';
				mesgare.append(time+message+'\n');
			}
			
			if (result.getDatatype().equals("primessage")) {
				bmc.noticebell();
				String message=result.getStringdata();
				Date now=new Date();
				String time=String.format("%tr", now)+'\n';
				mesgare.append("（私聊信息）"+time+message+'\n');
				int k=JOptionPane.showConfirmDialog(chatroom, "是否回复？", 
						"收到"+result.getStringdatas()[1]+"的私聊信息", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.INFORMATION_MESSAGE,null);
				if (k==JOptionPane.YES_OPTION) {
					PriChatPanel prigeter=new PriChatPanel();
					prigeter.setClient(client);
					prigeter.setName(result.getStringdatas()[1]);
					prigeter.setMyname(result.getStringdatas()[0]);
					prigeter.setMesArea(chatroom.getMesgpanel().getMesgare());
					prigeter.run();
				}
			}
			
			if (result.getDatatype().equals("twologined")) {
				JOptionPane.showMessageDialog(chatroom, "账号在别处登录，被逼下线！","警告",JOptionPane.WARNING_MESSAGE);
				chatroom.dispose();
				
			}
			if (result.getDatatype().equals("revised")) {
				JOptionPane.showMessageDialog(chatroom, "密码修改成功","恭喜",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

}

