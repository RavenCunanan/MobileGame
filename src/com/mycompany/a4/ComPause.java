package com.mycompany.a4;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ComPause extends Command {

	private Game game;
	private Button button;
	private GameWorld gw;
	public ComPause(GameWorld gw,Game game,Button button) {
		super("Pause");
		this.gw=gw;
		this.game=game;
		this.button=button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!gw.getPause()) {
			game.pressPause();
			button.setText("Play");
		}
		else {
			game.pressPause();
			button.setText("Pause");
		}
	}
}
