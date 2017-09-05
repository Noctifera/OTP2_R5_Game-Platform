package modal;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw extends Canvas {
	private GraphicsContext gc;

	public Draw(int x, int y){
		super(x,y);
		this.gc = this.getGraphicsContext2D();
	}
	public void clear(){
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
