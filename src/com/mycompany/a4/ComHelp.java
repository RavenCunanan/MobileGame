package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ComHelp extends Command {

	public ComHelp() {
		super("Help");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String text=("a: accelerate \n b: break \n l: left turn \n r:\right turn \n f: collide with food station \n g: collide with spider \n t: tick \n ");
		Dialog.show("Help",text,"OK",null);
	}

	
}
