package com.mycompany.a4;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;

public class FoodStation extends Fixed implements IDrawable {

	Random rand=new Random();
	private int capacity;
	private GameWorld gw;
	
	public FoodStation(GameWorld gw) {
		
		super(ColorUtil.GREEN,gw);
		super.setSize(rand.nextInt(25)+75);//
		this.capacity=this.getSize();
		this.gw=gw;
		
	}
	
	@Override
	public String toString() {
		return("FoodStation: loc= " + Math.round(this.getX()*10)/10 +"," + Math.round(this.getY()*10.0)/10.0+ "  color= " +
				this.getColortoString() +"  size= " + this.getSize()+"  capacity= " + this.getCapacity());
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public boolean checkFoodStation() {
		if (capacity == 0)
			return false;
		else
			return true;
	}


	@Override
	public void handleCollision(GameObject affectObject) {
		if(this instanceof FoodStation && affectObject instanceof Ant) {
			if(this.getCapacity() !=0) { //check if food station is not empty
				
				((Ant)affectObject).setFoodLevel( this.getCapacity() + ((Ant)affectObject).getFoodLevel());
				this.setCapacity(0);
				this.setColor(ColorUtil.BLACK);
				super.getGW().addFood();
			}
		}
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(super.getColor());
		int x = (int)this.getX()+(int)pCmpRelPrnt.getX();
		int y = (int)this.getY()+(int)pCmpRelPrnt.getY();
		int sX=(int)Math.round(this.getX()+25)+pCmpRelPrnt.getX();
		int sY=(int)Math.round(this.getY()+20)+pCmpRelPrnt.getY();
		if(super.isSelected())
			g.drawRect(x,y,this.getSize(),this.getSize());
		else
			g.fillRect(x,y,this.getSize(),this.getSize());
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.capacity, sX, sY);
		
	}
}
