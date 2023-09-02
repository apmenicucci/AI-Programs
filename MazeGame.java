import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MazeGame extends JFrame {
    private int posX = 1; // CHaracter's X position
    private int posY = 1; // Character's Y position

    private char[][] maze = {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
        {'#', '#', '#', ' ', '#', ' ', '#', '#', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
        {'#', ' ', '#', '#', '#', '#', ' ', '#', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
        {'#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    public MazeGame() {
        setTitle("Maze Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                moveCharacter(e.getKeyCode());
                repaint();
            }
        });
        setVisible(true);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == '#') {
                g.setColor(Color.BLACK);
                g.fillRect(j * 40, i * 40, 40, 40);
            } else if (i == posY && j == posX) {
                g.setColor(Color.GREEN);
                g.fillRect(j * 40, i * 40, 40, 40);
            } else {
                g.setColor(Color.WHITE);
                g.fillRect(j * 40, i * 40, 40, 40);
            }
            }
            
        }
    }

    private void moveCharacter(int keyCode) {
        int newX = posX;
        int newY = posY;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                newY--;
                break;
            case KeyEvent.VK_DOWN:
                newY++;
                break;
            case KeyEvent.VK_LEFT:
                newX--;
                break;
            case KeyEvent.VK_RIGHT:
                newX++;
                break;
        }

        if (isValidMove(newX, newY)) {
            posX = newX;
            posY = newY;
        }

        if (maze[posY][posX] == ' ') {
            maze[posY][posX] = 'P';
        }

        if (posY == 7 && posX == 8) {
            JOptionPane.showMessageDialog(this, "Congratulations! You found the exit!");
            System.exit(0);
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < maze[0].length && y >= 0 && y < maze.length && maze [y][x] != '#';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MazeGame());
    }
}