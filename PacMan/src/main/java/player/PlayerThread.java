package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;

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

	private List<Point> path = new ArrayList<>();

	private volatile boolean supress = false;
	private int reader = 0;
	private int wait = 0;

	public PlayerThread(Player player, Controller con) {
		this.player = player;
		this.con = con;
	}

	public void run() {
		System.out.println("pt start");
		player.setPos(player.playerSpawn());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!supress) {
			con.setLives();
			con.setScore();
			getPlayerPath();
			if (reader < path.size()) {
				player.setPos(path.get(reader));
				 System.out.println("player pos: " + player.getPos());
				player.score(path.get(reader));
				reader++;
				try {
					Thread.sleep(300);
					if (wait == 1)
						saty();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	private void getPlayerPath() {
		if(path != player.getPath()) {
			path = player.getPath();
			reader = 0;
		}
		
	}
	
	

	public void supress() {
		supress = true;
	}

	public void saty() throws InterruptedException {
		Thread.sleep(500);
		wait = 0;
	}

	public void retrunTospawn() {
		reader = 0;
		path.clear();
		player.setPos(player.playerSpawn());
		wait = 1;

	}

	public List<Point> getPath() {
		return path;
	}

	public void setPath(ArrayList<Point> path) {
		this.path = path;
	}

	

}
