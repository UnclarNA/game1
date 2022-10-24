import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stu48426
 */
public class Bullet extends Rectangle {

    double xSpeed, ySpeed;
    double xSp, ySp;

    public Bullet(double xS, double yS, int x1, int y1) {
        super(x1, y1, 5, 5);
        double total = Math.abs(xS) + Math.abs(yS);
        xSpeed = xS / total;
        ySpeed = yS / total;
        xSp = x = x1;
        ySp = y = y1;
    }

    public void move() {
        xSp += xSpeed;
        ySp += ySpeed;
        x = (int) Math.round(xSp);
        y = (int) Math.round(ySp);
    }

   public void draw(Graphics g){
   g.setColor(Color.black);
   g.fillRect(x, y, width, height);
   Image img=new ImageIcon("a.png").getImage();
 
   }
}
