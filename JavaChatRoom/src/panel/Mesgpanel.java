package panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import handel.Client;
import handel.WriteFile;
import handel.WriteMessage;

public class Mesgpanel extends JPanel implements ActionListener{//群聊块
	JPanel senjp;
	JTextArea mesgare,contare;
	JButton sentmesg,sentfl;
	JScrollPane sjs,cjs;
	Client client;
	public Mesgpanel() {
		setLayout(new BorderLayout());
		senjp=new JPanel();
		senjp.setOpaque(false);
		senjp.setBorder(BorderFactory.createEtchedBorder());//设置边框
		mesgare=new JTextArea(10,20);
		mesgare.setOpaque(false);
		mesgare.setEditable(false);
		mesgare.setFont(new Font("微软雅黑",Font.PLAIN,15));
		sjs=new JScrollPane(mesgare);
		sjs.setOpaque(false);
		sjs.getViewport().setOpaque(false);
		sjs.setBorder(BorderFactory.createTitledBorder("聊天信息"));//设置边框
		contare=new JTextArea(3,25);
		contare.setOpaque(false);
		contare.setLineWrap(true);//内容到达边界自动换行
		cjs=new JScrollPane(contare);
		cjs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//禁止出现水平滚动
		cjs.setOpaque(false);
		cjs.getViewport().setOpaque(false);
		sentmesg=new JButton("发送");
		sentfl=new JButton("发送文件");
		sentfl.setContentAreaFilled(false);
		sentmesg.setContentAreaFilled(false);
		senjp.add(cjs);
		senjp.add(sentmesg);
		senjp.add(sentfl);
		add(sjs,BorderLayout.CENTER);
		add(senjp,BorderLayout.SOUTH);
		setOpaque(false);
		sentmesg.addActionListener(this);
		sentfl.addActionListener(this);
		}
	
	public JTextArea getMesgare() {
		return mesgare;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource()==sentmesg) {
			WriteMessage writeMessage=new WriteMessage();
			writeMessage.setOut(client.getOos());
			writeMessage.setMtp("allmessage");
			writeMessage.setMessage(contare.getText());
			writeMessage.write();
			Date now=new Date();
			String time=String.format("%tr", now)+'\n';
			mesgare.append(time+"我:"+contare.getText()+'\n');
			contare.setText(null);
		}
		if (e.getSource()==sentfl) {
			FileDialog fileDialog=new FileDialog();
			String filepath=fileDialog.getFlpath();
			String filename=fileDialog.getFlname();
			if (filepath!=null) {
				WriteFile writeFile=new WriteFile();
				writeFile.setFilepath(filepath);
				writeFile.setFiletype("fileall");
				writeFile.setOut(client.getOos());
				Date now=new Date();
				String time=String.format("%tr", now)+'\n';
				mesgare.append(time+"我:"+"文件->"+filename+'\n');
				new Thread(writeFile).start();
			}
		}
	}
}
