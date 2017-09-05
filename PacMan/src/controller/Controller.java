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
		map.readMap("Testmap.txt");
	}

	@Override
	public void move(KeyEvent event) {
		player.move(event);
	}
}
