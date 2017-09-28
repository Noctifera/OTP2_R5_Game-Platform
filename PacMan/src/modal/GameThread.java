package modal;

public class GameThread extends Thread {
	private Player p;
	private PlayerThread pt;
	private Ghost[] gh;
	private GhostThread[] gt;
	private DrawThread dt;
	
	
	public GameThread(Player p, PlayerThread pt, Ghost[] gh, GhostThread[] gt, DrawThread dt) {
		this.p = p;
		this.pt = pt;
		this.gh = gh;
		this.gt = gt;
		this.dt = dt;
	}

	public void run() {
		while(true) {
			//looselife();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//suppress();
	}
	public boolean play() {
		boolean apu = false;
		if(p.getLife() >= 0) {
			System.out.println(p.getLife());
			apu = true;
		}
		return apu;
	}
	
	public void suppress() {
		System.out.println("supress");
		for(int i = 0; i<gt.length; i++) {
			gt[i].supress();
		}
		pt.supress();
		dt.supress();
	}
	public void looselife() {
		int k = 0;
		for(int i = 0; i< gh.length; i++) {
			if(p.getPos().equals(gh[i].getPos()) && k<1) {
				p.getEaten();
				pt.retrunTospawn();
				gt[i].returnToHouse();
				
			}
		}
		
	}

	
	
	
	
	
	

}
