package modal;

import java.util.ArrayList;

public class GhostThread extends Thread {
	private Ghost gh;
	private ArrayList<Node> path = new ArrayList<>();
	//private Node next;

	private volatile boolean supress = false;
	
	private int reader;
	private int wait = 0;

	public GhostThread(Ghost gh) {
		this.gh = gh;
	}

	public void run() {
		gh.setPos(new Node(gh.ghostHouse()));
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		path.addAll(gh.insPath());
		//next = gh.inisPath();
		reader = 0;
		while (!supress) {
			
			
			while (reader < path.size()) {

				gh.setPos(path.get(reader));
				reader++;

				try {
					Thread.sleep(250);
					if (wait ==1) house();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(reader > 0) {
				reader = 0;
				path.clear();
				path.addAll(gh.insPath());
			}
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
	public void house() throws InterruptedException {
		Thread.sleep(3000);
		wait = 0;
	}
	public void supress() {
		supress = true;
	}
	public void returnToHouse() {
		reader = 0;
		path.clear();
		gh.setPos(new Node(gh.ghostHouse()));
		wait = 1;
		path.addAll(gh.insPath());
		
	}

}
