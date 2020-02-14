package panel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpDialog extends JDialog{
	JTextArea help;
	String ht;
	public HelpDialog() {
		help=new JTextArea();
		help.setEditable(false);
		add(new JScrollPane(help),BorderLayout.CENTER);
		thisHelp();
		help.append(ht);
		setSize(500,300);
		setLocationRelativeTo(null);//居中显示
		setTitle("帮助");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}
	public void thisHelp() {
		String t="帮助\n\n"+"1、聊天室的主界面发送信息为群发信息\n"
				+"2、可以双击在线列表的用户进行私聊\n"
				+"3、接收到的文件都保存在【E:\\chat\\“你的昵称”】这个文件夹上\n"
				+"4、更改音乐为更改通知铃声（音乐格式必须为*au,*aiff,*wav,*midi,*rfm）\n";
		ht=t;
	}
	public void setHelp(JTextArea help) {
		this.help = help;
	}
	
}
