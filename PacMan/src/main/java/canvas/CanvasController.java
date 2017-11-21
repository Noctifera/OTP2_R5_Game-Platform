package canvas;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import characters.Ghost;
import characters.Player;
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

public class CanvasController extends Canvas {
	
	private Draw_IF canvas;
	private Player player;
	
	private Map map;
	private ThreadController tc;
	

	private Point point;
	private int tileSize;
	private Ghost[] ghlist;
	private ArrayList<TextNode> maplist;


	public CanvasController(Point point,int tileSize,  Map map, Player player, Ghost[] ghlist) {
		super(point.x, point.y);
		this.tileSize = tileSize;
		this.point = point;
		this.player = player;
		this.tc = new ThreadController();
		this.map = map;
		this.ghlist = ghlist;
		handle();
	}

	public void update() {
		canvas.update();
	}

	public void menu() {
		maplist();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		canvas = new Menu(point.x, point.y, this.getGraphicsContext2D(), this);

	}

	public void game() {
		
		tc.startThreads(player,ghlist);
		
		canvas = new Draw(point.x, point.y, tileSize, player, ghlist, map, this.getGraphicsContext2D());

	}
	protected void maplist() {
		ArrayList<TextNode> list = new ArrayList<>();
		int x = 0;
		int y = 0;
		//DataBaseConnection.getAllMapsFromDataBase();
		for (MapsTable mt : DataBaseConnection.getMapList()) {
			try {
				BufferedImage mi = ImageIO.read(new ByteArrayInputStream(mt.getMapImage()));
				Image image = SwingFXUtils.toFXImage(mi, null);
				TextNode tn = new TextNode(mt.getMapName(), x, y, new Font(15), image);
				list.add(tn);
				// System.out.println("image width" + (x + tn.getImageWidth()));
				// System.out.println("width" + this.getWidth());
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

								map.setMap(DataBaseConnection.readOneMap(tn.getText()));

								
								game();
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
						tc.suppress();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						menu();
						
					} else if (canvas instanceof Menu) {
						game();
					}
				}
			}
		});
	}

}
