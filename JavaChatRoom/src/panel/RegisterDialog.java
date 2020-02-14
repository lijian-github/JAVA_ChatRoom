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
		registerbtn=new JButton("注册");
		cancel=new JButton("取消");
		jp.add(new JLabel("输入昵称"));jp.add(name);
		jp.add(new JLabel("输入邮箱"));jp.add(id);
		jp.add(new JLabel("输入地址"));jp.add(address);
		jp.add(new JLabel(" 输入密码 "));jp.add(pass1);
		jp.add(new JLabel(" 确认密码 "));jp.add(pass2);
		jp.add(registerbtn);jp.add(cancel);
		add(jp,BorderLayout.CENTER);
		registerbtn.addActionListener(this);
		cancel.addActionListener(this);
		setSize(250,170);
		setLocationRelativeTo(null);//居中显示
		setTitle("注册");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
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
						JOptionPane.showMessageDialog(null, "注册成功！","恭喜",JOptionPane.WARNING_MESSAGE);
						setVisible(false);
						return;
					}
					else{
						JOptionPane.showMessageDialog(null, "注册失败！","警告",JOptionPane.WARNING_MESSAGE);
						id.setText(null);
						pass1.setText(null);
						pass2.setText(null);
						name.setText(null);
					}
					
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					JOptionPane.showMessageDialog(null, "错误","警告",JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "输入的两次密码不相同，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
				pass1.setText(null);
				pass2.setText(null);
			}
			
		}
		
	}
		
	}
