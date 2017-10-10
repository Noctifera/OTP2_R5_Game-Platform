package modal;

import java.awt.Point;

public interface Draw_IF {
	public void update();
	
	public void move(); //piirettään ympyrä missä henkilö on
	
	public void clear(); // tyhjenetään ruutu
	
	public void drawDot(Point pos);
	
	public void drawLargeDot(Point pos);
	
	public void drawGhost();
	
	public void draw();
	
}
