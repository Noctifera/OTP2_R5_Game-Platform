package sounds_PacMan;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * controls the sounds during games
 * @author markus
 * @version 1.0
 *
 */
public class Sounds {
	/**
	 * file path for the music at the start of the game
	 */
	private final static  File beginning = new File("Sounds\\pacman_beginning.wav");
	/**
	 * file path for sound when a dots eaten
	 */
	private final static File dot = new File("Sounds\\wakka_wakka1.wav");
	/**
	 * file path for sound when a large dots eaten
	 */
	private final static File largeDot = new File("Sounds\\pacman_eatfruit.wav");
	/**
	 * file path for sound when player dies
	 */
	private final static File death = new File("Sounds\\pacman_death.wav");
	/**
	 * file path for sound when ghost gets eaten
	 */
	private final static File eatghost = new File("Sounds\\pacman_eatghost.wav");
	/**
	 * never used
	 */
	private final static File intermission = new File("Sounds\\pacman_intermission.wav");
/**
 * the media player used
 */
	private static MediaPlayer mediaplayer;
/**
 * 
 * @param name one of the file paths used in this class to play the sound
 */
	public static void playSound(File name) {
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

	public static File getBeginning() {
		return beginning;
	}

	public static File getDot() {
		return dot;
	}

	public static File getLargeDot() {
		return largeDot;
	}

	public static File getDeath() {
		return death;
	}

	public static File getEatghost() {
		return eatghost;
	}

	public static File getIntermission() {
		return intermission;
	}

	public static MediaPlayer getMediaplayer() {
		return mediaplayer;
	}
	
}
