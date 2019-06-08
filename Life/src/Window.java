import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
public final class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean isRunning;
	private Canvas canvas;
	private Graphics graphics;
	private BufferedImage bitmap;
	private int cellSize = 700;
	private Cell[][] grid = new Cell[cellSize][cellSize];
	public Window() {
		for (int x = 0; x < cellSize; x++) {
			for (int y = 0; y < cellSize; y++) {
				grid[x][y] = new Cell(Math.random() * 100 > 40 ? true : false);//left was true
				//pattern glider:
				if (y == 5 && (x == 2 || x == 3)) {
					grid[x][y].isLiving = true; //force test life
				}
				if (y == 4 && (x == 3 || x == 4)) {
					grid[x][y].isLiving = true;
				}
				if (y == 3 && x == 2) {
					grid[x][y].isLiving = true;
				}
				
				//glider gun creator:
				if ((y >= 35 && y <= 36 && x >= 11 && x <= 12) || (y >= 33 && y <= 34 && x >= 33 && x <= 34)) {
					grid[x][y].isLiving = true;
				}
				if (y >= 35 && y <= 37 && x == 15) {
					grid[x][y].isLiving = true;
				}
				if (x == 16 && (y == 34 || y == 38)) {
					grid[x][y].isLiving = true;
				}
				if (x >= 17 && x <= 18 && (y == 33 || y == 39)) {
					grid[x][y].isLiving = true;
				}
				if (x == 19 && y == 36) {
					grid[x][y].isLiving = true;
				}
				if (x == 20 && (y == 34 || y == 38)) {
					grid[x][y].isLiving = true;
				}
				if (y >= 35 && y <= 37 && x == 21) {
					grid[x][y].isLiving = true;
				}
				if (x == 22 && y == 36) {
					grid[x][y].isLiving = true;
				}
				if (x >= 24 && x <= 25 && y >= 33 && y <= 35) {
					grid[x][y].isLiving = true;
				}
				if (x == 26 && (y == 32 || y == 36)) {
					grid[x][y].isLiving = true;
				}
				if (x == 28 && ((y >= 31 && y <= 32) || (y >= 36 && y <= 37))) {
					grid[x][y].isLiving = true;
				}
			}
		}
		bitmap = new BufferedImage(1280, 720, 1);
		for (int x = 0; x < 1280; x++) {
			for (int y = 0; y < 720; y++) {
				bitmap.setRGB(x, y, 0xffffff);
			}
		}
		canvas = new Canvas();
		canvas.setSize(1280, 720);
		canvas.setBackground(Color.WHITE);
		canvas.setFocusable(false);
		this.add(canvas);
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(1280,720));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		canvas.createBufferStrategy(3);
		draw();
		isRunning = true;
		draw(); //test after gen 0
		while (isRunning) {
			runProgram();
		}
	}
	int counter = 0;
	private void runProgram() {
		counter++;
		if (counter > 1) {
			draw();
			counter = 0;
		}
		BufferStrategy b = canvas.getBufferStrategy();
		graphics = b.getDrawGraphics();
		graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphics.drawImage(bitmap, (canvas.getWidth()-1280)/2, (canvas.getHeight()-720)/2, null);
		b.show();
		graphics.dispose();
	}
	private int sizeX = 1280 / cellSize;
	private int sizeY = 720 / cellSize;
	private Cell[][] tempGrid = new Cell[cellSize][cellSize];
	private void draw() {
		for (int xx = 0; xx < cellSize; xx++) {
			for (int yy = 0; yy < cellSize; yy++) {
				tempGrid[xx][yy] = new Cell(grid[xx][yy].isLiving); //make a copy of grid
			}
		}
		for (int x = 0; x < cellSize; x++) {
			for (int y = 0; y < cellSize; y++) {
				if (isRunning) {
					//logic: note: read from copy of grid (tempGrid) and only write to original grid
					int neighbours = checkAdjacentLiving(x, y);
					//rule 1: Any live cell with fewer than two live neighbours dies, as if by underpopulation.
					if (tempGrid[x][y].isLiving && neighbours < 2) {
						grid[x][y].isLiving = false;
					}
					//rule 2: Any live cell with two or three live neighbours lives on to the next generation.
					if (tempGrid[x][y].isLiving && (neighbours == 2 || neighbours == 3)) {
						grid[x][y].isLiving = true;
					}
					//rule 3: Any live cell with more than three live neighbours dies, as if by overpopulation.
					if (tempGrid[x][y].isLiving && neighbours > 3) {
						grid[x][y].isLiving = false;
					}
					//rule 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
					if (tempGrid[x][y].isLiving == false && neighbours == 3) {
						grid[x][y].isLiving = true;
					}
					//end logic
				}
				int colour = grid[x][y].isLiving ? grid[x][y].colour : 0xffffff;
				for (int i = 0; i < sizeX; i++) {
					for (int j = 0; j < sizeY; j++) {
						bitmap.setRGB(sizeX * x + i, (sizeY * y + j), colour);
					}
				}
			}
		}
	}
	private int checkAdjacentLiving(int x, int y) {
		int count = 0;
		if (x+1 < cellSize && tempGrid[x+1][y].isLiving) {
			count++;
		}
		if (x-1 > -1 && tempGrid[x-1][y].isLiving) {
			count++;
		}
		if (y-1 > -1 && tempGrid[x][y-1].isLiving) {
			count++;
		}
		if (y+1 < cellSize && tempGrid[x][y+1].isLiving) {
			count++;
		}
		if (x-1 > -1 && y-1 > -1 && tempGrid[x-1][y-1].isLiving) {
			count++;
		}
		if (y-1 > -1 && x+1 < cellSize && tempGrid[x+1][y-1].isLiving) {
			count++;
		}
		if (x-1 > -1 && y+1 < cellSize && tempGrid[x-1][y+1].isLiving) {
			count++;
		}
		if (x+1 < cellSize && y+1 < cellSize && tempGrid[x+1][y+1].isLiving) {
			count++;
		}
		return count;
	}
}