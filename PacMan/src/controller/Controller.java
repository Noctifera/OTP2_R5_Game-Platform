package controller;

import java.util.ArrayList;

import application.PacMan_gui;
import javafx.scene.Scene;
import modal.*;

public class Controller implements Controller_IF {
	private Player player;
	private Map map;
	private PacMan_gui pMG;
	private Sounds sounds;

	public Controller(Player player, Map map, PacMan_gui pMG,Sounds sounds) {
		this.map = map;
		this.player = player;
		this.pMG = pMG;
		this.sounds = sounds;
	}

	public void start(Scene scene, Draw draw, Ghost[] ghlist) {
		map.readMap("Level1-fixed.txt");
		map.setDots();
		map.setLargeDots();
		sounds.playSound(sounds.getBeginning());
		
		GameThread gamethread = new GameThread(player,this,scene,draw,ghlist,sounds);
		gamethread.start();
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

	public void setHighScore(String playername) {
		player.setHighScore(playername);
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
	public void gameOver() {
		pMG.gameOver();
	}
}
