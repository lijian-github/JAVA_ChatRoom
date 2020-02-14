package panel;

import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileDialog {
	JFileChooser csfile;
	FileReader fileReader;
	String flpath=null;
	String flname;
	public FileDialog() {
		// TODO �Զ����ɵĹ��캯�����
		csfile=new JFileChooser();
		if (csfile.showOpenDialog(null)==JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null,"�ر�ѡ���","����",JOptionPane.WARNING_MESSAGE);
			return;
		}
		File file=csfile.getSelectedFile();
		flpath=file.getAbsolutePath();
		flname = file.getName();
	}
	public String getFlpath() {
		return flpath;
	}
	public String getFlname() {
		return flname;
	}
	
}