package com.mycompany.a4;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Fixed extends GameObject implements ISelectable {

	private boolean selected;
	
	public Fixed(int color,GameWorld gw) {
		super(color,gw);
	}
	
	public Fixed(int color,int size,GameWorld gw) {
		super(color,size,gw);
	}


	public void setSelected(boolean select) {
		selected=select;
	}


	public boolean isSelected() {
		return selected;
	}


	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrint) {
		
		int radius =super.getSize()/2;
		int left = (int)Math.round(super.getX()-radius+pCmpRelPrint.getX());
		int top = (int)Math.round(super.getY()-radius+pCmpRelPrint.getY());
		int right = left + super.getSize();
		int bottom = top + super.getSize();
		boolean lr = pPtrRelPrnt.getX() > left && pPtrRelPrnt.getX() < right;
		boolean tb = pPtrRelPrnt.getY() > top && pPtrRelPrnt.getY() < bottom;
		return lr && tb;
		
	}

	@Override
	public void handleCollision(GameObject go) {

		
	}


}
