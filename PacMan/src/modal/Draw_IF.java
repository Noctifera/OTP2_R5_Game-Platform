package modal;

import java.awt.Point;

import javafx.scene.canvas.Canvas;

/**
 * Class extends {@link Canvas} and allows items to be drawn onto it
 * 
 * @author markus
 * @version 1.0
 *
 */
public interface Draw_IF {
	/**
	 * One function that combines all drawn
	 */
	public void update();

	/**
	 * Gets Players position and draws an orange circle
	 */
	public void move(); // piirettään ympyrä missä henkilö on

	/**
	 * draws a black square the size of the canvas
	 */
	public void clear(); // tyhjenetään ruutu
	/**
	 * 
	 * @param pos position where to draw the dot
	 */
	public void drawDot(Point pos);

	/**
	 * Draws a wall to the given point
	 * 
	 * @param point The Point where The wall is Drawn
	 */
	public void drawWall(Point point);
	/**
	 * 
	 * @param pos  position where the large dot is drawn
	 */
	public void drawLargeDot(Point pos);
	/**
	 * gets position of all ghosts and draws them to canvas
	 */
	public void drawGhost();
/**
 * finds items from map and runs their draw functoins accordingly
 */
	public void draw();

}
