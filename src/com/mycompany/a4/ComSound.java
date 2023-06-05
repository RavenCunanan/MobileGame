package com.mycompany.a4;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ComSound extends Command {
private GameWorld gw;
	
	public ComSound (GameWorld gw) {
		
		super("Sound On/OFF");
		this.gw=gw;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((CheckBox) e.getComponent()).isSelected()) {
			gw.setSound(true);
		}
		else {
			gw.setSound(false);
		}
	}
}
