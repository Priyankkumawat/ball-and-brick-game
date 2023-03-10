/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Galactus__3000
 */
public class GenerateBrick {
    public int[][] map;
    public int brickwidth;
    public int brickheight;
    public int r,c;
    
    public GenerateBrick(int x,int y){
        map=new int[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                map[i][j]=1;
            }
        }
        r=x;
        c=y;
        brickwidth=540/y;
        brickheight=150/x;
    }
    public void setbrick(int val,int r, int c){
        map[r][c]=val;
    }
    public void draw(Graphics2D g){
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(map[i][j]>0){
                    g.setColor(Color.white);
                    g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
                    
                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
                }
            }
        }
    }
}
