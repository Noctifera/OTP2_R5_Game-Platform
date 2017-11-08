package controller;

import java.awt.Point;
import java.util.List;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import modal.Draw;
import modal.FileReader;
import modal.Map;
import modal.DataBaseReader;

public class Controller {
	private Draw draw;
	private Map map;
	private DataBaseReader readers;
	private Main main;
	private FileReader file;

	private String[] strings;
	private int tileSize;
	private String event = "";

	public Controller(Main main, Draw draw, Map map, String[] strings, int tileSize) {
		this.main = main;

		this.draw = draw;
		this.map = map;
		this.strings = strings;
		this.tileSize = tileSize;
		this.event = strings[0];
		this.readers = new DataBaseReader(map,draw);
		this.file = new FileReader(map,draw);

	}

	public void beginning() {
		map.setMap(map.initializeMap());
		draw.clear();
		draw.drawGrid();
		draw.drawOuterBound();
		System.out.println(readers.getAllMapsFromDataBase());
		file.getAllMapsFromFile();
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
		System.out.println(point + s);
		if (s.equals(strings[4]) || s.equals(strings[5])) {
			map.onlyOne(point, s);
			draw.drawFullMap();
		} else {
			map.addToMap(point, s);
			draw.drawFullMap();
		}
	}

	public void ClearMap() {
		map.initializeMap();
		draw.clear();
		draw.drawGrid();
		// draw.drawFullMap();
	}

	public void saveMap(String fileName) {
		readers.SaveMapToDataBase(fileName);
	}

	public String mapContains() {
		return map.mapContains();
	}

	public void getMapFile(String fileName) {
		map.setMap(file.getMap(fileName));
		draw.clear();
		draw.drawGrid();
		draw.drawFullMap();
	}

	public void getMapDataase(String fileName) {
		map.setMap(readers.readOneMap(fileName));
		draw.clear();
		draw.drawGrid();
		draw.drawFullMap();
	}

	public List<String> readFiles() {

		List<String> fileNames = file.GetMapNamesFromFile();
		return fileNames;
	}

	public List<String> namesFromDataBase() {

		List<String> fileNames = readers.allMapNames();
		return fileNames;
	}

	public void smallHandle(Button ok) {
		ok.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				main.SavePopUpClose();
				main.smallPopupClose();
			}
		});
	}

	public void handePopUp(Button save, ToggleGroup group, TextField textfield) {

		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String fileName = textfield.getText();

				if (group.getToggles().get(0).isSelected()) {
					main.smallPopup(file.saveMapToFile(fileName)).show();
				} else {
					main.smallPopup(readers.SaveMapToDataBase(fileName)).show();
				}

			}
		});

	}

	public void handle(Button ready, Button newM, ChoiceBox<String> cb, ListView<String> files, ToggleGroup gruop, RadioButton butprivate, RadioButton butpublic, ChoiceBox<String> lang) {
		draw.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println(event);
				draw(e, event);
			}

		});
		ready.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				main.SavePopUp().show();
				;
			}
		});
		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> o, Object o1, Object o2) {
				for (int i = 0; i < strings.length; i++) {
					if (o2.equals(strings[i])) {
						event = strings[i];
						// System.out.println(event);
					}
				}
			}
		});
		files.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub

				if (gruop.getToggles().get(0).isSelected()) {
					// private
					System.out.println("private: " + newValue);
					if(newValue != null) {
						getMapFile(newValue);
					}else {
						//ClearMap();
					}
					
				} else {
					System.out.println("public: " + newValue);
					if(newValue != null) {
						getMapDataase(newValue);
					}else {
						//ClearMap();
					}
					
				}
				
			}

		});
		newM.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				ClearMap();
			}
		});
		butprivate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				files.getSelectionModel().clearSelection();
				files.setItems(FXCollections.observableArrayList(readFiles()));
				
				
			}
		});
		butpublic.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				files.getSelectionModel().clearSelection();
				files.setItems(FXCollections.observableArrayList(namesFromDataBase()));
			}
		});
		lang.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				main.lang((int) newValue);
			}


		});

	}
}
