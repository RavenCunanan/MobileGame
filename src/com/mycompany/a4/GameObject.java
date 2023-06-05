package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;
import java.util.Random;

public abstract class GameObject implements ICollider {
	
	private int color;
	private int size;
    private Point location;
	private GameWorld gw;
	private ArrayList<GameObject> collide;
	Random rand=new Random();
	
	
	public GameObject(int color,GameWorld nGW) {
		
		this.color=color;
		this.size=rand.nextInt(50)+10;
		float x=(float)rand.nextDouble()*1001;
		float y=(float)rand.nextDouble()*1001;
		this.gw = nGW;
		this.location= new Point(x,y);	
		collide = new ArrayList <GameObject>();
	}
	
	public GameObject(int color, int size,GameWorld nGW) {
		
		this.color=color;
		this.size=size;
		float x=(float)rand.nextDouble()*1001;
		float y=(float)rand.nextDouble()*1001;
		this.gw = nGW;
		this.location= new Point(x,y);	
		collide = new ArrayList <GameObject>();
		
	}
	
	@Override
	public boolean collidesWith(GameObject go) {
		
		boolean collision =false;
		double cenX =this.getX()+(go.size/2);
		double cenY =this.getY()+(go.size/2);
		double goX=go.getX();
		double goY=go.getY();
		double newX=cenX-goX;
		double newY=cenY-goY;
		double distance=(newX*newX + newY*newY);
		int rad=this.getSize()/2;
		int goRad=go.getSize()/2;
		int sqr=(rad*rad+2*rad*goRad+goRad*goRad);
		if(distance<=sqr) {
			
			collision =true;
		}
		
		return collision;
		
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(float x,float y) {
		this.location = new Point(x,y);
	}

	public float getX() {
		return location.getX();
	}

	public void setX(float x) {
		location.setX(x);
	}

	public float getY() {
		return location.getY();
	}

	public void setY(float y) {
		location.setY(y);
	}
	
	public String getColortoString(){
		return "["+ ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color)+"]";
	}
	
	public GameWorld getGW() {
		return gw;
	}
	


	
}
