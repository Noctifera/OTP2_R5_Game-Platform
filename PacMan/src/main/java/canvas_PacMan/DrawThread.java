package canvas_PacMan;

import javafx.application.Platform;

/**
 * Extends Thread, DrawThread runs draw class
 * 
 * @author kari-antti
 *
 */

public class DrawThread extends Thread {
	private Game dc;

	private volatile boolean supress = false;

	public DrawThread(Game dc) {
		this.dc = dc;
	}

	public void run() {
		
		while (!supress) {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dc.update();
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
	public void supress() {
		supress = true;
	}

}
