package panel;

import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BellmDialog {
	JFileChooser csbm;
	FileReader fileReader;
	String bmpath=null;
	public BellmDialog() {
		// TODO �Զ����ɵĹ��캯�����
		csbm=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("��Ƶ�ļ�(*au,*aiff,*wav,*midi,*rfm)", "au","aiff","wav","midi","rfm");
		csbm.setFileFilter(filter);
		if (csbm.showOpenDialog(null)==JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null,"�ر�ѡ���","����",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bmpath=csbm.getSelectedFile().getAbsolutePath();
	}
	public String getBmpath() {
		return bmpath;
	}
	
}
