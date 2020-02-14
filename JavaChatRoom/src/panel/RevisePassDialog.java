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
		l1=new JLabel("�µ�����");
		l2=new JLabel("ȷ������");
		newpasspf1=new JPasswordField();
		newpasspf2=new JPasswordField();
		confirmbtn=new JButton("ȷ��");
		cancelbtn=new JButton("ȡ��");
		jp.add(l1);jp.add(newpasspf1);
		jp.add(l2);jp.add(newpasspf2);
		jp.add(confirmbtn);jp.add(cancelbtn);
		add(jp,BorderLayout.CENTER);
		cancelbtn.addActionListener(this);
		confirmbtn.addActionListener(this);
		setTitle("�޸�����");
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
				JOptionPane.showMessageDialog(this, "������������벻��ͬ�����������룡","����",JOptionPane.WARNING_MESSAGE);
				newpasspf1.setText(null);
				newpasspf2.setText(null);
			}
			
		}
		if (e.getSource()==cancelbtn) {
			
		}
	}

}
