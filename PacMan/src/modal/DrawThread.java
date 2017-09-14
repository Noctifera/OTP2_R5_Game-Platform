package modal;

import controller.Controller;
import javafx.application.Platform;

public class DrawThread extends Thread {
	private Draw pm;
	private Player player;
	private Controller con;

	public DrawThread(Draw pm,Player player,Controller con) {
		this.player = player;
		this.pm = pm;
		this.con = con;
	}

	public void run() {
		while (player.getLife() >0 && !pm.collectAllDots()) {
			try {
			pm.eat();
			}catch(NullPointerException e) {
				player.setPos(pm.getPlayerSpawn());
			}
			//con.setLives();
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

}
