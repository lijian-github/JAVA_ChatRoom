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
		// TODO 自动生成的构造函数存根
		csbm=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("音频文件(*au,*aiff,*wav,*midi,*rfm)", "au","aiff","wav","midi","rfm");
		csbm.setFileFilter(filter);
		if (csbm.showOpenDialog(null)==JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null,"关闭选择框！","警告",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bmpath=csbm.getSelectedFile().getAbsolutePath();
	}
	public String getBmpath() {
		return bmpath;
	}
	
}
