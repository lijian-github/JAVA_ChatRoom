package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import handel.Client;

public class InfmPanel extends JPanel implements MouseListener{//登录者信息块
	JPanel imjp;
	JScrollPane mljs;
	JList<String> menblist;
	JLabel icon,inform,count;
	Client client;
	Chatroom chatroom;
	public InfmPanel() {
		setLayout(new BorderLayout());
		imjp=new JPanel();
		imjp.setBorder(BorderFactory.createEtchedBorder());
		imjp.setOpaque(false);
		imjp.setLayout(new BorderLayout());
		icon=new JLabel();
		icon.setOpaque(false);
		icon.setPreferredSize(new Dimension(50, 50));
		ImageIcon ic = new ImageIcon("icon.png");
		ic.setImage(ic.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));//设置图片大小与显示头像的组件大小一样
		icon.setIcon(ic);
		inform=new JLabel("昵称等信息",JLabel.CENTER);
		inform.setOpaque(false);
		imjp.add(icon,BorderLayout.WEST);
		imjp.add(inform,BorderLayout.CENTER);
		menblist=new JList<String>();
		menblist.setOpaque(false);
		((JComponent) menblist.getCellRenderer()).setOpaque(false);//设置列表选项为透明
		mljs=new JScrollPane(menblist);
		mljs.setOpaque(false);
		mljs.getViewport().setOpaque(false);
		mljs.setBorder(BorderFactory.createTitledBorder("在线列表"));
		count=new JLabel("在线人数：");
		count.setOpaque(false);
		add(imjp, BorderLayout.NORTH);
		add(mljs, BorderLayout.CENTER);
		add(count,BorderLayout.SOUTH);
		setOpaque(false);
		menblist.addMouseListener(this);
	}
	
	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public JLabel getIcon() {
		return icon;
	}

	public JList<String> getMenblist() {
		return menblist;
	}

	public JLabel getInform() {
		return inform;
	}

	public JLabel getCount() {
		return count;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==menblist) {
			if (e.getClickCount()==2) {
				int index = menblist.locationToIndex(e.getPoint());
				String m=menblist.getModel().getElementAt(index);
				if (m.equals(inform.getText())) {
					JOptionPane.showMessageDialog(chatroom,"不能和自己聊天！","警告",JOptionPane.WARNING_MESSAGE);
				}else {
					PriChatPanel prisenter=new PriChatPanel();
					prisenter.setName(m);
					prisenter.setClient(client);
					prisenter.setMyname(inform.getText());
					prisenter.setMesArea(chatroom.getMesgpanel().getMesgare());//获取主界面的聊天框
					prisenter.run();
				}
				
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
