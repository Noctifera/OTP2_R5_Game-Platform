package characters_PacMan;

import java.awt.Point;

public interface Character_IF {
	
	
	public Point getPos();
	
	public Point characterSpawn();
	
	public void getNextPos();
	
	public void findPath();
	
	public int pathlength();
	
	public void setPos(Point pos);
	
	public boolean iseaten();
	
	public void setEaten(boolean eaten);
	
	public void eaten();
	
	public boolean isGameEnd();
	
	public int getReader();
	
	public boolean getVulnerable();
	
	
	
	
}
