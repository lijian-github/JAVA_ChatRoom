package panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import handel.Client;
import handel.WriteLogin;
import model.DataBag;

public class LoginPanel extends JFrame implements ActionListener {
	JPanel btnp;
	LBackp backp;
	JLabel label,idl,passl;
	JTextField idjf;
	JPasswordField passpf;
	JButton lgbtn,rgbtn;
	Box bv,bh1,bh2;
	Client client;
	public LoginPanel() {
		client=new Client();
		backp=new LBackp();
		backp.setLayout(new FlowLayout());
		bv=Box.createVerticalBox();
		bv.setOpaque(false);
		bh1=Box.createHorizontalBox();
		bh2=Box.createHorizontalBox();
		bh1.setOpaque(false);
		bh2.setOpaque(false);
		btnp=new JPanel();
		btnp.setOpaque(false);
		label=new JLabel("�����ҵ�¼",JLabel.CENTER);//����
		label.setFont(new Font("����",Font.BOLD, 20));//����Ӵ�20��
		label.setPreferredSize(new Dimension(300, 50));
		idl=new JLabel("����");
		passl=new JLabel("����");
		idjf=new JTextField();
		passpf=new JPasswordField(15);
		idjf.setOpaque(false);
		passpf.setOpaque(false);
		bh1.add(idl);
		bh1.add(idjf);
		bh2.add(passl);
		bh2.add(passpf);
		bv.add(bh1);
		bv.add(new JLabel("   "));
		bv.add(bh2);
		lgbtn=new JButton("��¼");
		rgbtn=new JButton("ע��");
		lgbtn.setContentAreaFilled(false);//���ð���͸��
		rgbtn.setContentAreaFilled(false);
		btnp.add(lgbtn);
		btnp.add(rgbtn);
		backp.add(label);
		backp.add(bv);
		backp.add(btnp);
		add(backp);
		setTitle("��¼");
		setVisible(true);
		setSize(300, 250);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		passpf.addActionListener(this);
		lgbtn.addActionListener(this);
		rgbtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		if (arg0.getSource()==lgbtn||arg0.getSource()==passpf) {
			WriteLogin wlogin=new WriteLogin();
			wlogin.setClient(client);
			wlogin.setId(idjf.getText());
			wlogin.setPass(new String(passpf.getPassword()));
			new Thread(wlogin).start();
			try {
				DataBag iflogined=(DataBag) wlogin.getClient().getOis().readObject();
				if (iflogined.isB()) {
					JOptionPane.showMessageDialog(null, "��¼�ɹ���","��ϲ",JOptionPane.WARNING_MESSAGE);
					setVisible(false);
					new Chatroom(client);
					
				}else {

					JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ�","����",JOptionPane.WARNING_MESSAGE);
					passpf.setText(null);
					idjf.setText(null);
				}
				
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				JOptionPane.showMessageDialog(null, "����","����",JOptionPane.WARNING_MESSAGE);
			}
		}
		if (arg0.getSource()==rgbtn) {
			new RegisterDialog(client);
		}
		
	}
	

}
class LBackp extends JPanel{//������
	ImageIcon icon;
	Image img;
	String bgadr="Loginbackground.jpg";
	public LBackp(){
		// TODO �Զ����ɵĹ��캯�����
		icon=new ImageIcon(bgadr);
		img=icon.getImage();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO �Զ����ɵķ������
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
	}
	
}
