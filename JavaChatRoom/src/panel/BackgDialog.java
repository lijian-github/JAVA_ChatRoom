package panel;

import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BackgDialog {
	JFileChooser csjpg;
	FileReader fileReader;
	String bgpath=null;
	public BackgDialog() {
		// TODO �Զ����ɵĹ��캯�����
		csjpg=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("ͼ���ļ�(*jpg,*png,*gif)", "jpg","png","gif");
		csjpg.setFileFilter(filter);
		if (csjpg.showOpenDialog(null)==JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null,"�ر�ѡ���","����",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bgpath=csjpg.getSelectedFile().getAbsolutePath();
	}
	public String getBgpath() {
		return bgpath;
	}
	
}
