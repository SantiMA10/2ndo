package player;

import java.io.*;

public class MiFile {
	private File f;
	
	public MiFile (File f){
		this.f = f;
	}
	
	public File getF() {
		return f;
	}

	public String toString() {
		return f.getName().replaceAll(".mp3", "");
	}
}
