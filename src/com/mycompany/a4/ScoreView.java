package com.mycompany.a4;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;

public class ScoreView extends Container implements Observer {
	private Label clock;
	private Label life;
	private Label lastFlagReached;
	private Label foodLevel;
	private Label health;
	private Label sound;
	private GameWorld gw;
	
	public ScoreView() {

		setLayout();
		setLClock();
		setLLife();
		setLLastFlagReached();
		setLFoodLevel();
		setLHealth();
		setLSound();
	}
	
	public void setLayout() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
	}
	
	public void setLClock()
	{
		Label clockS=new Label("Time: ");
		clockS.getAllStyles().setFgColor(ColorUtil.BLUE);
		clockS.getAllStyles().setPadding(1,1,30,1);
		clock=new Label("0 ");
		clock.getAllStyles().setFgColor(ColorUtil.BLUE);
		clock.getAllStyles().setPadding(1,1,1,2);
		this.add(clockS);
		this.add(clock);
		
	}
	
	public void setLLife()
	{
		Label lifeS=new Label("Lives Left: ");
		lifeS.getAllStyles().setFgColor(ColorUtil.BLUE);
	    lifeS.getAllStyles().setPadding(1,1,1,1);
		life=new Label("0 ");
		life.getAllStyles().setFgColor(ColorUtil.BLUE);
        life.getAllStyles().setPadding(RIGHT,2);
		this.add(lifeS);
		this.add(life);
	}
	
	public void setLLastFlagReached()
	{
		Label flagS=new Label("Last Flag Reached: ");
		flagS.getAllStyles().setFgColor(ColorUtil.BLUE);
		flagS.getAllStyles().setPadding(1,1,1,1);
		lastFlagReached=new Label("0 ");
		lastFlagReached.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagReached.getAllStyles().setPadding(RIGHT,2);
		this.add(flagS);
		this.add(lastFlagReached);
	}
	
	public void setLFoodLevel()
	{
		Label foodS=new Label("Food Level:");
		foodS.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodS.getAllStyles().setPadding(1,1,1,1);
		foodLevel=new Label("0");
		foodLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevel.getAllStyles().setPadding(RIGHT,2);
		this.add(foodS);
		this.add(foodLevel);
	}
	
	public void setLHealth()
	{
		Label healthS=new Label("Health Level:");
		healthS.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthS.getAllStyles().setPadding(1,1,1,1);
		health=new Label("0");
		health.getAllStyles().setFgColor(ColorUtil.BLUE);
	    health.getAllStyles().setPadding(RIGHT,2);
		this.add(healthS);
		this.add(health);
	}
	
	public void setLSound()
	{
		Label soundS=new Label("Sound:");
		soundS.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundS.getAllStyles().setPadding(1,1,1,1);
		sound=new Label("OFF ");
		sound.getAllStyles().setFgColor(ColorUtil.BLUE);
	    sound.getAllStyles().setPadding(RIGHT,5);
		this.add(soundS);
		this.add(sound);
	}
	
			
	@Override
	public void update(Observable observable, Object data) {
		
		gw=(GameWorld) data;
     	this.life.setText(" "+gw.getLife());
		this.clock.setText(" "+gw.getClock());		
		this.lastFlagReached.setText(" "+gw.getLastFlagReached());
		this.foodLevel.setText(" "+gw.getFoodLevel());
		this.clock.setText(" "+gw.getClock());
		this.health.setText(" "+gw.getHealthLevel());
		if(gw.isSound()) {
			
			this.sound.setText("ON ");
			
		}
		else {
			this.sound.setText("OFF ");
		}
		this.repaint();
	}

}
