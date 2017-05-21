package controllers;

import models.Board;
import processing.core.PApplet;

public class Game {

	private Board board;
	
	private boolean started;
	private boolean won;
	private boolean over;
	
	public Game(PApplet applet) {
		this.board = new Board(applet.height / 40, applet.width / 40, applet);
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public boolean isWon() {
		return won;
	}
	
	public boolean isOver() {
		return over;
	}
	
	public boolean isEnded() {
		return won || over;
	}
	
	public void start() {
		started = true;
	}
	
	public void update() {
		board.show();
	}
	
	public void restart() {
		board.reset();
		started = true;
		won = over = false;
	}
	
	public void cellClicked(int mouseX, int mouseY, boolean isMarking) {
		if (isMarking) {
			board.markCell(mouseY / 40, mouseX / 40);
		} else if (board.revealCell(mouseY / 40, mouseX / 40)) {
			board.revealAll();
			over = true;
		} else if (!over && board.isWon()) {
			won = true;
		}

	}
}
