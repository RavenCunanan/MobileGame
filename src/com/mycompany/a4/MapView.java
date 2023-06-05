package com.mycompany.a4;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.*;
import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;


public class MapView extends Container implements Observer {
	private static int height;
	private static int width;
	private GameWorld gw;
	static private Point mvOrigin;
	private Transform theVTM,worldToND,ndToDisplay ;
	private float winLeft,winBottom,winTop,winRight;
	
	public MapView(){
		MapView.height=this.getHeight();
		MapView.width=this.getWidth();
		
		
		winLeft=0;
		winBottom=0;
		winRight=931/2;
		winTop=639/2;
		
		
		this.getAllStyles().setBorder(Border.createLineBorder(10,ColorUtil.rgb(255,0,0))); //red rectangle
		this.setLayout(new BorderLayout());
		this.getAllStyles().setBgColor(ColorUtil.rgb(255,255,255)); //white
		this.getAllStyles().setBgTransparency(255);
	}
	
	@Override
	public void update(Observable observable, Object data) {
		
		gw = (GameWorld) data;
		this.repaint();
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		float winWidth = winRight - winLeft;
		float winHeight = winTop - winBottom;
		Graphics g2 = (Graphics) g;
		worldToND=buildWorldToNDXform(winWidth, winHeight, winLeft, winBottom);
		ndToDisplay = buildNDToDisplayXform(this.getWidth(), this.getHeight());
		
		theVTM =  ndToDisplay.copy();
		theVTM.concatenate(worldToND);
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		gXform.translate(getAbsoluteX(),getAbsoluteY());
		gXform.concatenate(theVTM);
		gXform.translate(-getAbsoluteX(),-getAbsoluteY());
		
		
		
		Point pCmpRelPrnt = new Point(this.getX(),this.getY());
		Point pCmpRelScrn = new Point(getAbsoluteX(),getAbsoluteY());
		
		
		IIterator itr =gw.getGWCollection().getIterator();
		while(itr.hasNext()) {
			
			GameObject obj =itr.getNext();
			if(obj instanceof IDrawable) {
				((IDrawable) obj).draw(g,pCmpRelPrnt);
			}
			
		}
		g.resetAffine();
		
	}
	
	private Transform buildWorldToNDXform(float winWidth, float winHeight, float winLeft, float winBottom) {
		
		Transform trn=Transform.makeIdentity();
		trn.scale( (1/winWidth) , (1/winHeight) );
		trn.translate(-winLeft,-winBottom);
		return trn;		
		
	}
	
	private Transform buildNDToDisplayXform (float displayWidth, float displayHeight){ 
		
		Transform trn=Transform.makeIdentity();
		trn.translate(0, displayHeight);
		trn.scale(displayWidth, -displayHeight);
		return trn;
	}

	@Override
	public void pointerPressed(int x, int y) {
		Point pressPoint =new Point(x - getParent().getAbsoluteX(), y - getParent().getAbsoluteY());
		Point oldPoint = new Point(getX(),getY());
		if(gw.getPause()) {
			gw.click(pressPoint,oldPoint);
		}
	}

	public static int getMapViewHeight() {
		return height;
	}

	public static void setMapViewHeight(int height) {
		MapView.height = height;
	}

	public static int getMapViewWidth() {
		return width;
	}

	public static void setMapViewWidth(int width) {
		MapView.width = width;
	}
	
	public void setMVOrgin(Point p) {
		MapView.mvOrigin=p;
	}
	
	public static Point getMVOrigin() {
		return mvOrigin;
	}
	
	
	


}
