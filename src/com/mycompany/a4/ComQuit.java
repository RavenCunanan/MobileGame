package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ComQuit extends Command {

		public ComQuit() {
			super("Quit");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Command yes=new Command("Yes ");
			Command no=new Command("No ");
			Command []coms=new Command[] {yes,no};
			Command check=Dialog.show("Quit ", "Are you sure you want to quit?",coms);
			if(check==yes) {
				System.exit(0);
			}
		} 
}
