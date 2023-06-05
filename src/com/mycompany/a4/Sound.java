package com.mycompany.a4;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {

	private Media mus;
	public Sound(String file) {
		
		try {
			
			InputStream in=Display.getInstance().getResourceAsStream(getClass(),"/"+file);
			mus=MediaManager.createMedia(in,"audio/wav");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		
		mus.setTime(0);
		mus.play();
		
	}
	
}
