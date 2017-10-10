package modal;

import java.awt.Point;
import java.util.ArrayList;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 * Thread that runs the players movement
 * 
 * @author markus
 * @version 1.0
 *
 */
public class PlayerThread extends Thread {
	private Player player;
	private Controller con;

	private Scene scene;

	private ArrayList<Point> path = new ArrayList<>();

	private volatile boolean supress = false;
	private int reader = 0;
	private int wait = 0;

	public PlayerThread(Player player, Controller con, Scene scene) {
		this.player = player;
		this.scene = scene;
		this.con = con;
	}

	public void run() {
		player.setPos(player.playerSpawn());
		handle();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader = 0;
		while (!supress) {
			con.setLives();
			con.setScore();
			if (reader < path.size()) {
				player.setPos(path.get(reader));
				//System.out.println("player pos: " + player.getPos());
				player.score(path.get(reader));
				reader++;
				try {
					Thread.sleep(300);
					if (wait == 1)saty();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public void supress() {
		supress = true;
	}

	public void saty() throws InterruptedException {
		Thread.sleep(500);
		wait = 0;
	}

	public void handle() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				// liikkuminen
				KeyCode code = event.getCode();
				ArrayList<Point> p = player.move(code);
				if (p.size() > 0) {
					path.clear();
					reader = 0;
					path = p;
					//System.out.println("path: "+path);
					
				}

			}
		});
	}

	public void retrunTospawn() {
		reader = 0;
		path.clear();
		player.setPos(player.playerSpawn());
		wait = 1;

	}

}
