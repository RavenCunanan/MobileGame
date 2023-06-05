package com.mycompany.a4;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
public class BGSound implements Runnable {
	private Media mus;
	public BGSound(String file) {
		
		try {
			
			InputStream in=Display.getInstance().getResourceAsStream(getClass(),"/"+file);
			mus=MediaManager.createMedia(in,"audio/wav",this);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void pause() {
		mus.pause();
	}
	
	public void play() {
		mus.play();
	}
	

	public void run() {
		
		mus.setTime(0);
		mus.play();
		
	}
}
