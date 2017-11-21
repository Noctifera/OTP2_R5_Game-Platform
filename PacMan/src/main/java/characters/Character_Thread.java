package characters;

public class Character_Thread extends Thread {

	private Character character;

	private volatile boolean suppress = false;

	public Character_Thread(Character character) {
		this.character = character;
	}

	public void run() {
		spawn();
		character.findPath();

		while (!suppress) {
			if (character.getReader() < character.pathlength()) {
				character.getNextPos();
			} else {
				character.findPath();
			}

			if (character instanceof Ghost) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (character instanceof Player) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void suppress() {
		suppress = true;
	}

	private void spawn() {
		character.setPos(character.characterSpawn());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(character.getPos());
	}
}
