package canvas;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ghosts.Ghost;
import hibernate.DataBaseConnection;
import hibernate.MapsTable;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import map.Map;
import player.Player;

public class ComCanvas extends Canvas {
	private Draw_IF canvas;
	private Player player;
	private Ghost[] gh;
	private Map map;

	private int width;
	private int height;
	private int tileSize;
	private ArrayList<TextNode> maplist;

	public ComCanvas(Player player, Ghost[] gh, Map map, int width, int height, int tileSize) {
		super(width, height);
		this.player = player;
		this.gh = gh;
		this.map = map;
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;

		handle();
	}

	public void update() {
		canvas.update();
	}

	public void menu() {
		maplist();
		canvas = new Menu(width, height, this.getGraphicsContext2D(), this);
	}

	public void game() {
		canvas = new Draw(width, height, tileSize, player, gh, map, this.getGraphicsContext2D());
	}

	protected void maplist() {
		ArrayList<TextNode> list = new ArrayList<>();
		int x = 0;
		int y = 0;
		for (MapsTable mt : DataBaseConnection.getMapList()) {
			try {
				BufferedImage mi = ImageIO.read(new ByteArrayInputStream(mt.getMapImage()));
				Image image = SwingFXUtils.toFXImage(mi, null);
				TextNode tn = new TextNode(mt.getMapName(), x, y, new Font(15), image);
				list.add(tn);
				//System.out.println("image width" + (x + tn.getImageWidth()));
				//System.out.println("width" + this.getWidth());
				if ((x + tn.getImageWidth() * 2) >= this.getWidth()) {
					x = 0;
					y = y + tn.getImageHeight() + 20;
				} else {
					x = x + tn.getImageWidth() + 10;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		maplist = list;
	}

	public ArrayList<TextNode> getMaplist() {
		return maplist;
	}

	private void handle() {
		// TODO Auto-generated method stub
		this.setFocusTraversable(true);
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				// TODO Auto-generated method stub

				if (canvas instanceof Menu) {
					for (TextNode tn : maplist) {
						if ((me.getX() > tn.getX()) && (me.getX() < (tn.getX() + tn.getImageWidth()))) {
							if (me.getY() > tn.getY() && (me.getY() < (tn.getY() + tn.getImageHeight()))) {
								// System.out.println("color");
								tn.setColor(true);
							} else {
								tn.setColor(false);
							}
						} else {
							tn.setColor(false);
						}
					}
				}

			}
		});
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				// TODO Auto-generated method stub
				if (canvas instanceof Menu) {
					for (TextNode tn : maplist) {
						if ((me.getX() > tn.getX()) && (me.getX() < (tn.getX() + tn.getImageWidth()))) {
							if (me.getY() > tn.getY() && (me.getY() < (tn.getY() + tn.getImageHeight()))) {
								System.out.println(tn.getText());
								DataBaseConnection.readOneMap(tn.getText());
							}

						}
					}
				}
			}
		});

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				if ((event.getCode() != KeyCode.ESCAPE)) {
					if (canvas instanceof Draw) {
						player.path(event.getCode());
					}
				} else {
					if (canvas instanceof Draw) {
						menu();
					} else if (canvas instanceof Menu) {
						game();
					}
				}
			}
		});
	}

}
