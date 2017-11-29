package controller;

import java.util.ArrayList;
import java.util.List;
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
	private ThreadController tc;

	public Controller(PacMan_gui pMG,Player player, Map map,Ghost[] ghlist) {
		this.pMG = pMG;
		this.player = player;
		this.map = map;
		tc = new ThreadController(player, ghlist);
	}
	public void start(DrawThread dt) {
		DataBaseConnection.getAllMapsFromDataBase();
		DataBaseConnection.getAllHighScoresFromDataBase();
		DataBaseConnection.setFirstMap();
		map.setMap(DataBaseConnection.getUsedMap().getMapData());
		getScores(DataBaseConnection.getUsedMap().getMapName());

		dt.start();
		
		
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
	public void startThreads() {
		tc.startThreads();
	}
	public void ThreadsSuppress() {
		tc.suppress();
	}
	public boolean ThreadActive() {
		return tc.isActive();
	}
	public void setHighScore(String playername) {
		player.post(playername);
	}

	public void gameOver() {
		pMG.gameOver();
	}
	public void readMap(String s) {
		map.setMap(DataBaseConnection.readOneMap(s));
		pMG.bottomDataPane(DataBaseConnection.scoreForMap(s));
	}
	public void getScores(String nString) {
		pMG.bottomDataPane(DataBaseConnection.scoreForMap(nString));
		
	}
	public List<String> readFiles(){
		return DataBaseConnection.getMapNames();
	}
}
