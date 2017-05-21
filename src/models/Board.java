package models;

import java.util.Arrays;

import bases.Cell;
import bases.Component;
import processing.core.PApplet;

public class Board implements Component {
	
	private Cell[][] cells;
	
	private int fCount;
	private int cellsLeft;
	
	private final PApplet applet;
	
	public Board(int row, int column, PApplet applet) {
		this.applet = applet;
		this.cells = new Cell[row][column];
		initCells();
	}
	
	public boolean isWon() {
		return fCount == cellsLeft;
	}
	
	public void markCell(int i, int j) {
		if (cells[i][j].isMarked()) {
			cells[i][j].unmark();
		} else {
			cells[i][j].mark();
		}
	}
	
	public boolean revealCell(int i, int j) {
		cellsLeft--;
		cells[i][j].reveal();
		if (cells[i][j] instanceof SafeCell && ((SafeCell) cells[i][j]).getFCount() == 0) {
			for (int iOffset = -1; iOffset <= 1; iOffset++) {
				for (int jOffset = -1; jOffset <= 1; jOffset++) {
					if (i + iOffset >= 0 && i + iOffset < cells.length && j + jOffset >= 0 && j + jOffset < cells[0].length
							&& cells[i + iOffset][j + jOffset] instanceof SafeCell && !cells[i + iOffset][j + jOffset].isRevealed()) {
						revealCell(i + iOffset, j + jOffset);
					}
				}
			}
		}
		return cells[i][j] instanceof FCell;
	}
	
	public void revealAll() {
		Arrays.stream(cells)
		  	 .flatMap(row -> Arrays.stream(row))
		  	 .filter(cell -> !cell.isRevealed())
		  	 .forEach(cell -> cell.reveal());
	}
	
	public void reset() {
		fCount = cellsLeft = 0;
		initCells();
	}

	@Override
	public void show() {
		Arrays.stream(cells)
			  .flatMap(row -> Arrays.stream(row))
			  .forEach(cell -> cell.show());
	}
	
	private void initCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (Math.random() < .15) {
					cells[i][j] = new FCell(i, j, applet);
					fCount++;
				} else {
					cells[i][j] = new SafeCell(i, j, applet);
				}
				cellsLeft++;
			}
		}
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j] instanceof SafeCell) {
					int fCount = 0;
					for (int iOffset = -1; iOffset <= 1; iOffset++) {
						for (int jOffset = -1; jOffset <= 1; jOffset++) {
							if (i + iOffset >= 0 && i + iOffset < cells.length && j + jOffset >= 0 && j + jOffset < cells[i].length
									&& cells[i + iOffset][j + jOffset] instanceof FCell) {
								fCount++;
							}
						}
					}
					((SafeCell) cells[i][j]).setFCount(fCount);
				}
			}
		}
	}
}
