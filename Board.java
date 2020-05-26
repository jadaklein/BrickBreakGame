//package BreakoutGame;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//class for the board
public class Board extends JPanel implements Playable {

    //JLabel scoreLabel = new JLabel("Score: 0");

    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean inGame = true;
    private int score;
    private String scoreMessage = "Your Score: ";
    private String playerName;
    private String playerMessage = "Thanks for playing ";

    //@Override
    //public boolean playGame() {
    //    return false;
    //}

    //@Override
    //public void endGame() {

    //}

    //public void openGame() {

    //}

    public String getScore() {
        return "Your score: " + String.valueOf(score);
    }

    public String returnUsername() {
        return playerName;
    }
//////////////////////////////////////
    public Board() {

        openGame();
        initBoard();
    }


    public void someoneScored()
    {
        score++;
       // scoreLabel.setBounds(5, 5, 100, 100);
       // scoreLabel.setText("Score: " + score);
    }

    public void openGame() {//initBoard, private
        //JLabel scoreLabel = new JLabel("Score: 0")
        playGame();
    }
        //scoreLabel.setBounds(5, 5, 100, 100);
        private void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        //playGame();
    }

    public boolean playGame() {//gameInit

        playerName = JOptionPane.showInputDialog(null, "Enter your name", "Username", JOptionPane.QUESTION_MESSAGE);
        score = 0;
        bricks = new Brick[Constants.BRICK_NUMBER];

        ball = new Ball();
        paddle = new Paddle();

        int k = 0;

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 8; j++) {

                bricks[k] = new Brick(j * 40 + 40, i * 10 + 60);//30, 50
                k++;
            }
        }

        timer = new Timer(Constants.PERIOD, new GameCycle());
        timer.start();
        return true;
    }


    private void drawScore(Graphics2D g2d){
        g2d.setColor(Color.black);
        g2d.drawString("Score: " + String.valueOf(score), 10, 20);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {

            drawObjects(g2d);
            drawScore(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    //draws the different
    private void drawObjects(Graphics2D g2d) {

        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < Constants.BRICK_NUMBER; i++) {

            if (!bricks[i].isDestroyed()) {

                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
                        bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {

        var font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (Constants.WIDTH - fontMetrics.stringWidth(message)) / 2,
                Constants.WIDTH / 2);
        scoreMessage += "" + String.valueOf(score);
        g2d.drawString(scoreMessage,
                (Constants.WIDTH - fontMetrics.stringWidth(scoreMessage)) / 2,
                Constants.WIDTH / 3);
        playerMessage += returnUsername();
        g2d.drawString(playerMessage,
                (Constants.WIDTH - fontMetrics.stringWidth(playerMessage)) / 2,
                Constants.WIDTH / 4);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            paddle.keyPressed(e);
        }
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {

        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    public void endGame() {

        inGame = false;
        timer.stop();
        //return true;

    }

    private void checkCollision() {

        if (ball.getRect().getMaxY() > Constants.BOTTOM) {

            endGame();
        }

        for (int i = 0, j = 0; i < Constants.BRICK_NUMBER; i++) {

            if (bricks[i].isDestroyed()) {
                //score++;
                j++;
            }

            if (j == Constants.BRICK_NUMBER) {
                //score++;
                message = "Victory";
               // message = "Play again?";
               // JButton yes = new JButton("Yes");
               // yes.setBounds(100,100,140, 40);
               // yes.addMouseListener(null);

               // JButton no = new JButton("No");
              //  no.setBounds(280, 100, 140, 40);
               endGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {

                ball.setxDirection(-1);
                ball.setyDirection(-1);
            }

            if (ballLPos >= first && ballLPos < second) {

                ball.setxDirection(-1);
                ball.setyDirection(-1 * ball.getyDirection());
            }

            if (ballLPos >= second && ballLPos < third) {

                ball.setxDirection(0);
                ball.setyDirection(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {

                ball.setxDirection(1);
                ball.setyDirection(-1 * ball.getyDirection());
            }

            if (ballLPos > fourth) {

                ball.setxDirection(1);
                ball.setyDirection(-1);
            }
        }

        for (int i = 0; i < Constants.BRICK_NUMBER; i++) {

            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {

                    if (bricks[i].getRect().contains(pointRight)) {

                        ball.setxDirection(-1);
                    } else if (bricks[i].getRect().contains(pointLeft)) {

                        ball.setxDirection(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {

                        ball.setyDirection(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {

                        ball.setyDirection(-1);
                    }

                    bricks[i].setDestroyed(true);
                    someoneScored();
                    //score++;
                }
            }
        }
    }
}
