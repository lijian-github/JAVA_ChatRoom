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
		// TODO 自动生成的构造函数存根
		csjpg=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("图像文件(*jpg,*png,*gif)", "jpg","png","gif");
		csjpg.setFileFilter(filter);
		if (csjpg.showOpenDialog(null)==JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null,"关闭选择框！","警告",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bgpath=csjpg.getSelectedFile().getAbsolutePath();
	}
	public String getBgpath() {
		return bgpath;
	}
	
}
