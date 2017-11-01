package application;


import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.Draw;
import modal.Map;
import modal.DataBaseReader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	private Controller con;
	private Draw draw;
	private Map map;
	
	private Button ready;
	private Button newM;
	private Stage saveStage;
	private Stage smallPopUpStage;
	private ToggleGroup group;
	private ListView<String> files = new ListView<>();
	private ChoiceBox<String> cb;
	private final int width = 720;
	private final int height = 480;
	private final int tileSize = 40;
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostHouse"};
	
	public void init() {
		map = new Map(width, height, tileSize, strings);
		draw = new Draw(width, height, tileSize, map, strings);
		con = new Controller(this,draw, map, strings, tileSize);
	}
	public Stage SavePopUp() {
		saveStage = new Stage();
		
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		
		String s = con.mapContains();
		if(!s.equals("")){
		Label check = new Label();
		check.setText(s);
		gp.add(check, 1, 1,2,1);
		}
		
		  group = new ToggleGroup();
		    RadioButton button1 = new RadioButton("private");
		    button1.setToggleGroup(group);
		    button1.setId("private");
		    button1.setSelected(true);
		    RadioButton button2 = new RadioButton("public");
		    button2.setToggleGroup(group);
		    button2.setId("public");
		    
		  gp.add(button1, 1, 2);
		  gp.add(button2, 2, 2);
		  group.getSelectedToggle();
		
		
		Label label = new Label();
		label.setText("Map Name: ");
		gp.add(label, 1, 3);
		
		
		TextField fileName = new TextField();
		gp.add(fileName, 2, 3);
		
		Button save = new Button("Save Map");
		gp.add(save, 2, 4);
				
		if(!s.equals("")) {
			button1.setDisable(true);
			button2.setDisable(true);
			fileName.setDisable(true);
			save.setDisable(true);
		}
		
		con.handePopUp(save, group, fileName);
		
		Scene scene = new Scene(gp);
		saveStage.setScene(scene);
		return saveStage;
	}
	public void SavePopUpClose() {
		saveStage.close();
	}
	
	public Stage smallPopup(boolean save) {
		GridPane gp = new GridPane();
		Label label = new Label();
		gp.add(label, 1, 1);
		if(save){
			label.setText("Map Saved");
		}else {
			label.setText("Map could not be saved");
		}
		Button button = new Button("OK");
		gp.add(button, 1, 2);
		
		smallPopUpStage = new Stage();
		Scene scene = new Scene(gp);
		smallPopUpStage.setScene(scene);;
		con.smallHandle(button);
		return smallPopUpStage;
		
		
	}
	public void smallPopupClose() {
		smallPopUpStage.close();
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			GridPane root = new GridPane();
			root.setHgap(10);
			root.setVgap(10);
			
			Scene scene = new Scene(root,width+260, height+20);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("MapCreator");
			Image image = new Image("file:maxresdefault.jpg");
			primaryStage.getIcons().add(image);
			
			
			
			con.beginning();
			root.add(draw,1,1);
			root.add(rightVerticalBox(),2,1);
			con.handle(ready, newM, cb, files);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					System.exit(0);

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private VBox rightVerticalBox() {
		GridPane gd = new GridPane();
		cb = new ChoiceBox<String>();
		cb.setItems(FXCollections.observableArrayList(strings));

		cb.setValue(strings[0]);
		gd.add(cb, 0, 0);
		
		newM = new Button("Clear Map");
		gd.add(newM, 1, 0);
		
		group = new ToggleGroup();
	    RadioButton button1 = new RadioButton("private");
	    button1.setToggleGroup(group);
	    button1.setId("private");
	    button1.setSelected(true);
	    RadioButton button2 = new RadioButton("public");
	    button2.setToggleGroup(group);
	    button2.setId("public");
	    
	     gd.add(button1, 0, 1);
	     gd.add(button2, 1, 1);
	    

		files.setItems(FXCollections.observableArrayList(con.readFiles()));
		
		gd.add(files, 0, 2,2,1);
		
		ready = new Button("Map Ready to be Saved");
		gd.add(ready, 0, 3);

		VBox vb = new VBox(gd);

		return vb;

	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
