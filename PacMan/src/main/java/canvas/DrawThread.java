package canvas;

import game.GameLogic;
import javafx.application.Platform;

/**
 * Extends Thread, DrawThread runs draw class
 * 
 * @author kari-antti
 *
 */

public class DrawThread extends Thread {
	private ComCanvas cc;
	private GameLogic gl;

	private volatile boolean supress = false;

	public DrawThread(ComCanvas cc,GameLogic gl) {
		this.cc = cc;
		this.gl = gl;
	}

	public void run() {
		
		while (!supress) {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					cc.update();
				}
			});
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void openMenu() {
		gl.openMenu();
	}
	public void runGame() {
		gl.resumegame();
	}

	public void supress() {
		supress = true;
	}

}
