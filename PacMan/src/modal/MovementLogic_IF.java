package modal;

import java.awt.Point;

public interface MovementLogic_IF {
	public Point yli(Point newpos); //mitä tapahtuu kun mennään alueen ulkopuolelle
	public boolean avoidWall(Point pos); //tarkastetan voidaanko siirtya ruutuun
}
