//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable, KeyListener {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    //declare screen/level booleans
    public boolean startScreen=true;
    public boolean isPlaying=false;
    public boolean gameOver1;
    public boolean gameOver2;
    public boolean endScreen=true;

    //Declare the objects used in the program below
    /** STEP 1 declare your object and give it a name **/
    public Volleyball volleyball;
    public Hero player1;
    public Hero player2;
    public boolean player1IsIntersectingVolleyball;
    public boolean player2IsIntersectingVolleyball;
    public Obstacle[] basketballs;

    /** STEP 2 declare an image for your object**/
    public Image volleyballPic;
    public Image volleyballCourt;
    public Image player1Pic;
    public Image player2Pic;
    public Image basketballPic;
    public Image gameOver1Pic;
    public Image gameOver2Pic;
    public boolean isBasketballIntersectingPlayer1;
    public boolean isBasketballIntersectingPlayer2;



    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below
        //for each object that has a picture, load in images as well
        /** STEP 3 construct a specific Hero **/
        volleyball = new Volleyball(300,200,3,3,40,40);
        player1 = new Hero(100,300,5,5,200,190);
        player2 = new Hero(600,300,5,5,200,258);
        basketballs = new Obstacle[5];
        for(int i=0; i<basketballs.length; i=i+1){
            int randY = (int)(Math.random()*400);
            int randDx = (int)(Math.random()*800);
            basketballs[i]= new Obstacle(randDx,randY,1,1);
        }


        /** STEP 4 load in the image for your object **/
        volleyballCourt = Toolkit.getDefaultToolkit().getImage("volleyballCourt.jpg");
        volleyballPic = Toolkit.getDefaultToolkit().getImage("volleyballPic.png");
        player1Pic = Toolkit.getDefaultToolkit().getImage("player1.png");
        player2Pic = Toolkit.getDefaultToolkit().getImage("player2.png");
        basketballPic = Toolkit.getDefaultToolkit().getImage("basketball.jpg");
        gameOver1Pic = Toolkit.getDefaultToolkit().getImage("gameOver1.png");
        gameOver2Pic = Toolkit.getDefaultToolkit().getImage("gameOver2.png");


    }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            collisions();  //checks for rec intersections
            checkGameOver();
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
        }
    }

    public void moveThings() {
        player1.move();
        player2.move();
        volleyball.move();
        volleyball.bouncingMove();
        for(int i=0;i<basketballs.length;i=i+1){
            basketballs[i].wrappingMove();
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        if(startScreen==true){
            g.drawString("press space bar to start",400,300);
        }

        if(isPlaying==true) {

            /** STEP 5 draw the image of your object to the screen **/
            g.drawImage(volleyballCourt,0,0,WIDTH,HEIGHT,null);
            g.drawImage(volleyballPic, volleyball.xpos, volleyball.ypos, volleyball.width, volleyball.height, null);
            g.drawImage(player1Pic, player1.xpos, player1.ypos, player1.width, player1.height, null);
            g.drawImage(player2Pic, player2.xpos, player2.ypos, player2.width, player2.height, null);


            for(int i=0; i<basketballs.length; i=i+1) {
                g.drawImage(basketballPic, (int)(basketballs[i].xpos),(int)(basketballs[i].ypos), basketballs[i].width, basketballs[i].height,null);
            }

        }
        if(gameOver1==true){
            g.drawImage(gameOver1Pic,0,0,WIDTH,HEIGHT,null);
            System.out.println("game over 1 is true");
        }
        if(gameOver2==true){
            g.drawImage(gameOver2Pic,0,0,WIDTH,HEIGHT,null);
            System.out.println("game over 2 is true");

        }

        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();

        bufferStrategy.show();
    }

    public void collisions() {
        if (player1.rec.intersects(volleyball.rec) && player1IsIntersectingVolleyball == false) {
            player1IsIntersectingVolleyball = true;
            volleyball.dx = -volleyball.dx;
            volleyball.dy = -volleyball.dy;
        }
        if (player1.rec.intersects(volleyball.rec) && player1IsIntersectingVolleyball == true) {
            player1IsIntersectingVolleyball = false;
        }
        if (player2.rec.intersects(volleyball.rec) && player2IsIntersectingVolleyball == false) {
            player2IsIntersectingVolleyball = true;
            volleyball.dx = -volleyball.dx;
            volleyball.dy = -volleyball.dy;
        }
        if (player2.rec.intersects(volleyball.rec) && player2IsIntersectingVolleyball == true) {
            player2IsIntersectingVolleyball = false;
        }
        for(int i=0;i<basketballs.length;i=i+1) {
            if (player1.rec.intersects(basketballs[i].rec) && isBasketballIntersectingPlayer1 == false) {
                isBasketballIntersectingPlayer1 = true;
              //  System.out.println("ouch");
                player1.width -= 2;
                player1.height -= 2;
                System.out.println("player 1 width: " +player1.width);
            }
            if (player1.rec.intersects(basketballs[i].rec) == false) {
                isBasketballIntersectingPlayer1 = false;
            }
        }
        for(int i=0;i<basketballs.length;i=i+1) {
            if (player2.rec.intersects(basketballs[i].rec) && isBasketballIntersectingPlayer2 == false) {
                isBasketballIntersectingPlayer2 = true;
                // System.out.println("ouch");
                player2.width -= 2;
                player2.height -= 2;
            }
            if (player2.rec.intersects(basketballs[i].rec) == false) {
                isBasketballIntersectingPlayer2 = false;
            }
        }


    }
    public void checkGameOver(){
        if(player1.width<11 ){
            System.out.println("1 oh no");
          // player1.isAlive=false;
//            player1.dx=0;
//            player1.dy=0;
            gameOver1=true;
        }
        if(player2.width<15 ){
            System.out.println("2 oh no");
         //   player2.isAlive=false;
         //   player2.dx=0;
          //  player2.dy=0;
            gameOver2=true;
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);
        panel.add(canvas);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode=e.getKeyCode();
        System.out.println("Key: "+key+" , KeyCode: "+ keyCode);
        if(keyCode==68){
            player1.rightPressed=true;
        }
        if(keyCode==65){
            player1.leftPressed=true;
        }
        if(keyCode==87){
            player1.downPressed=true;
        }
        if(keyCode==83){
            player1.upPressed=true;
        }
        if(keyCode==39){
            player2.rightPressed=true;
        }
        if(keyCode==37){
            player2.leftPressed=true;
        }
        if(keyCode==38){
            player2.downPressed=true;
        }
        if(keyCode==40){
            player2.upPressed=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key=e.getKeyChar();
        int keyCode=e.getKeyCode();

        //spacebar to start
        if(keyCode==32){
            startScreen=false;
            isPlaying=true;
        }

        //user control
        if(keyCode==68){
            player1.rightPressed=false;
        }
        if(keyCode==65){
            player1.leftPressed=false;
        }
        if(keyCode==87){
            player1.downPressed=false;
        }
        if(keyCode==83){
            player1.upPressed=false;
        }
        if(keyCode==39){
            player2.rightPressed=false;
        }
        if(keyCode==37){
            player2.leftPressed=false;
        }
        if(keyCode==38){
            player2.downPressed=false;
        }
        if(keyCode==40){
            player2.upPressed=false;
        }
    }
}
