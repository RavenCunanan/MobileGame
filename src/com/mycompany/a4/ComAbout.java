package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ComAbout extends Command {
	public ComAbout () {
		
		super("About");
		
	}
	
	@Override
    public void actionPerformed(ActionEvent e)
    {
		String about=("Raven Cunanan\n CSC 133\n \n Version 1.0 ");
        Dialog.show("About",about,"OK",null );
    }
}
