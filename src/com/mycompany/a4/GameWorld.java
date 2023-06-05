package com.mycompany.a4;

import java.util.Random;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point;

public class GameWorld extends Observable {
	

	public static int gameHeight =1000;
	public static int gameWidth =1000;
	private int clock;
	Random rand=new Random();
	private boolean sound;
	private boolean pause;
	private boolean position;
	private GameWorldCollection gameObj;
	private int flagCount;
	private BGSound bg;
	private Sound eat;
	private Sound flag;
	private Sound hit;
	
	
	public void init() {//create initial game
		this.clock=0;
		this.flagCount=0;
		this.sound=false;
		gameObj=new GameWorldCollection();
		position =false;
		create();
		this.setChanged();   //Observable
		this.notifyObservers(this);
		
	}
	
	public void create() {//create game objects ,now iterators
		int flagAmount=4+rand.nextInt(6);
		int spiderAmount=3+rand.nextInt(4);
		int foodStationAmount=3+rand.nextInt(3);
		
		gameObj.add(Ant.getAnt(this));

		for(int i=1;i<= flagAmount;i++) {
			gameObj.add(new Flag(i,this));
			flagCount++;
		}
		
		for(int i=1;i<=spiderAmount;i++) {
			gameObj.add(new Spider(this));
		}
		
		for(int i=1;i<=foodStationAmount;i++) {
			gameObj.add(new FoodStation(this));
		}
		
		
			
	}
	
