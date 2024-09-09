package columns;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Columns extends JFrame {

	private static final int TIME_SHIFT = 250;
	private static final int MIN_TIME_SHIFT = 200;
	private Model model = new Model();
	private View view = new View();
	private static ScheduledExecutorService timer;
	private Controller controller;
	private static JButton quitButton;
	private static JButton refreshButton;
	public boolean finished = false;

	public Columns() {
		setTitle("Columns Game");
		setSize(300, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		model.initModel();
		model.initMatrixes();
		model.initMembers();
		model.createNewFigure();
		add(view);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				processUserActions(e.getKeyCode());
			}
		});

		startGame();

	}

	private void startGame() {
		view.updateField(model.newField);
		controller = new Controller(model, view);
		restartScheduler(controller, model);
	}

	private void processUserActions(int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_LEFT:
				controller.tryMoveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				controller.tryMoveRight();
				break;
			case KeyEvent.VK_UP :
				controller.rotateUp();
				break;
			case KeyEvent.VK_DOWN:
				controller.rotateDown();
				break;
			case KeyEvent.VK_SPACE:
				controller.dropFigure(model.Fig);
				break;
			case KeyEvent.VK_P:
				controller.pause();
				break;
			case KeyEvent.VK_EQUALS:
				controller.increaseLevel();
				model.firstMove = true;
				restartScheduler(controller, model);
				break;
			case KeyEvent.VK_MINUS:
				controller.decreaseLevel();
				model.firstMove = true;
				restartScheduler(controller, model);
				break;
		}
	}

	private static void restartScheduler(Controller controller, Model model) {
		if (timer != null) {
			timer.shutdown();
		}
		timer = Executors.newSingleThreadScheduledExecutor();
		timer.scheduleAtFixedRate(controller::trySlideDown, 0, 1000 - (model.level - 1L) * 100L, TimeUnit.MILLISECONDS);
	}


	public static void main(String[] args) {
		Columns game = new Columns();
		game.setVisible(true);
	}
}
