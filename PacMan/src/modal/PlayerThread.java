package modal;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerThread extends Thread {
	private Player player;
	private Scene scene;
	
	private volatile boolean supress = false;

	public PlayerThread(Player player, Scene scene) {
		this.player = player;
		this.scene = scene;
	}
	
	public void run() {
		handle();
		player.setPos(player.playerSpawn());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!supress) {
			
		}
	}
	public void supress() {
		supress = true;
	}
	public void handle() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				// liikkuminen
				KeyCode code = event.getCode();
				player.move(code);
			}
		});
	}
	

}
