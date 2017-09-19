package modal;

import javafx.application.Platform;

public class DrawThread extends Thread {
	private Draw draw;

	public DrawThread(Draw draw) {
		this.draw = draw;
	}

	public void run() {
		while (draw.keepPlaying()) {
			try {
			draw.eat();
			}catch(NullPointerException e) {
				draw.playerPos();
			}
			//con.setLives();
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// pm.clear();
					draw.update();
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

}
