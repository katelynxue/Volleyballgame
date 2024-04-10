import java.awt.*;

public class Hero {
    //variable declaration section
    public String name;
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;
    public boolean rightPressed;
    public boolean leftPressed;
    public boolean upPressed;
    public boolean downPressed;
    public static final double GRAVITY = 0.1;


    public Hero(int pXpos, int pYpos, int pdx, int pdy, int pwidth, int pheight){
        xpos=pXpos;
        ypos=pYpos;
        dx=pdx;
        dy=pdy;
        width=pwidth;
        height=pheight;
        isAlive=true;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){
        //horizontal
        if(rightPressed==true){
            dx=2;
        }else if(leftPressed==true){
            dx=-2;
        }else {
            dx=0;
        }

        //vertical
        if(downPressed==true){
            dy=2;
        }else if(upPressed==true){
            dy=-2;
        } else {
            dy=0;
        }
        xpos=xpos+dx;
        ypos=ypos-dy;
        rec = new Rectangle(xpos, ypos, width, height);

    }
    public void printInfo(){
        System.out.println("x postition: " + xpos);
        System.out.println("y position: "+ypos);
        System.out.println("speed in x direction: "+dx);
        System.out.println("speed in y direction: "+dy);
        System.out.println("width: "+width);
        System.out.println("height: " +height);
        System.out.println("isAlive: "+isAlive);
    }
}


