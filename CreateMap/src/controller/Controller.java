package controller;

import java.awt.Point;

import javafx.scene.input.MouseEvent;
import modal.Draw;
import modal.Map;

public class Controller {
	private Draw draw;
	private Map map;

	private String[] strings;

	public Controller(Draw draw, Map map, String[] strings) {
		this.draw = draw;
		this.map = map;
		this.strings = strings;
	}

	public void beginning() {
		map.initializeMap();
		draw.clear();
		draw.drawGrid();
		draw.drawOuterBound();
	}

	public void draw(MouseEvent e, String s) {
		int mouseX = (int) e.getX();
		int mouseY = (int) e.getY();

		while (mouseX % 40 != 0) {
			mouseX = mouseX - 1;
			if (mouseX % 40 == 0) {
				break;
			}

		}
		while (mouseY % 40 != 0) {
			mouseY = mouseY - 1;
			if (mouseY % 40 == 0) {
				break;
			}
		}
		Point point = new Point(mouseX, mouseY);
		//System.out.println(point);
		if (s.equals(strings[4]) || s.equals(strings[5])) {
			map.onlyOne(point, s);
		}
		map.addToMap(point, s);
		draw.drawFullMap();
	}

	public void saveMap(String fileName) {
		if (!fileName.equals("")) {
			if (map.mapContains().equals("")) {
				map.SaveMapToFile(fileName);
			}else{
				System.out.println("Missing items:"+map.mapContains());
			}
		} else {
			System.out.println("No File Name");
		}

	}

	public void getMap(String fileName) {
		map.GetMapFromFile(fileName);
		draw.clear();
		draw.drawGrid();
		draw.drawFullMap();
	}

	public String[] readFiles() {
		String[] fileNames = new String[map.allFiles().length];
		for (int i = 0; i < map.allFiles().length; i++) {
			fileNames[i] = map.allFiles()[i].getName();
		}
		for (String s : fileNames) {
			System.out.println(s);
		}
		return fileNames;
	}
}
