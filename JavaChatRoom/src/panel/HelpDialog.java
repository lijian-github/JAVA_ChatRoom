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
		setLocationRelativeTo(null);//������ʾ
		setTitle("����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}
	public void thisHelp() {
		String t="����\n\n"+"1�������ҵ������淢����ϢΪȺ����Ϣ\n"
				+"2������˫�������б���û�����˽��\n"
				+"3�����յ����ļ��������ڡ�E:\\chat\\������ǳơ�������ļ�����\n"
				+"4����������Ϊ����֪ͨ���������ָ�ʽ����Ϊ*au,*aiff,*wav,*midi,*rfm��\n";
		ht=t;
	}
	public void setHelp(JTextArea help) {
		this.help = help;
	}
	
}
