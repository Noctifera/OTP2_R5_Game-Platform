package canvas;

import java.util.ArrayList;

import characters.Character_Thread;
import characters.Ghost;
import characters.Player;

public class ThreadController {

	private ArrayList<Character_Thread> threads;

	private void inis(Player player, Ghost[] ghlist) {
		threads = new ArrayList<>();
		for (int i = 0; i < ghlist.length; i++) {
			threads.add(new Character_Thread(ghlist[i]));
		}
		threads.add(new Character_Thread(player));
	}

	public void startThreads(Player player, Ghost[] ghlist) {
		inis(player, ghlist);
		for (Character_Thread ct : threads) {
			ct.start();
		}

	}

	public void suppress() {
		for (Character_Thread ct : threads) {
			ct.suppress();
		}
	}
}
