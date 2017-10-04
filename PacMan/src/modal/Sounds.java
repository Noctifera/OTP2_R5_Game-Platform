package modal;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {

	private File beginning = new File("Sounds\\pacman_beginning.wav");
	private File dot = new File("Sounds\\wakka_wakka1.wav");
	private File largeDot = new File("Sounds\\pacman_eatfruit.wav");
	private File death = new File("Sounds\\pacman_death.wav");
	private File eatghost = new File("Sounds\\pacman_eatghost.wav");
	private File intermission = new File("Sounds\\pacman_intermission.wav");

	private MediaPlayer mediaplayer;

	public void playSound(File name) {
		try {
		mediaplayer.stop();
		}catch(NullPointerException e) {
			
		}
		Media m = new Media(name.toURI().toString());
		//System.out.println(m.getSource());
		//System.out.println(m.getDuration());
		mediaplayer = new MediaPlayer(m);
		mediaplayer.setAutoPlay(true);
	}

	public File getBeginning() {
		return beginning;
	}

	public File getDot() {
		return dot;
	}

	public File getLargeDot() {
		return largeDot;
	}

	public File getDeath() {
		return death;
	}

	public File getEatghost() {
		return eatghost;
	}

	public File getIntermission() {
		return intermission;
	}

	public MediaPlayer getMediaplayer() {
		return mediaplayer;
	}
	
}
