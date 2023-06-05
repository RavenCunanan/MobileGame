package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ComTurnLeft extends Command {
	private GameWorld gw;
	
	public ComTurnLeft (GameWorld gw) {
		
		super("Turn Left");
		this.gw=gw;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.turnLeft();
		System.out.println("Turned Left");
	}
}
