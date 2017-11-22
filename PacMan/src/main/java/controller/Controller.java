package controller;

import application.PacMan_gui;
import canvas.CanvasController;
import canvas.DrawThread;
import characters.Player;
import hibernate.DataBaseConnection;
import map.Map;

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
	private Map map;

	public Controller(PacMan_gui pMG,Player player, Map map) {
		this.pMG = pMG;
		this.player = player;
		this.map = map;
	}
	public void start(CanvasController cc) {
		DataBaseConnection.getAllMapsFromDataBase();
		DataBaseConnection.getAllHighScoresFromDataBase();
		DataBaseConnection.setFirstMap();
		map.setMap(DataBaseConnection.getUsedMap().getMapData());
		getScores(DataBaseConnection.getUsedMap().getMapName());
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
	public void getScores(String nString) {
		pMG.bottomDataPane(DataBaseConnection.scoreForMap(nString));
		
	}
}
