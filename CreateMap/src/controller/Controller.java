package controller;

import java.awt.Point;
import java.io.File;

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
		System.out.println(point);
		if (s.equals(strings[0])) {
			draw.drawDot(point);
			map.addToMap(point, strings[0]);

		} else if (s.equals(strings[1])) {
			draw.drawLargeDot(point);
			map.addToMap(point, strings[1]);

		} else if (s.equals(strings[2])) {
			draw.drawWall(point);
			map.addToMap(point, strings[2]);

		} else if (s.equals(strings[3])) {
			draw.clearTile(point);
			map.addToMap(point, strings[3]);

		} else if(s.equals(strings[4])){
			map.onlyOne(point, strings[4]);
		} else if(s.equals(strings[5])){
			map.onlyOne(point, strings[5]);
		}
	}
	public void saveMap(String fileName){
		map.SaveMapToFile(fileName);
	}
	public void getMap(String fileName){
		map.GetMapFromFile(fileName);
	}
	public String[] readFiles(){
		String[] fileNames = new String[map.allFiles().length];
		for(int i = 0; i<map.allFiles().length;i++){
			fileNames[i] = map.allFiles()[i].getName();
		}
		return fileNames;
	}
}
