package models;

import java.awt.Color;

import bases.AbstractCell;
import processing.core.PApplet;

public class FCell extends AbstractCell {

	public FCell(int row, int column, PApplet applet) {
		super(row, column, applet);
	}
	
	@Override
	public void show() {
		super.show();
		if (isRevealed()) {
			applet.fill(Color.BLUE.getRGB());
			applet.text("F", x + 20, y + 28);
		}
	}
}
