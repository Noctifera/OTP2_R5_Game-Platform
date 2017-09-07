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
	public String[] readFiles() {
		String[] fileNames = new String[map.allFiles().length];
		for (int i = 0; i < map.allFiles().length; i++) {
			fileNames[i] = map.allFiles()[i].getName();
		}
		for (String s : fileNames) {
			System.out.println(s);
		}
		return fileNames;
	}
	public void getMap(String fileName) {
		map.readMap(fileName);
	}
}
