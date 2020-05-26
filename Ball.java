package BreakoutGame;

import javax.swing.ImageIcon;

//class for Ball movement and functionality
public class Ball extends Sprite {

    //instance variables for the x and y directions of the ball
    private int xDirection;
    private int yDirection;

    //ball methods
    public Ball() {

        initialBall();
    }

    //the initial state of the ball
    private void initialBall() {

        xDirection = 1;
        yDirection = -1;

        loadImage();
        getImageDimensions();
        resetState();
    }

    //loads the image for the sprite from the resources package
    private void loadImage() {

        var ballImage = new ImageIcon("src/resources/ball.png");
        spriteImage = ballImage.getImage();
    }

    //movement of the ball
    void move() {

        x += xDirection;
        y += yDirection;

        if (x == 0) {

            setxDirection(1);
        }

        if (x == Constants.WIDTH - imageWidth) {

            System.out.println(imageWidth);
            setxDirection(-1);
        }

        if (y == 0) {

            setyDirection(1);
        }
    }

    //resets the state of the ball
    private void resetState() {

        x = Constants.INITIAL_BALL_X;
        y = Constants.INITIAL_BALL_Y;
    }

    //sets the x direction of the ball
    void setxDirection(int x) {

        xDirection = x;
    }

    //sets the y direction of the ball
    void setyDirection(int y) {

        yDirection = y;
    }

    //gets the y direction of the ball
    int getyDirection() {

        return yDirection;
    }
}
