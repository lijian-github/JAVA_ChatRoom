package panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import handel.Client;
import handel.WriteFile;
import handel.WriteMessage;

public class PriChatPanel extends JFrame implements ActionListener{
	String name,myname;
	JLabel title;
	JTextArea psare;
	JPanel conp;
	JButton sentpm,sentpf;
	JScrollPane psjs;
	Client client;
	String []pm;
	JTextArea MesArea;//������������
	public void setMyname(String myname) {
		this.myname = myname;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	public void setMesArea(JTextArea mesArea) {
		MesArea = mesArea;
	}

	public void run() {
		pm=new String[2];
		pm[0]=name;
		pm[1]=myname;
		setLayout(new BorderLayout());
		title=new JLabel(name);
		conp=new JPanel();
		conp.setBorder(BorderFactory.createEtchedBorder());
		psare=new JTextArea(3,25);
		psare.setLineWrap(true);//���ݵ���߽��Զ�����
		psjs=new JScrollPane(psare);
		psjs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//��ֹ����ˮƽ����
		sentpm=new JButton("����");
		sentpf=new JButton("�����ļ�");
		conp.add(psjs);
		conp.add(sentpm);
		conp.add(sentpf);
		add(title, BorderLayout.NORTH);
		add(conp,BorderLayout.CENTER);
		setTitle("������"+name+"˽��");
		setSize(500,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		sentpm.addActionListener(this);
		sentpf.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==sentpm) {
			WriteMessage writeMessage=new WriteMessage();
			writeMessage.setOut(client.getOos());
			writeMessage.setMtp("primessage");
			writeMessage.setOto(pm);
			writeMessage.setMessage(psare.getText());
			writeMessage.write();
			Date now=new Date();
			String time=String.format("%tr", now)+'\n';
			MesArea.append("��˽����Ϣ��"+time+"��->"+name+":"+psare.getText()+'\n');
			psare.setText(null);
			setVisible(false);
		}
		if (e.getSource()==sentpf) {
			FileDialog fileDialog=new FileDialog();
			String filepath=fileDialog.getFlpath();
			String filename=fileDialog.getFlname();
			if (filepath!=null) {
				WriteFile writeFile=new WriteFile();
				writeFile.setFilepath(filepath);
				writeFile.setFiletype("filepri");
				writeFile.setPrifile(name);
				writeFile.setOut(client.getOos());
				new Thread(writeFile).start();
				Date now=new Date();
				String time=String.format("%tr", now)+'\n';
				MesArea.append("��˽����Ϣ��"+time+"��->"+name+"�ļ�:"+filename+'\n');
				setVisible(false);
			}
		}
		
	}
	
}
