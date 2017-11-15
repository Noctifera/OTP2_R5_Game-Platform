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
	private Draw_IF draw;
	private GameLogic gl;

	private volatile boolean supress = false;

	public DrawThread(Draw_IF draw,GameLogic gl) {
		this.draw = draw;
		this.gl = gl;
	}

	public void run() {
		draw.handle(this);
		draw.front();
		while (!supress) {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					draw.update();
				}
			});
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		draw.back();
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
