package controller;

import java.awt.Point;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modal.Draw;
import modal.Map;
import modal.Readers;

public class Controller {
	private Draw draw;
	private Map map;
	private Readers readers;
	private Main main;

	private String[] strings;
	private int tileSize;
	private String event = "";

	public Controller(Main main,Readers readers,Draw draw, Map map, String[] strings, int tileSize) {
		this.main = main;
		this.readers = readers;
		this.draw = draw;
		this.map = map;
		this.strings = strings;
		this.tileSize = tileSize;
		this.event = strings[0];
	}
	public void popUp(String message) {
		main.popUp(message);
	}
	public void beginning() {
		readers.getAllMapsFromDataBase();
		map.initializeMap();
		draw.clear();
		draw.drawGrid();
		draw.drawOuterBound();
	}

	public void draw(MouseEvent e, String s) {
		int mouseX = (int) e.getX();
		int mouseY = (int) e.getY();

		while (mouseX % tileSize != 0) {
			mouseX = mouseX - 1;
			if (mouseX % tileSize == 0) {
				break;
			}

		}
		while (mouseY % tileSize != 0) {
			mouseY = mouseY - 1;
			if (mouseY % tileSize == 0) {
				break;
			}
		}
		Point point = new Point(mouseX, mouseY);
		System.out.println(point+s);
		if (s.equals(strings[4]) || s.equals(strings[5])) {
			map.onlyOne(point, s);
			draw.drawFullMap();
		}else {
			map.addToMap(point, s);
			draw.drawFullMap();
		}
	}
	public void ClearMap() {
		map.initializeMap();
		draw.clear();
		draw.drawGrid();
		draw.drawFullMap();
	}

	public void saveMap(String fileName) {
		String contains = map.mapContains();
		if(contains.equals("")) readers.SaveMapToDataBase(fileName);
		else {
			popUp(contains);
		}
	}

	public void getMap(String fileName) {
		readers.readOneMap(fileName);
		draw.clear();
		draw.drawGrid();
		draw.drawFullMap();
	}

	public String[] readFiles() {
		
		String[] fileNames = readers.allMapNames();
		return fileNames;
	}
	
	public void handle(TextField name,Button save,Button newM,ChoiceBox<String> cb,ListView<String> files) {
		draw.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println(event);
				draw(e, event);
			}

		});
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String mapName = name.getText();
				if(!mapName.equals("")) {
					saveMap(mapName);
				}else{
					popUp("Name The Map");
				}
				
				// files.setItems(FXCollections.observableArrayList(con.readFiles()));
			}
		});
		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> o, Object o1, Object o2) {
				for (int i = 0; i < strings.length; i++) {
					if (o2.equals(strings[i])) {
						event = strings[i];
						//System.out.println(event);
					}
				}
			}
		});
		files.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				getMap(newValue);
				// System.out.println(newValue);
			}

		});
		newM.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				ClearMap();
			}
		});

	}
	

}
