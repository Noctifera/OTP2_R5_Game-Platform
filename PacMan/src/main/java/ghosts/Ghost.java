package ghosts;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import map.MovementLogic;
import player.Player;

/**
 * Extends PathFinder, implements {@link Ghost_IF}. Instantiates path and
 * handles ghosts colors.
 * 
 * @author kari-antti
 *
 */
public class Ghost extends PathFinder implements Ghost_IF {
	private MovementLogic ml;
	private Player player;

	private Point pos;
	private String ghost;

	public Ghost(MovementLogic ml, Player player, String ghost) {
		super(ml);
		this.player = player;
		this.ghost = ghost;
		this.ml = ml;
	}

	public ArrayList<Point> insPath() {

		if (player.getVulnerable().equals("deactive")) {

			double rand = Math.random();

			if (rand <= 0.30) {
				Point randpoint = ml.randomPoint();
				return path(pos, randpoint);
			} else {

				Point point = player.getPos();
				return path(pos, point);

			}
		} else {
			Point randpoint = ml.randomPoint();
			return path(pos, randpoint);
		}
	}

	public Point getPos() {
		return pos;
	}

	public Image getImage() {
		if(ghost == "Blinky" && player.getVulnerable().equals("deactive")) {
			File f = new File("Picture\\Pacman-red-blinky.png");
			Image img = new Image(f.toURI().toString(),false);
			return img;
		}else if(ghost == "Speedy" && player.getVulnerable().equals("deactive")) {
			File f = new File("Picture\\Pacman-pink-pinky.png");
			Image img = new Image(f.toURI().toString(),false);
			return img;
		}else if(ghost == "Bashful" && player.getVulnerable().equals("deactive")) {
			File f = new File("Picture\\Pacman-light-blue-inky.png");
			Image img = new Image(f.toURI().toString(),false);
			return img;
		}else if(ghost.equals("Pokey") && player.getVulnerable().equals("deactive")) {
			File f = new File("Picture\\Pacman-orange-clyde.png");
			Image img = new Image(f.toURI().toString(),false);
			return img;
		}else {
			 File f = new File("Picture\\Pacman-red-blinky.png");
			Image img = new Image(f.toURI().toString(),false);
			return img;
		}
	}

	public Color getColor() {
		if (ghost == "Blinky" && player.getVulnerable().equals("deactive")) {
			return Color.RED;
		} else if (ghost == "Speedy" && player.getVulnerable().equals("deactive")) {
			return Color.PINK;
		} else if (ghost == "Bashful" && player.getVulnerable().equals("deactive")) {
			return Color.AQUAMARINE;
		} else if (ghost.equals("Pokey") && player.getVulnerable().equals("deactive")) {
			return Color.GOLD;
		} else {
			return Color.BLUEVIOLET;
		}
	}

	public String vulnerableStatus() {
		return player.getVulnerable();
	}

	public String getGhost() {
		return ghost;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Point ghostHouse() {
		return ml.ghostHouse();
	}
}