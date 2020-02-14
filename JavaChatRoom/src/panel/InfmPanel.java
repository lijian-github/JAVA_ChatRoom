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

public class InfmPanel extends JPanel implements MouseListener{//��¼����Ϣ��
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
		ic.setImage(ic.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));//����ͼƬ��С����ʾͷ��������Сһ��
		icon.setIcon(ic);
		inform=new JLabel("�ǳƵ���Ϣ",JLabel.CENTER);
		inform.setOpaque(false);
		imjp.add(icon,BorderLayout.WEST);
		imjp.add(inform,BorderLayout.CENTER);
		menblist=new JList<String>();
		menblist.setOpaque(false);
		((JComponent) menblist.getCellRenderer()).setOpaque(false);//�����б�ѡ��Ϊ͸��
		mljs=new JScrollPane(menblist);
		mljs.setOpaque(false);
		mljs.getViewport().setOpaque(false);
		mljs.setBorder(BorderFactory.createTitledBorder("�����б�"));
		count=new JLabel("����������");
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
					JOptionPane.showMessageDialog(chatroom,"���ܺ��Լ����죡","����",JOptionPane.WARNING_MESSAGE);
				}else {
					PriChatPanel prisenter=new PriChatPanel();
					prisenter.setName(m);
					prisenter.setClient(client);
					prisenter.setMyname(inform.getText());
					prisenter.setMesArea(chatroom.getMesgpanel().getMesgare());//��ȡ������������
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
