package model;

import javax.swing.AbstractListModel;

public class ListDataModel extends AbstractListModel{
	
	String []mlist;

	public void setMlist(String[] mlist) {
		this.mlist = mlist;
	}

	@Override
	public Object getElementAt(int index) {
		// TODO 自动生成的方法存根
		return mlist[index-1];
	}

	@Override
	public int getSize() {
		// TODO 自动生成的方法存根
		return 0;
	}

}
