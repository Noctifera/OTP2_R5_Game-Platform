package characters_PacMan;

public class Character_Thread extends Thread {

	private Character_IF character;

	private volatile boolean suppress = false;

	public Character_Thread(Character_IF character) {
		this.character = character;
	}

	public void run() {
		spawn();
		character.findPath();

		while (!suppress) {
			if (character.getReader() < character.pathlength()) {
				character.getNextPos();

				if (character.iseaten()) {
					character.eaten();
					spawn();
				}
			} else {
				character.findPath();
			}

			if (character instanceof Ghost) {
				if (character.getVulnerable()) {
					try {
						Thread.sleep(350);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else if (character instanceof Player) {

				if (character.getVulnerable()) {
					((Player) character).increseVulnerableCount();
					if (((Player) character).getVulnerableCount() > 30) {
						((Player) character).setVulnerable(false);
						((Player) character).setVulnerableCount(0);
					}
				}
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
		// System.out.println("character spawn: "+character.characterSpawn());
		// System.out.println("character pos: "+character.getPos());
		character.setEaten(false);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		character.findPath();

	}

}
