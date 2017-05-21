package models;

import java.awt.Color;

import bases.AbstractCell;
import processing.core.PApplet;

public class SafeCell extends AbstractCell {

	private int fCount;
	
	public SafeCell(int row, int column, PApplet applet) {
		super(row, column, applet);
		this.fCount = 0;
	}
	
	public int getFCount() {
		return fCount;
	}
	
	public void setFCount(int fCount) {
		this.fCount = fCount;
	}
	
	@Override
	public void show() {
		super.show();
		if (isRevealed() && fCount > 0) {
			applet.fill(Color.BLUE.getRGB());
			applet.text(fCount, x + 20, y + 28);
		}
	}
}
