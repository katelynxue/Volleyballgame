import java.awt.*;

public class Volleyball {
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;

    public Volleyball(int pXpos, int pYpos, int pdx, int pdy, int pwidth, int pheight){
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
        xpos=xpos+dx;
        ypos=ypos-dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void bouncingMove(){
        if(xpos>1000-width){
            dx=-dx;
        }
        if(xpos<0){
            dx=-dx;
        }
        if(ypos>700-height){
            dy=-dy;
        }
        if(ypos<0){
            dy=-dy;
        }
        rec = new Rectangle(xpos, ypos, width, height);
    }
}
