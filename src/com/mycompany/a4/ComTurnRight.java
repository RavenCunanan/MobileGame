package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ComTurnRight extends Command {
	private GameWorld gw;
	
	public ComTurnRight (GameWorld gw) {
		
		super("Turn Right");
		this.gw=gw;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.turnRight();
		System.out.println("Turned Right");
	}
}