package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


/**
 * Thread that runs the players movement
 * 
 * @author markus
 * @version 1.0
 *
 */
public class PlayerThread extends Thread {
	private Player player;

	private List<Point> path = new ArrayList<>();

	private volatile boolean supress = false;
	private int reader = 0;

	public PlayerThread(Player player) {
		this.player = player;
	}

	public void run() {
		//System.out.println("pt start");
		//retrunTospawn();
		
		while (!supress) {
			getPlayerPath();
			if (reader < path.size()) {
				player.setPos(path.get(reader));
				//System.out.println("player pos: " + player.getPos());
				player.score(path.get(reader));
				reader++;
				try {
					Thread.sleep(300);
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

	public void retrunTospawn() {
		reader = 0;
		path.clear();
		player.getPath().clear();
		player.setPos(player.playerSpawn());

	}

	public List<Point> getPath() {
		return path;
	}

	public void setPath(ArrayList<Point> path) {
		this.path = path;
	}

	

}
