package BreakoutGame;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

//class for the paddle movement and functionality
public class Paddle extends Sprite  {

    private int dx;

    public Paddle() {

        initPaddle();
    }

    private void initPaddle() {

        loadImage();
        getImageDimensions();

        resetState();
    }

    private void loadImage() {

        var paddleImage = new ImageIcon("src/resources/paddle.png");
        spriteImage = paddleImage.getImage();
    }

    void move() {

        x += dx;

        if (x <= 0) {

            x = 0;
        }

        if (x >= Constants.WIDTH - imageWidth) {

            x = Constants.WIDTH - imageWidth;
        }
    }

    void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 1;
        }
    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }

    private void resetState() {

        x = Constants.INITIAL_PADDLE_X;
        y = Constants.INITIAL_PADDLE_Y;
    }
}
