package controller;

import javafx.scene.input.KeyEvent;
import modal.*;

public class Controller implements Controller_IF {
	private Player player;
	private Map map;

	public Controller(Player player, Map map) {
		this.map = map;
		this.player = player;
	}

	public void start() {
		map.readMap("Level1-fixed.txt");
	}

	@Override
	public void move(KeyEvent event) {
		player.move(event);
	}
	public String[] readFiles() {
		return map.allFiles();
	}
	public void getMap(String fileName) {
		map.readMap(fileName);
	}
}
