package characters;

import java.awt.Point;

public interface Character {
	
	
	public Point getPos();
	
	public Point characterSpawn();
	
	public void getNextPos();
	
	public void findPath();
	
	public int pathlength();
	
	public void setPos(Point pos);
	
	public boolean eaten();
	
	public boolean isGameEnd();
	
	public int getReader();
	
	public boolean getVulnerable();
	
	
	
	
}
