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
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new eaten(player, ghlist)).start();
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
	
	private class eaten implements Runnable {

		private Player player;
		private Ghost[] ghlist;

		public eaten(Player player, Ghost[] ghlist) {
			this.player = player;
			this.ghlist = ghlist;
		}

		@Override
		public void run() {
			
			while (isActive()) {
				
				for (Ghost gh : ghlist) {
					
					if (player.getVulnerable()) {
						if (player.getPos().equals(gh.getPos())) {
							//System.out.println("eaten: true");
							gh.setEaten(true);
							//System.out.println("ghost: "+gh.iseaten());

						}
					} else if (!player.getVulnerable()) {
						//System.out.println("player: " + player.getPos() + " Ghost: " + gh.getGhost() + ": " + gh.getPos());
						if (player.getPos().equals(gh.getPos())) {
							//System.out.println("eaten: true");
							
							player.setEaten(true);
							for(Ghost gh1: ghlist) {
								gh1.setEaten(true);
								
							}
							break;
							
							//System.out.println("player: "+player.iseaten());
						}
					}
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
