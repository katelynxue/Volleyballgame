import java.awt.*;

public class Obstacle {
    public double xpos;
    public double ypos;
    public double dx;
    public double dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;

    public Obstacle(double pXpos, double pYpos, double pdx, double pdy){
        xpos=pXpos;
        ypos=pYpos;
        dx=pdx;
        dy=pdy;
        width=50;
        height=50;
        isAlive=true;
        rec = new Rectangle((int)xpos, (int)ypos, width, height);
    }
    public Obstacle(){
        xpos=100;
        ypos=200;
        dx=2;
        dy=3;
        width=50;
        height=50;
        isAlive=true;

    }

    public void move(){
        xpos=xpos+dx;
        ypos=ypos-dy;
        rec = new Rectangle((int)xpos, (int)ypos, width, height);
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
        rec = new Rectangle((int)xpos,(int)ypos, width, height);
    }
    public void wrappingMove(){
        if(xpos>1000) {
            xpos = 0;
        }
        if(xpos<0){
            xpos=1000;
        }
        if(ypos>700){
            ypos=0;
        }
        if(ypos<0){
            ypos=700;
        }
        ypos=ypos+dy;
        xpos=xpos+dx;
        rec = new Rectangle((int)xpos,(int) ypos, width, height);
    }
}

