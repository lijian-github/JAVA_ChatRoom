package model;

import javax.swing.AbstractListModel;

public class ListDataModel extends AbstractListModel{
	
	String []mlist;

	public void setMlist(String[] mlist) {
		this.mlist = mlist;
	}

	@Override
	public Object getElementAt(int index) {
		// TODO �Զ����ɵķ������
		return mlist[index-1];
	}

	@Override
	public int getSize() {
		// TODO �Զ����ɵķ������
		return 0;
	}

}
