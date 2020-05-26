package BreakoutGame;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {

    int x;
    int y;
    int imageWidth;
    int imageHeight;
    Image spriteImage;

    protected void setX(int x) {

        this.x = x;
    }

    int getX() {

        return x;
    }
    //what is a protected method
    protected void setY(int y) {

        this.y = y;
    }

    int getY() {

        return y;
    }

    int getImageWidth() {

        return imageWidth;
    }

    int getImageHeight() {

        return imageHeight;
    }

    Image getImage() {

        return spriteImage;
    }

    Rectangle getRect() {

        return new Rectangle(x, y,
                spriteImage.getWidth(null), spriteImage.getHeight(null));
    }

    void getImageDimensions() {

        imageWidth = spriteImage.getWidth(null);
        imageHeight = spriteImage.getHeight(null);
    }
}
