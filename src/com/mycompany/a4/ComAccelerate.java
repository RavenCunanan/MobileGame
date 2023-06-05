package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ComAccelerate extends Command {
	private GameWorld gw;
	
	public ComAccelerate (GameWorld gw) {
		
		super("Accelerate");
		this.gw=gw;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.accelerate();
		System.out.println("Accelerating");
		
	}
	
}
