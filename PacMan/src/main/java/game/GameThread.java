package game;

import canvas.Draw;
import canvas.Draw_IF;
import canvas.Menu;
import controller.Controller;
import ghosts.Ghost;
import javafx.application.Platform;
import map.Map;
import player.Player;
import sounds.Sounds;

/**
 * GameThread controls the threads and checks for player status
 * 
 * @author kari-antti
 * 
 *
 */
public class GameThread extends Thread {
	private GameLogic gl;
	private Player p;
	private Controller con;
	private Ghost[] ghlist;
	private Sounds sounds;
	private Map map;
	private Draw_IF[] cavasList;

	public GameThread(Player p, Controller con,Ghost[] ghlist, Sounds sounds,Map map, Draw_IF[] cavasList) {
		this.p = p;
		this.con = con;
		this.ghlist = ghlist;
		this.sounds = sounds;
		this.map = map;
		this.cavasList = cavasList;
	}

	public void run() {
		
		gl = new GameLogic(con, p, ghlist, sounds, map, cavasList);
		gl.loadFirstMap();
		
		while (true) {
			if (gl.mapLoaded()) {
				 
				System.out.println("map loaded");
				System.out.println(map.getMap().toString());
				gl.StartGame();
				while (gl.play()) {

					gl.looselife();
					if (p.getVulnerable() == "active") {
						gl.vulnerable();
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
				break;

			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void suppress() {
		gl.suppress();

	}

}
