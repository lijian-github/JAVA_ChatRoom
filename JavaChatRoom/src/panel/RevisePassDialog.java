package panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import handel.Client;
import handel.WriteRevisePass;


public class RevisePassDialog extends JDialog implements ActionListener{
	JLabel l1,l2;
	JPasswordField newpasspf1,newpasspf2;
	JButton confirmbtn,cancelbtn;
	JPanel jp;
	Client client=null;
	public RevisePassDialog(Client client) {
		this.client=client;
		jp=new JPanel();
		jp.setLayout(new GridLayout(3,2));
		l1=new JLabel("新的密码");
		l2=new JLabel("确认密码");
		newpasspf1=new JPasswordField();
		newpasspf2=new JPasswordField();
		confirmbtn=new JButton("确认");
		cancelbtn=new JButton("取消");
		jp.add(l1);jp.add(newpasspf1);
		jp.add(l2);jp.add(newpasspf2);
		jp.add(confirmbtn);jp.add(cancelbtn);
		add(jp,BorderLayout.CENTER);
		cancelbtn.addActionListener(this);
		confirmbtn.addActionListener(this);
		setTitle("修改密码");
		setSize(250,160);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==confirmbtn) {
			String pass1=new String(newpasspf1.getPassword());
			String pass2=new String(newpasspf2.getPassword());
			if (pass1.equals(pass2)) {
				System.out.println(client);
				WriteRevisePass revisePass=new WriteRevisePass();
				revisePass.setClient(client);
				revisePass.setRepass(pass1);
				revisePass.write();
				setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(this, "输入的两次密码不相同，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
				newpasspf1.setText(null);
				newpasspf2.setText(null);
			}
			
		}
		if (e.getSource()==cancelbtn) {
			
		}
	}

}
