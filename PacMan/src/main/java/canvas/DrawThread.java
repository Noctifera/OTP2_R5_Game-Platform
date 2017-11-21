package canvas;

import javafx.application.Platform;

/**
 * Extends Thread, DrawThread runs draw class
 * 
 * @author kari-antti
 *
 */

public class DrawThread extends Thread {
	private CanvasController cc;

	private volatile boolean supress = false;

	public DrawThread(CanvasController cc) {
		this.cc = cc;
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
				Thread.sleep(200);
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
