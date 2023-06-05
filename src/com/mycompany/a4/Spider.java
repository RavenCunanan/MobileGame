package com.mycompany.a4;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Spider extends Moveable implements IDrawable{
	Random rand=new Random();
	
	public Spider(GameWorld gw) {
		super(ColorUtil.BLACK,gw);
		int minSize=10;
		int maxSize=50;
		super.setSize(rand.nextInt(maxSize-minSize)+minSize);
		super.setHeading(rand.nextInt(360));
		super.setSpeed(rand.nextInt(20)+10);

	}
		
	public int getSign() {
		if(rand.nextInt(2)==1) {
			return 1;
		}
		else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return ("Spider: loc= "+ Math.round(this.getX()* 10.0)/ 10.0  +","+ Math.round(this.getY()*10.0)/10.0 + "  color= " + this.getColortoString() + 
				"  heading=" + this.getHeading()+ "  speed= " + this.getSpeed()+ "  size= " + this.getSize()); 
		
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(super.getColor());
		int x = (int)this.getX()+ pCmpRelPrnt.getX();
		int y = (int)this.getY()+ pCmpRelPrnt.getY();
		int[] xPoints = {x,(x-20),(x+20),x};
		int[] yPoints = {(y+30),(y-30),(y-30),(y+30)};
		g.drawPolygon(xPoints, yPoints, 3);
		
	}
	
	@Override
	public void handleCollision(GameObject affectObject) {
		
		if(this instanceof Spider && affectObject instanceof Ant) {
			((Ant)affectObject).setHealthLevel(((Ant)affectObject).getHealthLevel()-1);//damage tick 1
			if( ((Ant)affectObject).getHealthLevel() <=9 && ((Ant)affectObject).getColor()!=ColorUtil.GRAY ) { //color change when hit and when not already gray
				
				((Ant)affectObject).setColor(ColorUtil.GRAY);
				
			}
			if(((Ant)affectObject).hasHealth()==false){
				System.out.println("The Ant has died");
				if(((Ant)affectObject).getLife() != 0) {
					
					((Ant)affectObject).lostLife( ((Ant)affectObject).getX() , ((Ant)affectObject).getY() );
					
				}
				else {
					
					System.out.println("Game over,you failed!");
					System.exit(0);
					
				}
			}
		}
		
	}
	


}
