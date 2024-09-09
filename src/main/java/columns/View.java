package columns;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {

	public static final Color[] COLORS = {
			Color.black, Color.cyan, Color.blue, Color.red, Color.green,
			Color.yellow, Color.pink, Color.magenta, Color.white
	};

	private static final int TOP_BORDER = 2;
	private static final int LEFT_BORDER = 2;
	private static final int BOX_SIZE = 25;

	private int[][] field;
	private Figure currentFigure;
	private int level = 1;
	private int score;
	private boolean finished = false;


	public View() {
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("Arial", Font.PLAIN, 12));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawField(g);
		drawFigure(g);
		showLevel(g);
		showScore(g);
		if(finished) drawGameOver(g);
	}

	public void updateField(int[][] field) {
		this.field = field;
		repaint();
	}

	public void updateFigure(Figure figure) {
		this.currentFigure = figure;
		repaint();
	}

	public void updateLevel(int level) {
		this.level = level;
		repaint();
	}

	public void updateScore(int score) {
		this.score = score;
		repaint();
	}

	private void drawField(Graphics gr) {
		if (field == null) return;
		for (int i = 1; i <= Model.Depth; i++) {
			for (int j = 1; j <= Model.Width; j++) {
				drawBox(gr, j, i, field[j][i]);
			}
		}
	}

	private void drawFigure(Graphics gr) {
		if (currentFigure == null) return;
		int[] c = currentFigure.getCells();
		drawBox(gr, currentFigure.x, currentFigure.y, c[0]);
		drawBox(gr, currentFigure.x, currentFigure.y + 1, c[1]);
		drawBox(gr, currentFigure.x, currentFigure.y + 2, c[2]);
	}

	private void drawBox(Graphics gr, int x, int y, int c) {
		if (c == 0) {
			gr.setColor(Color.black);
		} else {
			gr.setColor(COLORS[c]);
		}
		gr.fillRect(LEFT_BORDER + x * BOX_SIZE - BOX_SIZE, TOP_BORDER + y * BOX_SIZE - BOX_SIZE, BOX_SIZE, BOX_SIZE);
		gr.setColor(Color.black);
		gr.drawRect(LEFT_BORDER + x * BOX_SIZE - BOX_SIZE, TOP_BORDER + y * BOX_SIZE - BOX_SIZE, BOX_SIZE, BOX_SIZE);
	}

	private void showLevel(Graphics gr) {
		gr.setColor(Color.black);
		gr.drawString("Level: " + level, LEFT_BORDER + 100, 400);
	}

	private void showScore(Graphics gr) {
		gr.setColor(Color.black);
		gr.drawString("Score: " + score, LEFT_BORDER, 400);
	}

	private void drawGameOver(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Arial", Font.BOLD, 25));
		FontMetrics metrics = graphics.getFontMetrics();
		String message = "Game Over";
		int x = 21;
		int y = 100;
		graphics.drawString(message, x, y);
	}

	public void finish() {
		this.finished = true;
		repaint();
	}


}
