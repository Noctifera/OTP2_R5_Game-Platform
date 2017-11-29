package canvas;

import java.util.ArrayList;

import characters.Character_Thread;
import characters.Ghost;
import characters.Player;

public class ThreadController {

	private ArrayList<Character_Thread> threads;
	private Player player;
	private Ghost[] ghlist;
	private boolean active = false;

	public ThreadController(Player player, Ghost[] ghlist) {
		this.player = player;
		this.ghlist = ghlist;
	}

	private void inis() {

		threads = new ArrayList<>();

		for (int i = 0; i < ghlist.length; i++) {
			threads.add(new Character_Thread(ghlist[i]));
		}

		threads.add(new Character_Thread(player));
	}

	public void startThreads() {
		inis();
		for (Character_Thread ct : threads) {
			ct.start();
		}
		active = true;
	}

	public void suppress() {
		for (Character_Thread ct : threads) {
			ct.suppress();
		}
		active = false;
	}

	public boolean isActive() {
		return active;
	}
	

}
