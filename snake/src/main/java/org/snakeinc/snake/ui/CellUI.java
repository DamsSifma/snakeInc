package org.snakeinc.snake.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;

import lombok.AllArgsConstructor;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.food.Apple;
import org.snakeinc.snake.model.food.Broccoli;
import org.snakeinc.snake.model.food.Food;
import org.snakeinc.snake.model.snakes.SnakeColor;

@AllArgsConstructor
public class CellUI {

    private Cell cell;
    private int upperPixelX;
    private int upperPixelY;

    public void drawRectangle(Graphics g) {
        g.fillRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getSnakeColor(cell.getSnake().getColor()).darker());
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    }

    public void drawOval(Graphics g) {
        g.fillOval(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    }

    public void drawTriangle(Graphics g) {
        int[] xPoints = {upperPixelX + GamePanel.TILE_PIXEL_SIZE / 2, upperPixelX, upperPixelX + GamePanel.TILE_PIXEL_SIZE};
        int[] yPoints = {upperPixelY, upperPixelY + GamePanel.TILE_PIXEL_SIZE, upperPixelY + GamePanel.TILE_PIXEL_SIZE};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public void draw(Graphics g) {

        if (cell.containsAFood()) {
            g.setColor(getFoodColor(cell.getFood()));
            if (cell.getFood() instanceof Broccoli) {
                drawTriangle(g);
            } else {
                drawOval(g);
            }
        }
        if (cell.containsASnake()) {
            g.setColor(getSnakeColor(cell.getSnake().getColor()));
            drawRectangle(g);
        }
    }

    private Color getSnakeColor(SnakeColor snakeColor) {
        return switch (snakeColor) {
            case GREEN -> Color.GREEN;
            case PINK -> Color.PINK;
            case GRAY -> Color.GRAY;
            case BLUE -> Color.BLUE;
        };
    }

    private Color getFoodColor(Food food) {
        return switch (food) {
            case Apple apple -> {
                if (apple.isPoisoned()) {
                    yield Color.MAGENTA;
                } else {
                    yield Color.RED;
                }
            }
            case Broccoli broccoli -> {
                if (broccoli.isSteamed()) {
                    yield Color.ORANGE;
                } else {
                    yield Color.GREEN;
                }
            }
            default -> Color.YELLOW;
        };
    }
}
