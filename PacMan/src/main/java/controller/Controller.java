package controller;

import application.PacMan_gui;
import canvas.CanvasController;
import canvas.DrawThread;
import hibernate.DataBaseConnection;
import player.Player;

/**
 * controls data between the application and modal
 * 
 * @author markus
 * @version 1.0
 *
 */
public class Controller implements Controller_IF {
	private PacMan_gui pMG;
	private Player player;

	public Controller(PacMan_gui pMG,Player player) {
		this.pMG = pMG;
		this.player = player;
	}
	public void start(CanvasController cc) {
		DataBaseConnection.getAllMapsFromDataBase();
		DataBaseConnection.getAllHighScoresFromDataBase();
		cc.menu();
		DrawThread dt = new DrawThread(cc);
		dt.start();
	}

	public void setLives(int lives) {
		pMG.setLives(lives);
	}

	public void setScore(int score) {
		pMG.setScore(score);
	}

	public void setHighScore(String playername) {
		player.post(playername);
	}

	public void gameOver() {
		pMG.gameOver();
	}
}
