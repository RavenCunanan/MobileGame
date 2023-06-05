package com.mycompany.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Flag extends Fixed implements IDrawable{
	
	private int sequenceNumber;
	private GameWorld gw;
	
	public Flag(int sequenceNumber,GameWorld gw) {
			super(ColorUtil.CYAN,gw);//color,size
			super.setSize(100);
			this.sequenceNumber=sequenceNumber;		
			this.gw=gw;
		}
		
	@Override
	public String toString() {
		return("Flag: loc= " + Math.round(this.getX()*10)/10 + "," + Math.round(this.getY()*10.0)/10.0+ "  color= " +
				this.getColortoString() +"  size= " + this.getSize()+ "  seqNumber= " + this.getSequenceNumber());
	}
	
	public void setColor(int color) {
		System.out.println("Color is constant, cannot change");
	}
	
	public void setSize(int size) {
		System.out.println("Size is constant, cannot change");
	}

	private int getSequenceNumber() {
		return sequenceNumber;
	}

	//@Override
	public void handleCollision(GameObject affectObject) {
		
		Command ok =new Command("Ok");
		Command conf=new Command("");
		
		if(this instanceof Flag && affectObject instanceof Ant) {
			int nFlag= (((Ant)affectObject).getLastFlagReached()+1);
			 if(nFlag==this.sequenceNumber) {
				 System.out.println("Next Flag Reached");
				 ((Ant)affectObject).setLastFlagReached(this.sequenceNumber);
			 }
			 else if(((Ant)affectObject).getLastFlagReached()==super.getGW().getFlagCount()) {
				 conf=Dialog.show("You win! "," Total time: "+super.getGW().getClock() ,ok);
				 if(conf.equals(ok))
					 System.exit(0);
			 }
			 
			 else {
				 System.out.println("Flag not yet available");
			 }			 
			 
		}
			
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		int sX=(int)Math.round(this.getX()-10)+pCmpRelPrnt.getX();
		int sY=(int)Math.round(this.getY()-20)+pCmpRelPrnt.getY();
		int x = (int)this.getX()+ pCmpRelPrnt.getX();
		int y = (int)this.getY()+ pCmpRelPrnt.getY();
		int[] xPoints = {x,(x-20),(x+20),x};
		int[] yPoints = {(y+30),(y-30),(y-30),(y+30)};
		if(super.isSelected()) {
			g.drawPolygon(xPoints, yPoints, 3);
			
		}
		else
			g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.sequenceNumber, sX, sY);
		
	}
	

}
