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
import javax.swing.JTextField;
import handel.Client;
import handel.WriteRegister;
import model.DataBag;


public class RegisterDialog extends JDialog implements ActionListener {
	JTextField id,name,address;
	JPasswordField pass1,pass2;
	JButton registerbtn,cancel;
	JPanel jp;
	Client client;
	public RegisterDialog(Client client) {
		this.client=client;
		jp=new JPanel();
		jp.setLayout(new GridLayout(6,2));
		id=new JTextField(10);
		name=new JTextField(10);
		address=new JTextField(10);
		pass1=new JPasswordField(10);
		pass2=new JPasswordField(10);
		registerbtn=new JButton("ע��");
		cancel=new JButton("ȡ��");
		jp.add(new JLabel("�����ǳ�"));jp.add(name);
		jp.add(new JLabel("��������"));jp.add(id);
		jp.add(new JLabel("�����ַ"));jp.add(address);
		jp.add(new JLabel(" �������� "));jp.add(pass1);
		jp.add(new JLabel(" ȷ������ "));jp.add(pass2);
		jp.add(registerbtn);jp.add(cancel);
		add(jp,BorderLayout.CENTER);
		registerbtn.addActionListener(this);
		cancel.addActionListener(this);
		setSize(250,170);
		setLocationRelativeTo(null);//������ʾ
		setTitle("ע��");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==cancel) {
			setVisible(false);
		}
		if (e.getSource()==registerbtn) {
			String p1=new String(pass1.getPassword());
			String p2=new String(pass2.getPassword());
			if (p1.equals(p2)) {
				WriteRegister register=new WriteRegister();
				register.setClient(client);
				register.setId(id.getText());
				register.setName(name.getText());
				register.setAdrress(address.getText());
				register.setPass(p1);
				new Thread(register).start();
				try {
					DataBag re=(DataBag) client.getOis().readObject();
					if (re.isB()) {
						JOptionPane.showMessageDialog(null, "ע��ɹ���","��ϲ",JOptionPane.WARNING_MESSAGE);
						setVisible(false);
						return;
					}
					else{
						JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�","����",JOptionPane.WARNING_MESSAGE);
						id.setText(null);
						pass1.setText(null);
						pass2.setText(null);
						name.setText(null);
					}
					
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					JOptionPane.showMessageDialog(null, "����","����",JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "������������벻��ͬ�����������룡","����",JOptionPane.WARNING_MESSAGE);
				pass1.setText(null);
				pass2.setText(null);
			}
			
		}
		
	}
		
	}
