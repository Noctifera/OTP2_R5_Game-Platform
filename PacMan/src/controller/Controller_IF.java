package controller;

import javafx.scene.input.KeyEvent;

public interface Controller_IF {

	public void move(KeyEvent event); // nuolinäppäimiä käyttäen liikutaan ylös alas vasemalle oikealle. liikeen peristeella määriteetään uusi posia.
	public void start(); //kentän alustus
}
