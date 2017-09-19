package modal;

public class GhostThread extends Thread {
	private Ghost gh;
	private MovementLogic ml;

	private volatile boolean supress = false;

	public GhostThread(Ghost gh, MovementLogic ml) {
		this.gh = gh;
		this.ml = ml;
	}

	public void run() {
		gh.setPos(ml.ghostHouse());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gh.insPath();
		while (!supress) {

			while (gh.getPath() != null && gh.getPath().size() > gh.getSize()) {
				gh.update();
				//System.out.println("update");
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
