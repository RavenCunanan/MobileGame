package com.mycompany.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Ant extends Moveable implements ISteerable , IDrawable {
	private int maximumSpeed;
	private int foodLevel; // how hungry, 0=can't move
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private int life;
	private int steer;
	private static Ant ant;
	
	private Ant(GameWorld gw) {
		super(ColorUtil.YELLOW,50,gw);//color,size
		super.setSpeed(10);
		super.setHeading(0);
		this.setSteer(0);
		this.maximumSpeed=50;
		this.foodLevel=2000;
		this.foodConsumptionRate=1;
		this.healthLevel=10;
		this.lastFlagReached=0;
		this.life=3;
		
	}
	
	public static Ant getAnt(GameWorld gw) { //insures one ant
		if(ant==null) {
			ant= new Ant(gw);
		}
		return ant;
		
	}
	
	public void accelerate() {  //'a'
		if (this.getSpeed() >= maximumSpeed){
			System.out.println("Max speed already achived, will not speed further.");
		}
		else {
			this.setSpeed(this.getSpeed()+1);
		}
	}
	
	public void brakeSpeed() {  //'b'
		if(getSpeed()>0) {
			this.setSpeed(this.getSpeed()-1);
		}
		else {
			System.out.println("Cannot slow down any further.");
		}
	}
	@Override
	public void turnLeft() {//'l'
		
		this.setSteer(this.getSteer()+20);
		this.setHeading(this.getSteer()+this.getHeading());
		this.setSteer(0);
	}
	@Override
	public void turnRight() {//'r'
		this.setSteer(this.getSteer()-20);
		this.setHeading(this.getSteer()+this.getHeading());
		this.setSteer(0);
	}
	
	public boolean hasHealth() {//check if ant still alive
		if(this.getHealthLevel()==0) {
			return false;
		}else {
			return true;
		}
	}
	
	public void damage() {//collision with spider
		this.healthLevel--;
	}
	
	public void checkSpeed() {//max speed percentage is affected by health
		if(this.healthLevel<10) {
			if(this.getSpeed()<this.getMaximumSpeed()) {
				this.setMaximumSpeed((int) (50-(50*((10-healthLevel)*.1))));//remember 50 is max speed and 10 is max health
			}
			else {
				this.setSpeed(this.getMaximumSpeed());
			}
		}
		else if(healthLevel==0) {//ant is dead
			this.setSpeed(0);
		}
	}
	
	public void consumptionTick() {// make food level down and make sure ant can't move at level 0
		this.setFoodLevel(this.getFoodLevel()-this.getFoodConsumptionRate());
		if(this.foodLevel==0) {
			this.setSpeed(0);
		}
	}
	
	public void lostLife(float x, float y) { //reset ant when lost life;//color,size
		this.setX(x);
		this.setY(y);
		this.setColor(ColorUtil.YELLOW);
		super.setHeading(0);
		this.setSteer(0);
		this.maximumSpeed=50;
		this.foodLevel=2000;
		this.foodConsumptionRate=1;
		this.healthLevel=10;
		this.life--;
		this.setSpeed(10);
	}
	
	@Override
	public String toString() {
		return("Ant: loc= "+Math.round(this.getX()* 10.0)/ 10.0  +","+ Math.round(this.getY()*10.0)/10.0+ "  color= " + this.getColortoString()+
				"  heading= " + this.getHeading()+ "  speed= " + this.getSpeed()+  "  size= " + this.getSize()+ "  maxSpeed=" + this.getMaximumSpeed()+
				" food consumption rate="+this.getFoodConsumptionRate()+"  foodLevel=" + this.getFoodLevel()+ "  healthLevel=" + 
				 this.getFoodLevel()+" last flag reached= "+this.getLastFlagReached());
	}
			
	//setters and getters
	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(int foodLevel) {//helps with 'f'
		this.foodLevel = foodLevel;
	}

	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}

	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}

	public int getHealthLevel() {
		return healthLevel;
	}

	public void setHealthLevel(int healthLevel) {//helps with 'g'
		this.healthLevel = healthLevel;
	}

	public int getLastFlagReached() {
		return lastFlagReached;
	}

	public void setLastFlagReached(int lastFlagReached) { //helps with'1-9'
		this.lastFlagReached = lastFlagReached;
	}
	
	public void setLife(int life)
	{
		this.life = life;
	}
	
	public int getLife() {
		return life;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(super.getColor());
		int xPos = (int)this.getLocation().getX()+pCmpRelPrnt.getX();
		int yPos = (int)this.getLocation().getY()+pCmpRelPrnt.getY();
		g.fillArc(xPos, yPos, this.getSize(), this.getSize(), 0, 360);
		
	}
	
	public void HandleCollision(GameObject go) {
			
			
	}

	public int getSteer() {
		return steer;
	}

	public void setSteer(int steer) {
		this.steer = steer;
	}

	
	
}
