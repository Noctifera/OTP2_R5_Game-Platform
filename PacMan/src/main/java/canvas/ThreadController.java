package canvas;

import ghosts.Ghost;
import ghosts.GhostThread;
import player.Player;
import player.PlayerThread;

public class ThreadController {
	private Player player;
	private Ghost[] ghlist;
	private GhostThread[] ghtList;
	private PlayerThread pt;
	
	public ThreadController(Player player, Ghost[] ghlist) {
		this.player = player;
		this.ghlist = ghlist;
	}
	
	public void startThreads() {
		ghtList = new GhostThread[ghlist.length];
		
		for(int i = 0; i< ghtList.length; i++) {
			GhostThread ght  = new GhostThread(ghlist[i]);
			ghtList[i] = ght;
			ght.start();
			
		}
		pt = new PlayerThread(player);
		pt.start();
	}
	
	public void suppress() {
		for(GhostThread gt: ghtList) {
			gt.supress();
		}
		pt.supress();
	}
	
	
	
}
