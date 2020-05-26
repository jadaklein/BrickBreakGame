package BreakoutGame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;

public class Brick extends Sprite {

    private boolean destroyed;

    public Brick(int x, int y) {

        initBrick(x, y);
    }

    private void initBrick(int x, int y) {

        this.x = x;
        this.y = y;

        destroyed = false;

        loadImage();
        getImageDimensions();
    }


    private void loadImage() {
        //File imageFile = new File("https://pngimage.net/wp-content/uploads/2018/05/blue-rectangle-png.png");
        //BufferedImage image = ImageIO.read(imageFile);
        var brickImage = new ImageIcon("src/resources/brick.png");
        spriteImage = brickImage.getImage();
    }

    boolean isDestroyed() {

        return destroyed;
    }

    void setDestroyed(boolean val) {

        destroyed = val;
    }
}
