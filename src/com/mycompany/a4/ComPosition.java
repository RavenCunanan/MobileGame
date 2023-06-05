package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Dialog;

public class ComPosition extends Command {

	private GameWorld gw;
	public ComPosition(GameWorld gw) {
		super("Position");
		this.gw=gw;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gw.getPause()) {
			gw.postionStatus();
		}
	}

}
