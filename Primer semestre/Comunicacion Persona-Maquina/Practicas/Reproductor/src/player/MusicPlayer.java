package player;


import java.io.File;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	private BasicPlayer basicPlayer = null;
	private File playing = null;
	
	public MusicPlayer(){
		basicPlayer = new BasicPlayer();
	}
	
	public void play (File file){
		try {
			basicPlayer.open(file);
			playing = file;
			basicPlayer.play();
		}
		catch (Exception e){}
	}
	
	public void stop(){
		try {
			basicPlayer.stop();
			playing = null;
		}
		catch (BasicPlayerException e){
		}
	}
	
	public File getPlaying(){
		return playing;
	}
	
	public void setVolume(double vol, double volMax){
		try{
			
			basicPlayer.setGain(vol/volMax);
		}
		catch (BasicPlayerException e){
		}
	}
}
