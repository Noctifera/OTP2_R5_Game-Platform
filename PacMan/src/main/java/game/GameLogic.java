package game;

import java.util.List;

import canvas.DrawThread;
import canvas.Draw_IF;
import controller.Controller;
import ghosts.Ghost;
import ghosts.GhostThread;
import hibernate.DataBaseConnection;
import hibernate.MapsTable;
import map.Map;
import player.Player;
import player.PlayerThread;
import sounds.Sounds;

public class GameLogic {

	private PlayerThread playerthread;
	private DrawThread dt;
	private Controller con;
	private Player player;
	private Sounds sounds;
	private Map map;
	private Draw_IF[] cavasList;

	private int ghostAmount = 4;
	private Ghost[] ghlist;
	private GhostThread[] ghtlist = new GhostThread[ghostAmount];

	private boolean mapl = false;
	private int deactiveCount = 0;

	public GameLogic(Controller con, Player p, Ghost[] ghlist, Sounds sound, Map map, Draw_IF[] cavasList) {
		this.con = con;
		this.player = p;
		this.ghlist = ghlist;
		this.sounds = sound;
		this.map = map;
		this.cavasList = cavasList;
	}

	public List<MapsTable> setMenu() {
		List<MapsTable> nameList = DataBaseConnection.getMapList();
		return nameList;
	}

	public boolean mapLoaded() {
		return mapl;
	}
	public void loadFirstMap() {
		map.setMap(DataBaseConnection.readOneMap(DataBaseConnection.getMapList().get(0).getMapName()));
		mapl = true;
	}

	public void loadMap(String name) {
		map.setMap(DataBaseConnection.readOneMap(name));
		mapl = true;
	}

	public void StartGame() {

		player.setPos(player.playerSpawn());
		for (Ghost g : ghlist) {
			g.setPos((g.ghostHouse()));
		}

		dt = new DrawThread(cavasList[0],this);
		dt.start();

		try {
			Thread.sleep(4400);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		playerthread = new PlayerThread(player, con);
		playerthread.start();

		for (int i = 0; i < ghtlist.length; i++) {
			ghtlist[i] = new GhostThread(ghlist[i]);

		}
		for (GhostThread ghost : ghtlist) {
			ghost.start();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void openMenu() {
		suppress();
		dt = new DrawThread(cavasList[1],this);
		dt.start();
	}
	
	public void resumegame() {
		suppress();
		dt = new DrawThread(cavasList[0],this);
		dt.start();
		playerthread.start();
		for (GhostThread ghost : ghtlist) {
			ghost.start();
		}
	}

	public boolean play() {
		boolean apu = false;
		if (player.getLife() > 0 && (player.allDots().size() > 0 || player.allLargeDots().size() > 0)) {

			apu = true;
		}
		return apu;
	}

	public void looselife() {
		int i = 0;
		while (i < ghlist.length) {

			switch (player.getVulnerable()) {
			case "deactive":
				if (ghlist[i].getPos().equals(player.getPos())) {
					sounds.playSound(sounds.getDeath());
					for (int j = 0; j < ghlist.length; j++) {
						ghtlist[j].returnToHouse();
					}
					player.getEaten();
					playerthread.retrunTospawn();

					break;
				}

			case "active":

				if (ghlist[i].getPos().equals(player.getPos())) {
					sounds.playSound(sounds.getEatghost());
					ghtlist[i].returnToHouse();
					player.ghost();
				}

				break;

			default:
				break;
			}

			i++;
		}
	}

	public void vulnerable() {
		deactiveCount++;
		if (deactiveCount == 1000) {
			player.setVulnerable("deactive");
			deactiveCount = 0;
		}

	}

	public void suppress() {
		System.out.println("GameLogic: supress");
		for (int i = 0; i < ghtlist.length; i++) {
			ghtlist[i].supress();
		}
		playerthread.supress();
		dt.supress();
	}
	public void handle() {
		
	}
}
