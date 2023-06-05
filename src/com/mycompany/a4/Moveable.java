package com.mycompany.a4;

public class Moveable extends GameObject {

	private int heading;
	private int speed;
	
	public Moveable(int color,GameWorld gw) {
		super(color,gw);
	}
	
	public Moveable(int color,int size,GameWorld gw) {
		super(color,size,gw);
	}
	
	public int getHeading() {
		return heading;
	}
	
	public void setHeading(int headingX) {
		while(headingX>=360){
			headingX-=360;
		}
		while(headingX<0) {
			headingX += 360;
		}
		heading=headingX;
		
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void move() {
		
		float newX = this.getX()+(float)Math.cos(Math.toRadians(90-this.heading))*speed;
		float newY = this.getY()+(float)Math.sin(Math.toRadians(90-this.heading))*speed;
		int offset=0;
		
		if(this instanceof Spider) {
			offset=50;
		}
		else if(this instanceof Ant) {
			offset=50;
		}
		
		int ogX=(int)MapView.getMVOrigin().getX();
		int ogY=(int)MapView.getMVOrigin().getY();
		
		if(ogX+newX+offset >= MapView.getMapViewWidth()+ogX &&(heading !=0||heading!=180)) {
			
			setHeading(360-heading);
			
		}
		
		if(this instanceof Spider) {
			if(ogX + newX <= ogX+20 &&(heading!=0||heading!=180)) {
				setHeading(360-heading);
			}
		}
		else {
			if(ogX+newX<=ogX && (heading!=0||heading!=180)) {
				setHeading(360-heading);
			}
		}
		if(ogY+newY+offset >= ogY+GameWorld.getGameHeight() ) {
			setHeading((360-heading+180)%360);
			
		}
		if(this instanceof Spider) {
			if(ogY+newY<=ogY+25) {
				setHeading((360-heading+180)%360);
			}
		}
		else {
			if(ogY+newY <= ogY) {
				setHeading((360-heading+180)%360);
			}
		}
		this.setLocation(newX, newY);
	}

	@Override
	public void handleCollision(GameObject go) {
		// TODO Auto-generated method stub
		
	}


	
}
