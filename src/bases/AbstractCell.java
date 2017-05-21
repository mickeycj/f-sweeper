package bases;

import java.awt.Color;

import processing.core.PApplet;

public abstract class AbstractCell implements Cell {

	protected final float x;
	protected final float y;
	
	private boolean marked;
	private boolean revealed;
	
	protected final PApplet applet;
	
	protected AbstractCell(int row, int column, PApplet applet) {
		this.x = column * 40;
		this.y = row * 40;
		this.applet = applet;
	}

	@Override
	public boolean isMarked() {
		return marked;
	}
	
	@Override
	public boolean isRevealed() {
		return revealed;
	}
	
	@Override
	public void mark() {
		if (!revealed) {
			marked = true;
		}
	}
	
	@Override
	public void unmark() {
		if (!revealed) {
			marked = false;
		}
	}
	
	@Override
	public void reveal() {
		revealed = true;
		marked = false;
	}
	
	@Override
	public void show() {
		applet.textSize(16);
		applet.textAlign(PApplet.CENTER);
		applet.stroke(Color.BLACK.getRGB());
		if (!revealed) {
			applet.fill(212);
			applet.rect(x, y, 40, 40);
			if (marked) {
				applet.fill(Color.BLUE.getRGB());
				applet.text("M", x + 20, y + 28);
			}
		} else {
			applet.fill(Color.LIGHT_GRAY.getRGB());
			applet.rect(x, y, 40, 40);
		}
	}
}
