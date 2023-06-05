package com.mycompany.a4;

import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;

public interface ISelectable {
	
	public void setSelected(boolean select);
	public boolean isSelected();
	public boolean contains(Point pPtrRelPrnt,Point pCmpRelPrint);
	
}
