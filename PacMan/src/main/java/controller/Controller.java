package controller;

import application.PacMan_gui;
import canvas.Draw;
import canvas.Draw_IF;
import canvas.Menu;
import game.*;
import ghosts.Ghost;
import hibernate.DataBaseConnection;
import map.Map;
import player.Player;
import sounds.Sounds;

/**
 * controls data between the application and modal
 * 
 * @author markus
 * @version 1.0
 *
 */
public class Controller implements Controller_IF {
	private Player player;
	private PacMan_gui pMG;

	public Controller(Player player, PacMan_gui pMG) {
		this.player = player;
		this.pMG = pMG;
	}

	public void start(Ghost[] ghlist,Sounds sounds,Map map, Draw_IF[] canvasList) {
		DataBaseConnection.getAllMapsFromDataBase();
		DataBaseConnection.getAllHighScoresFromDataBase();
		GameThread gt = new GameThread(player, this, ghlist, sounds, map, canvasList);
		gt.start();
	}

	public void setLives() {
		pMG.setLives(player.getLife());
	}

	public void setScore() {
		pMG.setScore(player.getScore());
	}

	public void setHighScore(String playername) {
		player.post(playername);
	}

	public void gameOver() {
		pMG.gameOver();
	}
}