	public void accelerate() {//'a'
		
		System.out.println("The ant accelerates.");
		IIterator itr= gameObj.getIterator();
		while(itr.hasNext()) {
			
			GameObject affectObject=itr.getNext(); //affectObject = the object that will be affected by an action
			if(affectObject instanceof Ant)
			{
				((Ant)affectObject).accelerate();
			}
			
		}
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	public void breakSpeed() {//'b'
		
		System.out.println("The ant breaks in speed.");
		IIterator itr= gameObj.getIterator();
		while(itr.hasNext()) {
			
			GameObject affectObject=itr.getNext(); 
			if(affectObject instanceof Ant)
			{
				((Ant)affectObject).brakeSpeed();
			}
			
		}
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	public void turnLeft() {//'l'
		
		System.out.println("Ant has turned left.");
		IIterator itr= gameObj.getIterator();
		while(itr.hasNext()) {
			GameObject affectObject=itr.getNext(); 
			if(affectObject instanceof Ant)
			{
				((Ant)affectObject).turnLeft();
			}
		}
		this.setChanged();
		this.notifyObservers(this);		
				
	}
	
	public void turnRight() {//'r'
		
		System.out.println("Ant has turned right.");
		IIterator itr= gameObj.getIterator();
		while(itr.hasNext()) {
			GameObject affectObject=itr.getNext(); 
			if(affectObject instanceof Ant)
			{
				((Ant)affectObject).turnRight();
			}
		}
		this.setChanged();
		this.notifyObservers(this);		
				
	}
	
	public void tick() {//'t'    tick did not work properly in A1, now trying to make ant and spider tick in a nested while instead of being in the same for loop 
		
		clock++;
		IIterator itr=gameObj.getIterator();
		while(itr.hasNext()) {
			
			GameObject affectObject=itr.getNext();
			if(affectObject instanceof Ant) {
				
				if( ((Ant)affectObject).getFoodLevel()!=0) {
					
					((Ant)affectObject).move();  //move ant
					((Ant)affectObject).consumptionTick();
					IIterator jtr=gameObj.getIterator();
					while(jtr.hasNext()) {
						
						GameObject affectObjectJ=jtr.getNext();
						if(affectObjectJ instanceof Spider) { //move spider
							((Spider)affectObjectJ).move();
							
						}
					}
				}
				else if(((Ant)affectObject).getLife()!=0) {//check if ant can move
					
					System.out.println("The Ant has died!");
					((Ant)affectObject).lostLife( ((Ant)affectObject).getX() , ((Ant)affectObject).getY() );
				
				}
				else {
					System.out.println("You lose!");
					System.exit(0);
				}
			}
		}
		checkCollision();
		
		if(isSound())
			bg.play();
		
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	public void checkCollision() {
		IIterator itr = gameObj.getIterator();
		while(itr.hasNext()) {
			
			GameObject affectObject=itr.getNext();
			IIterator jtr=gameObj.getIterator();
			while(jtr.hasNext()) {
				
				GameObject affectObject2=jtr.getNext();
				if(affectObject!=affectObject2) {
					
					if(affectObject.collidesWith(affectObject2)) {
					
					
						if(affectObject instanceof Ant && affectObject2 instanceof Spider) {
							if(isSound())
								hit.play();
						}
						
						else if(affectObject instanceof Ant && affectObject2 instanceof Flag) {
							if(isSound())
								flag.play();
						}
						
						else if(affectObject instanceof Ant && affectObject2 instanceof FoodStation) {
							if(isSound())
								eat.play();
						}
						
						affectObject.handleCollision(affectObject2);
					}
				}
			}
		}
	}
		
	public void exit() {//'x'and'y'
		
		System.exit(0);	
	
	}
	
	public GameWorldCollection getGWCollection() { 
		return gameObj;
	}
	
	public int getLastFlagReached() {
		IIterator itr=gameObj.getIterator();
		while(itr.hasNext()) {
			
			GameObject affectObject=itr.getNext();
			if(affectObject instanceof Ant) {
				
				return((Ant)affectObject).getLastFlagReached();
				
			}
			
		}
		
		return 0;
	}
	
	public int getFoodLevel() {
		IIterator itr=gameObj.getIterator();
		while(itr.hasNext()) {
			
			GameObject affectObject=itr.getNext();
			if(affectObject instanceof Ant) {
				
				return((Ant)affectObject).getFoodLevel();
				
			}
			
		}
		return 0;
	}
	
	public int getHealthLevel() {
		IIterator itr=gameObj.getIterator();
		while(itr.hasNext()) {
			
			GameObject affectObject=itr.getNext();
			if(affectObject instanceof Ant) {
				
				return((Ant)affectObject).getHealthLevel();
				
			}
			
		}
		return 0;
	}
	
	public int getLife() {
		IIterator itr=gameObj.getIterator();
		while(itr.hasNext()) {
			
			GameObject affectObject=itr.getNext();
			if(affectObject instanceof Ant) {
				
				return((Ant)affectObject).getLife();
				
			}
			
		}
		
		return 0;
	}
	
	public void click(Point pressPoint, Point oldPoint) {
		
		IIterator itr=gameObj.getIterator();
		while(itr.hasNext()) {
			if(itr.getNext() instanceof Fixed) {
				
				Fixed fObj = (Fixed)itr.getCurrent();
				if(position && fObj.isSelected()) {
					
					int posX = pressPoint.getX()-oldPoint.getX();
					int posY = pressPoint.getY()-oldPoint.getY();
					fObj.setLocation(posX, posY);
					position = false;
					fObj.setSelected(false);
					
				}
				else {
					if(fObj.contains(pressPoint, oldPoint))
						fObj.setSelected(true);
					else
						fObj.setSelected(false);
				}				
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	
	public void postionStatus() {
		if (position ==true)
			position=false;
		else
			position = true;
	}
	
	public void offPosition() {
		position=false;
	}
	
	public void createSound() {
		
		bg=new BGSound("bg.wav");
		eat=new Sound("eat.wav");
		flag=new Sound("flag.wav");
		hit=new Sound("Hit.wav");
		
	}

	public int getClock() {
		return this.clock/100;
	}

	public void setClock(int clock) {
		this.clock = clock;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
		this.setChanged();
		this.notifyObservers(this);		
	}
	
	public boolean getSound() {
		return sound;
	}
	
	public void turnOffSound() {
		bg.pause();
	}
	
	public void turnOnSound() {
		if(getSound()) {
			bg.play();
		}
	}
	
	public boolean getPause() {
		return pause;
	}
	
	public void setPause(boolean pauseX) {
		pause=pauseX;
	}

	public int getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(int flagCount) {
		this.flagCount = flagCount;
	}
	
	public void addFood() {
		gameObj.add(new FoodStation(this));
	}

	public static int getGameHeight() {
		return gameHeight;
	}

	public static int getGameWidth() {
		return gameWidth;
	}

	public static void setGameWidth(int gameWidth) {
		GameWorld.gameWidth = gameWidth;
	}

	public static void setGameHeight(int gameHeight) {
		GameWorld.gameHeight = gameHeight;
	}
	
}