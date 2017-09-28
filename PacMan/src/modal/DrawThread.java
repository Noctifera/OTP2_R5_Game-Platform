package modal;

import javafx.application.Platform;

public class DrawThread extends Thread {
	private Draw draw;

	private volatile boolean supress = false;

	public DrawThread(Draw draw) {
		this.draw = draw;
	}

	public void run() {
		while (!supress) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					draw.update();
				}

			});

			try {
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void supress() {
		supress = true;
	}

}
