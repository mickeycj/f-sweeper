package applet;

import java.awt.Color;

import controllers.Game;
import processing.core.PApplet;

public class Applet extends PApplet {

	private Game game;
	
	@Override
	public void settings() {
		size(640, 640);
	}
	
	@Override
	public void setup() {
		game = new Game(this);
		fill(Color.BLACK.getRGB());
		textSize(72);
		text("F-SWEEPER", width / 4 - 25, height / 2 - 2);
	}
	
	@Override
	public void draw() {
		if (game.isStarted()) {
			game.update();
			if (game.isEnded()) {
				fill(Color.WHITE.getRGB());
				rectMode(CENTER);
				rect(width / 2, height / 2 - 20, 360, 160);
				rectMode(CORNER);
				fill(Color.BLACK.getRGB());
				textSize(48);
				if (game.isWon()) {
					text("You Won!", width / 2 + 2, height / 2 - 20);
				} else if (game.isOver()) {
					text("Game Over!", width / 2 + 2, height / 2 - 20);
				}
				textSize(16);
				text("Press Spacebar to Restart", width / 2, height / 2 + 24);
			}
		}
	}
	
	@Override
	public void mousePressed() {
		if (game.isStarted()) {
			game.cellClicked(mouseX, mouseY, mouseButton == RIGHT);
		} else {
			game.start();
			background(212);
		}
	}
	
	@Override
	public void keyReleased() {
		if (game.isEnded() && key == ' ') {
			game.restart();
			game.start();
			background(212);
		}
	}
}
