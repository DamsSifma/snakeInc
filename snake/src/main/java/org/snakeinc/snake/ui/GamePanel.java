package org.snakeinc.snake.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.MalnutritionException;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.Direction;
import org.snakeinc.snake.model.Game;
import org.snakeinc.snake.model.snakes.HealthState;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    public static final int TILE_PIXEL_SIZE = 20;
    public static final int GAME_PIXEL_WIDTH = TILE_PIXEL_SIZE * GameParams.TILES_X;
    public static final int GAME_PIXEL_HEIGHT = TILE_PIXEL_SIZE * GameParams.TILES_Y;

    private Timer timer;
    private Game game;
    private boolean running = false;
    private Direction direction = Direction.RIGHT;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GAME_PIXEL_WIDTH, GAME_PIXEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
        startGame();
    }

    private void startGame() {
        game = new Game();
        timer = new Timer(100, this);
        timer.start();
        running = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            UIUtils.draw(g, game);
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (GAME_PIXEL_WIDTH - metrics.stringWidth("Game Over")) / 2, GAME_PIXEL_HEIGHT / 2);
        // show score below
        String scoreText = "Score: " + game.getScore().getPoints();
        g.drawString(scoreText, (GAME_PIXEL_WIDTH - metrics.stringWidth(scoreText)) / 2, GAME_PIXEL_HEIGHT / 2 + 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            try {
                game.iterate(direction);
            } catch (OutOfPlayException | SelfCollisionException | MalnutritionException exception ) {
                timer.stop();
                running = false;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        HealthState healthState = game.getSnake().getHealthState();
        Direction requested;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                requested = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                requested = Direction.RIGHT;
                break;
            case KeyEvent.VK_UP:
                requested = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                requested = Direction.DOWN;
                break;
            default:
                return;
        }

        Direction adjusted = healthState.adjustDirection(requested);

        if (!isOpposite(adjusted, direction)) {
            direction = adjusted; // on garde la nouvelle direction seulement si elle n'est pas opposée à l'actuelle
        }
    }

    private boolean isOpposite(Direction d1, Direction d2) {
        return (d1 == Direction.LEFT && d2 == Direction.RIGHT)
                || (d1 == Direction.RIGHT && d2 == Direction.LEFT)
                || (d1 == Direction.UP && d2 == Direction.DOWN)
                || (d1 == Direction.DOWN && d2 == Direction.UP);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
