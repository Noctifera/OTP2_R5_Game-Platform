package controller;

import java.util.ArrayList;

import application.PacMan_gui;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import modal.*;

public class Controller implements Controller_IF {
	private Player player;
	private Map map;
	private PacMan_gui pMG;

	public Controller(Player player, Map map, HighScore hs, PacMan_gui pMG) {
		this.map = map;
		this.player = player;
		this.pMG = pMG;
	}

	public void start() {
		map.readMap("Level1-fixed.txt");
		map.setDots();
		map.setLargeDots();
	}

	public void move(KeyEvent event) {
		KeyCode e = event.getCode();
		player.move(e);
		System.out.println(player.getVulnerable());
	}

	public void getMap(String fileName) {
		map.readMap(fileName);
	}

	public void gethighScore() {
		player.gethighScore();

	}

	public void setLives() {
		pMG.setLives(player.getLife());
	}

	public void setScore() {
		pMG.setScore(player.getScore());
	}

	public void setHighScore(int score, String playername, String date) {
		player.setHighScore(score, playername, date);
	}

	public ArrayList<String> presentScore() {
		return player.presentScore();
	}

	public ArrayList<String> presentName() {
		return player.presentName();
	}

	public ArrayList<String> presentDate() {
		return player.presentDate();
	}
}
