package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import handel.Bellmusic;
import handel.Client;
import handel.Reading;
import handel.WriteFile;
import handel.WriteMessage;

public class Chatroom extends JFrame{//总界面
	Mesgpanel mesgpanel;
	InfmPanel infmpanel;
	Backp bg;
	Menub menub;
	Bellmusic bmc;//通知音频播放
	Client client;//客户端对象，为了界面接收信息
	public Chatroom(Client client) {
		this.client=client;
		bmc=new Bellmusic();
		bg=new Backp();
		bg.setLayout(new BorderLayout());
		bg.setSize(700,500);
		bg.setbg();
		menub=new Menub();
		mesgpanel=new Mesgpanel();
		infmpanel=new InfmPanel();
		mesgpanel.setPreferredSize(new Dimension(500, 500));
		infmpanel.setPreferredSize(new Dimension(200, 500));
		menub.setBackp(bg);
		menub.setIcon(infmpanel.getIcon());
		menub.setBm(bmc);
		menub.setChatroom(this);
		menub.setClient(client);
		mesgpanel.setClient(client);
		infmpanel.setClient(client);
		infmpanel.setChatroom(this);
		bg.add(menub,BorderLayout.NORTH);
		bg.add(mesgpanel,BorderLayout.CENTER);
		bg.add(infmpanel,BorderLayout.EAST);
		add(bg);
		setSize(700,500);
		setLocationRelativeTo(null);//居中
		setVisible(true);
		setTitle("聊天室");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dosomething();
	}
	
	public Mesgpanel getMesgpanel() {
		return mesgpanel;
	}

	public InfmPanel getInfmpanel() {
		return infmpanel;
	}

	private void dosomething() {
		Reading reading=new Reading();
		reading.setChatroom(this);
		reading.setIn(client.getOis());
		reading.setClient(client);
		reading.setIcon(infmpanel.getIcon());
		reading.setImfor(infmpanel.getInform());
		reading.setMenblist(infmpanel.getMenblist());
		reading.setCount(infmpanel.getCount());
		reading.setMesgare(mesgpanel.getMesgare());
		reading.setBmc(bmc);
		new Thread(reading).start();
		
	}
}

class Backp extends JPanel{//背景层
	ImageIcon icon;
	Image img;
	String bgadr="background.jpg";
	public void setbg() {
		// TODO 自动生成的构造函数存根
		icon=new ImageIcon(bgadr);
		img=icon.getImage();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
	}

	public void setBgadr(String bgadr) {
		this.bgadr = bgadr;
	}
	
}

class Menub extends JMenuBar implements ActionListener{//菜单
	Client client=null;
	Bellmusic bm;
	JMenu menu;
	JMenuItem cgbg,cgpass,cgmusc,cgic,cghelp,cgexit;
	Backp backp;
	JLabel icon;
	String bgpath,icpath,bmpath;
	Chatroom chatroom;
	public Menub() {
		// TODO 自动生成的构造函数存根
		menu=new JMenu("菜单");
		cgbg=new JMenuItem("更换背景");
		cgpass=new JMenuItem("修改密码");
		cgic=new JMenuItem("更换头像");
		cgmusc=new JMenuItem("更换音乐");
		cghelp=new JMenuItem("查看帮助");
		cgexit=new JMenuItem("退出登录");
		menu.add(cgbg);
		menu.add(cgpass);
		menu.add(cgic);
		menu.add(cgmusc);
		menu.add(cghelp);
		menu.add(cgexit);
		add(menu);
		cgbg.addActionListener(this);
		cgmusc.addActionListener(this);
		cgic.addActionListener(this);
		cgpass.addActionListener(this);
		cghelp.addActionListener(this);
		cgexit.addActionListener(this);
		setOpaque(false);
	}


	public void setIcon(JLabel icon) {
		this.icon=icon;
		
	}
	
	public void setClient(Client client) {
		this.client = client;
	}


	public void setBackp(Backp backp) {
		this.backp = backp;
	}


	public String getBgpath() {
		return bgpath;
	}


	public String getIcpath() {
		return icpath;
	}
	
	public void setBm(Bellmusic bm) {
		this.bm = bm;
	}

	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource()==cgbg) {
			bgpath=new BackgDialog().getBgpath();
			if (bgpath!=null) {
				backp.setBgadr(bgpath);
				backp.setbg();
				backp.updateUI();
			}
		}
		if (e.getSource()==cgmusc) {
			bmpath=new BellmDialog().getBmpath();
			bm.setBellPath(bmpath);
		}
		if (e.getSource()==cgic) {
			icpath=new BackgDialog().getBgpath();
			if (icpath!=null) {
				WriteFile file=new WriteFile();
				file.setFilepath(icpath);
				file.setFiletype("fileicon");
				file.setOut(client.getOos());
				new Thread(file).start();
			}
		}
		if (e.getSource()==cgpass) {
			RevisePassDialog passDialog=new RevisePassDialog(client);
		}
		if (e.getSource()==cghelp) {
			new HelpDialog();
		}
		if (e.getSource()==cgexit) {
			int n=JOptionPane.showConfirmDialog(chatroom, "确定退出？","退出",JOptionPane.YES_NO_OPTION);
			if (n==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	
}
