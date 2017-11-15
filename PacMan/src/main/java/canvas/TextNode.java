package canvas;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class TextNode {

	private String text;
	private int x;
	private int y;
	private Font font;
	private int imageWidth;
	private int imageHeight;
	private Image image;
	private boolean color = false;

	public TextNode(String text, int x, int y, Font font, Image image) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.font = font;
		this.imageWidth = 288;
		this.imageHeight = 192;
		this.image = image;
		
	}
	

	public boolean isColor() {
		return color;
	}


	public void setColor(boolean color) {
		this.color = color;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "TextNode [text=" + text + ", x=" + x + ", y=" + y + ", font=" + font + ", imageWidth=" + imageWidth + ", imageHeight=" + imageHeight + ", image=" + image + "]";
	}
	
	
}
