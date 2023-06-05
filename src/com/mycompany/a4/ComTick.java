package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ComTick extends Command  {
private GameWorld gw;
	
	public ComTick (GameWorld gw) {
		
		super("Tick");
		this.gw=gw;
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.tick();
		System.out.println("Ticked");
	}

}
