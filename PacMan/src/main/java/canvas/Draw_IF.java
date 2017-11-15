package canvas;

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

	public void handle(DrawThread dt);

	public void front();

	public void back();

}
