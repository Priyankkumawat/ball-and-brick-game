/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Galactus__3000
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener{
    private int playerx=300;
    private int ballx=300;
    private int bally=400;
    private int ballxdir=-2;
    private int ballydir=-4;
    private Timer Timer;
    private int delay=8;
    private int score=0;
    private boolean play=false;
    private int totalbrick=21;
    private GenerateBrick map;
    
    public GamePlay(){
        addKeyListener(this);
        map=new GenerateBrick(3,7);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);   
        Timer=new Timer(delay,this);
        Timer.start();
    }
    
    @Override
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(3, 3, 694, 597);
        
        //border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 600);
        g.fillRect(3,0,694,3);
        g.fillRect(697,0,3,600);
        
        //text
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString("Score: "+score, 570, 30);
        
        //map
        map.draw((Graphics2D)g);
        
        //player
        g.setColor(Color.yellow);
        g.fillRect(playerx,550,90,10);
        
        //ball
        g.setColor(Color.gray);
        g.fillOval(ballx, bally, 20, 20);
        
        if(bally > 600){
            play=false;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString(score+"", 340, 250);
            g.drawString("  Game Over  ", 270,300 );
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press \"Enter\" to Restart", 230, 350);
            
        }
        if(totalbrick <= 0){
            play=false;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Congratulation!! "+score, 300, 250);
            g.drawString("  Game Over  ", 270,300 );
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press \"Enter\" to Restart", 230, 350);

        }
    }
    
    @Override
    public void keyPressed(KeyEvent e){
         if(e.getKeyCode() == KeyEvent.VK_RIGHT){
              if(playerx >= 591){
                  playerx=600;
              }
              else moveright();
         }
         if(e.getKeyCode() == KeyEvent.VK_LEFT){
             if(playerx <= 5){
                 playerx=3;
             }
             else moveleft();
         }
         if(e.getKeyCode()==KeyEvent.VK_ENTER){
             if(!play){
                 score=0;
                ballx=300;
                bally=400;
                ballxdir=-2;
                ballydir=-4;
                totalbrick=21;
                playerx=300;
                map=new GenerateBrick(3,7);
             }
         }
         repaint();
    }
    private void moveright(){
        play=true;
        playerx+=20;
    }
    private void moveleft(){
        play=true;
        playerx-=20;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        Timer.start();
        if(play==true){
            Rectangle b=new Rectangle(ballx,bally,20,20);
            Rectangle p=new Rectangle(playerx,550,100,10);
            if(b.intersects(p)) ballydir=-ballydir;
            
            A:
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j] > 0){
                        Rectangle t=new Rectangle(j*map.brickwidth+80,i*map.brickheight+50,map.brickwidth,map.brickheight);
                        if(b.intersects(t)){
                            map.setbrick(0, i, j);
                            totalbrick--;
                            score+=5;
                            if(ballx+19 <= t.x || ballx+1>= t.x+ t.width) ballxdir=-ballxdir;
                            else ballydir=-ballydir;
                            break A;
                        }
                    }
                }
            }
            
//            if(score > 50) ballxdir=
            
            ballx+=ballxdir;
            bally+=ballydir;
            if(ballx < 3) ballxdir=-ballxdir;
            if(ballx > 670) ballxdir=-ballxdir;
            if(bally < 3) ballydir=-ballydir;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

}
