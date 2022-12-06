import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gamepanel extends JPanel implements KeyListener, ActionListener {

    ImageIcon snaketitle;

    {
        snaketitle = new ImageIcon(getClass().getResource("snaketitleP.png"));
    }
    ImageIcon downface = new ImageIcon(getClass().getResource("bottomP.png"));
    ImageIcon topface = new ImageIcon(getClass().getResource("topP.png"));
    ImageIcon leftface = new ImageIcon(getClass().getResource("leftP.png"));
    ImageIcon rightface = new ImageIcon(getClass().getResource("rightP.png"));
    ImageIcon stomach = new ImageIcon(getClass().getResource("stomachP.png"));
    ImageIcon enemy = new ImageIcon(getClass().getResource("enemyP.png"));

    //create 4 variables to decide which head of snake to select in which situation

    boolean left=false;
    boolean right = true; //initially it will face right side
    boolean top=false;
    boolean down =false;
    boolean gameover=false;

    int xpos[]={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    int ypos[]={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    Random random = new Random();
    int xenemy=150,yenemy=200;
    // our playing area size is 750x750 so treating as 2d matrix create 2 arrays of length 750

    int snakelengthx[] = new int[750];
    int snakelengthy[] = new int[750];
    int move=0;
    int snakeLength=3;   //initialy we r keeping snake length as 3
    int score=0;
    Timer time;
    int delay=150;
    Gamepanel(){
        addKeyListener(this);
        setFocusable(true);
        time=new Timer(delay,this);
        time.start();
    }
    public void newEnemy(){
        xenemy=xpos[random.nextInt(34)];
        yenemy=ypos[random.nextInt(23)];
        for(int i=snakeLength-1;i>=0;i--)
        {
            if(snakelengthx[i]==xenemy && snakelengthy[i]==yenemy)
            {
                newEnemy();
            }
        }
    }
    public void enemyCollision()
    {
        if(snakelengthx[0]==xenemy && snakelengthy[0]==yenemy)
        {
            newEnemy();
            snakeLength++;
            score++;
            snakelengthx[snakeLength-1]=snakelengthx[snakeLength-2];
            snakelengthy[snakeLength-1]=snakelengthy[snakeLength-2];

        }
    }
    public void bodyCollision(){
        for(int i=snakeLength-1;i>0;i--)
        {
            if((snakelengthx[i]==snakelengthx[0]) && (snakelengthy[i]==snakelengthy[0]))
            {
                time.stop();
                gameover=true;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        g.drawRect(24,74,851,576);
        snaketitle.paintIcon(this,g,25,11);
        g.setColor(Color.black);                    //setting black color for playing area
        g.fillRect(25,75,851,576);// giving black color to playing area
        if(move==0) //when game is started
        {
            snakelengthx[0]=100;    //head of snake at 100px
            snakelengthx[1]=75;     //stomach at 75
            snakelengthx[2] = 50;   //stomach 2 at 50px

            snakelengthy[0]=100;    //head 100px from top of rect
            snakelengthy[1]=100;    //stomach1 100px from top of rect
            snakelengthy[2]=100;    //stomach2 100px from top of rect
            move++;
        }
        if(left)
        {
            leftface.paintIcon(this,g,snakelengthx[0],snakelengthy[0]);
        }
        if(right)
        {
            rightface.paintIcon(this,g,snakelengthx[0],snakelengthy[0]);
        }
        if(down)
        {
            downface.paintIcon(this,g,snakelengthx[0],snakelengthy[0]);
        }
        if(top)
        {
            topface.paintIcon(this,g,snakelengthx[0],snakelengthy[0]);
        }
        for(int i=1;i<snakeLength;i++)
        {
            stomach.paintIcon(this,g,snakelengthx[i],snakelengthy[i]);
        }
        enemy.paintIcon(this,g,xenemy,yenemy);
        if(gameover)
        {
            g.setColor(Color.white);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            g.drawString("GAME OVER", 390,320);
            g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
            g.drawString("Press space button to restart",350,350);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,15));
        g.drawString("Score : "+score,750,30);
        g.drawString("Length : "+snakeLength,750,50);
        g.dispose();
    }
    public void restart(){
        gameover=false;
        move=0;
        score=0;
        snakeLength=3;
        left=false;
        right=true;
        top=false;
        down=false;
        time.start();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=snakeLength-1;i>0;i--){
            snakelengthx[i]=snakelengthx[i-1];   // snake moving ahead by one unit when key is pressed
            snakelengthy[i]=snakelengthy[i-1];
        }
        if(left)
        {
            snakelengthx[0] = snakelengthx[0]-25; //moving left

        }
        if(right)
        {
            snakelengthx[0]=snakelengthx[0]+25;
        }
        if(top)
        {
            snakelengthy[0]=snakelengthy[0]-25;
        }
        if(down)
        {
            snakelengthy[0]=snakelengthy[0]+25;
        }
        if(snakelengthx[0]>850)
        {
            snakelengthx[0]=25;
        }
        if(snakelengthx[0]<25)
        {
            snakelengthx[0]=850;
        }
        if(snakelengthy[0]>625)
        {
            snakelengthy[0]=75;
        }
        if(snakelengthy[0]<75)
        {
            snakelengthy[0]=625;
        }
        enemyCollision();
        bodyCollision();
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE && gameover)
        {
            restart(); 
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT &&(!right))
        {
            left=true;
            right=false;
            top=false;
            down=false;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left))
        {
            left=false;
            right=true;
            top=false;
            down=false;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP && (!down)){
            top=true;
            left=false;
            right=false;
            down=false;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && (!top))
        {
            down=true;
            left=false;
            right=false;
            top=false;
            move++;
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}
