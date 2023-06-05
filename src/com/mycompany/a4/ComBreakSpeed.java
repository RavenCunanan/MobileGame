package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ComBreakSpeed extends Command {
private GameWorld gw;
	
	public ComBreakSpeed (GameWorld gw) {
		
		super("Break");
		this.gw=gw;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.breakSpeed();
		System.out.println("BreakingSpeed");
		
	}
	

}
