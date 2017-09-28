package application;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public interface PacMan_gui_IF {
	public void init(); // alustetaan oliot
	
	public void inisThread(); //alustetaan kaikki saikeet
	
	public HBox bottomDataPane(); //tietokannan ulos tulo
	
	public void listLooper(GridPane grid, ArrayList<String> list, String text); //bottomDataPane k‰ytt‰m‰ funktio
	
	public HBox topHorizonatalBox(); //elkit ja score 
	
	public void combine(); // yhdistet‰‰n kaikki n‰kyviin
	
	public void setLives(int livs); // p‰ivitet‰‰n ekit
	
	public void setScore(int score); // p‰ivitet‰‰n pisteet
	
	

}
