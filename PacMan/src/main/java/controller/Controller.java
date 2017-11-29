package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import application.PacMan_gui;
import canvas.DrawThread;
import canvas.ThreadController;
import characters.Ghost;
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
	public void start(DrawThread dt, Ghost[] ghlist, Player player) {
		DataBaseConnection.getAllMapsFromDataBase();
		DataBaseConnection.getAllHighScoresFromDataBase();
		DataBaseConnection.setFirstMap();
		map.setMap(DataBaseConnection.getUsedMap().getMapData());
		getScores(DataBaseConnection.getUsedMap().getMapName());

		dt.start();
		
		ThreadController tc = new ThreadController();
		tc.startThreads(player, ghlist);
		setTopData();
	}

	public void setTopData() {
		player.addObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				// TODO Auto-generated method stub
				String s = (String) arg;
				
				String[] sL = s.split(",");
				pMG.setLives(sL[0]);
				pMG.setScore(sL[1]);
				
			}
		});
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
