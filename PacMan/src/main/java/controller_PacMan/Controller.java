package controller_PacMan;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import application_PacMan.PacMan_gui;
import canvas_PacMan.DrawThread;
import canvas_PacMan.ThreadController;
import characters_PacMan.Ghost;
import characters_PacMan.Player;
import hibernate_PacMan.DataBaseConnection;
import hibernate_PacMan.FileReader;
import javafx.application.Platform;
import map_PacMan.Map;

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

	public Controller(PacMan_gui pMG, Player player, Map map, Ghost[] ghlist) {
		this.pMG = pMG;
		this.player = player;
		this.map = map;
		this.tc = new ThreadController(player, ghlist);
	}

	public void start(DrawThread dt) {

		FileReader.getAllMapsFromFile();
		FileReader.getAllHighScoresFromFile();

		DataBaseConnection.getAllMapsFromDataBase();
		DataBaseConnection.getAllHighScoresFromDataBase();
		
		try {
			map.setMap(FileReader.setFirstMap());

		} catch (NullPointerException e) {
			map.setMap(DataBaseConnection.setFirstMap());
		}

		try {
			getScoresPrivate(FileReader.getUsedMap().getMapName());
		} catch (NullPointerException e) {
			getScoresPublic(DataBaseConnection.getUsedMap().getMapName());
		}

		dt.start();

		setTopData();
	}

	public void setTopData() {
		player.addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				String s = (String) arg;

				String[] sL = s.split(",");
				pMG.setLives(sL[0]);
				pMG.setScore(sL[1]);

			}
		});
	}

	public void startThreads() {
		gameEndCheck();
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
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				pMG.gameOver();
			}
		});

	}

	public void readMapPublic(String s) {
		map.setMap(DataBaseConnection.readOneMap(s));
		getScoresPublic(s);
	}

	public void readMapPrivate(String s) {
		map.setMap(FileReader.getMap(s));
		getScoresPrivate(s);

	}

	public void getScoresPublic(String nString) {
		pMG.bottomDataPane(DataBaseConnection.scoreForMap(nString));

	}

	public void getScoresPrivate(String s) {
		pMG.bottomDataPane(FileReader.scoreForMap(s));
	}

	public List<String> readFilesDataBase() {
		return DataBaseConnection.getMapNames();
	}

	public List<String> readFilesPrivate() {
		return FileReader.GetMapNamesFromFile();
	}

	private void gameEndCheck() {
		GameCheck gc = new GameCheck(player);
		gc.addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				// System.out.println(arg);
				if ((boolean) arg) {

					ThreadsSuppress();
					gameOver();
					o.deleteObserver(this);
					gc.suppress = true;
				}
			}
		});
		new Thread(gc).start();
	}

	private class GameCheck extends Observable implements Runnable {
		private Player player;
		private boolean suppress = false;

		public GameCheck(Player player) {
			this.player = player;
		}

		public void run() {
			while (!suppress) {
				setChanged();
				notifyObservers(player.isGameEnd());

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
