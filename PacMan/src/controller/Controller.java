package controller;

import application.PacMan_gui;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import modal.*;

public class Controller implements Controller_IF {
	private Player player;
	private Map map;
	private HighScore hs;
	private PacMan_gui pMG;

	public Controller(Player player, Map map,HighScore hs,PacMan_gui pMG) {
		this.map = map;
		this.player = player;
		this.pMG = pMG;
	}

	public void start() {
		map.readMap("Level1-fixed.txt");
	}

	@Override
	public void move(KeyEvent event) {
		KeyCode e = event.getCode();
		player.move(e);
		setScore();
		System.out.println(player.getVulnerable());
	}
	public String[] readFiles() {
		return map.allFiles();
	}
	public void getMap(String fileName) {
		map.readMap(fileName);
	}
	public void gethighScore() {
		try {
			hs.selectFromDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setLives() {
		pMG.setLives(player.getLife());
	}
	public void setScore() {
		pMG.setScore(player.getScore());
	}
	public void setHighScore(int score,String playername,String date) {
		try {
			hs.post( score,  playername,  date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
