package modal;

import javafx.application.Platform;

public class DrawThread extends Thread {
	Draw pm;
	private volatile boolean supress =  false;

	public DrawThread(Draw pm) {
		this.pm = pm;
	}

	public void run() {
		while (!supress) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// pm.clear();
					pm.update();
				}

			});

			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	public void supress(){
		supress = true;
	}

}
