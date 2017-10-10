package modal;

import controller.Controller;
import javafx.application.Platform;
import javafx.scene.Scene;

public class GameThread extends Thread {
	private Player p;
	private PlayerThread playerthread;
	private DrawThread dt;
	private Controller con;
	private Scene scene;
	private Draw draw;
	private int ghostAmount = 4;
	private Ghost[] ghlist;
	private GhostThread[] ghtlist = new GhostThread[ghostAmount];
	private Sounds sounds;

	private int deactiveCount = 0;

	public GameThread(Player p, Controller con, Scene scene, Draw draw, Ghost[] ghlist, Sounds sounds) {
		this.p = p;
		this.con = con;
		this.scene = scene;
		this.draw = draw;
		this.ghlist = ghlist;
		this.sounds = sounds;
	}

	public void run() {
		p.setPos(p.playerSpawn());
		for (Ghost g : ghlist) {
			g.setPos((g.ghostHouse()));
		}

		dt = new DrawThread(draw);
		dt.start();

		try {
			Thread.sleep(4400);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		playerthread = new PlayerThread(p, con, scene);
		playerthread.start();
		for (int i = 0; i < ghtlist.length; i++) {
			ghtlist[i] = new GhostThread(ghlist[i]);

		}
		for (GhostThread ghost : ghtlist) {
			ghost.start();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while (play()) {
		
				looselife();
				if (p.getVulnerable() == "active") {
					vulnerable();
				}
				

			 
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				con.gameOver();
			}
		});
		
		suppress();

	}

	public boolean play() {
		boolean apu = false;
		if (p.getLife() > 0) {
			// System.out.println(p.getLife());
			apu = true;
		}
		return apu;
	}

	public void vulnerable() {
		//System.out.println(p.getVulnerable());
		deactiveCount++;
		if (deactiveCount == 1000) {
			p.setVulnerable("deactive");
			deactiveCount = 0;
		}
		
	}

	public void suppress() {
		System.out.println("supress");
		for (int i = 0; i < ghtlist.length; i++) {
			ghtlist[i].supress();
		}
		playerthread.supress();
		dt.supress();
	}

	public void looselife() {
		int i = 0;
		while (i < ghlist.length) {
			
			switch (p.getVulnerable()) {
			case "deactive":			
				if (ghlist[i].getPos().equals(p.getPos())) {
					sounds.playSound(sounds.getDeath());
					for (int j = 0; j < ghlist.length; j++) {
						ghtlist[j].returnToHouse();
					}
					p.getEaten();
					playerthread.retrunTospawn();

					break;
				}				
							
			case "active":
				
				if(ghlist[i].getPos().equals(p.getPos())) {
					sounds.playSound(sounds.getEatghost());
					ghtlist[i].returnToHouse();
					p.ghost();
				}
				
				break;

			default:
				break;
			}
			
			i++;
			}
		}
}
