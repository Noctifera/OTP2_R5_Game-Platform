package modal;

import java.awt.Point;

public class Node {
	private Point id;
	private double gScore; //cost from start
	private double fScore; //cost to target
	private double hscore = fScore + gScore;// combined cost of both 
	
	public Node(Point id, double gScore, double fScore) {
		this.id = id;
		this.gScore = gScore;
		this.fScore = fScore;
	}


	public Point getId() {
		return id;
	}


	public void setId(Point id) {
		this.id = id;
	}


	public double getgScore() {
		return gScore;
	}


	public void setgScore(int gScore) {
		this.gScore = gScore;
	}


	public double getfScore() {
		return fScore;
	}


	public void setfScore(int fScore) {
		this.fScore = fScore;
	}


	public double getHscore() {
		return hscore;
	}
}
