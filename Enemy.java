
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stu48426
 */
public class Enemy extends Rectangle {

    Dude target;
    Color color;
    String text;

    public Enemy(int x1, int y1, Dude t) {
        super(x1, y1, 10, 10);
        color = Color.red;
        text = "Basic. Walks right to where the target is.";
        target = t;

    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawString(text, x, y - 5);
    }

    public void move() {
        if (new Random().nextInt(100) < 80) {
            return;
        }
        if (target.getCenterX() > x) {
            x++;
        }
        if (target.getCenterX() < x) {
            x--;
        }

        if (target.getCenterY() > y) {
            y++;
        }
        if (target.getCenterY() < y) {
            y--;
        }
    }
}

/*
public class Predictor extends Enemy {

    Point targetPast, p;
    final int PREDICT_DIST = 15;

    public Predictor(int x1, int y1, Dude t) {
        super(x1, y1, t);
        color = Color.green;
        text = "predictor.tries to predict where the player is moving";
        target = t;
        targetPast = new Point((int) target.getCenterX(), (int) target.getCenterY());
        p = new Point((int) target.getCenterX(), (int) target.getCenterY()); 
        

    }



    public void move(int num) {
        if (new Random().nextInt(100) < 80) {
            return;
        }
        Point currentPoint = new Point((int) target.getCenterX(), (int) target.getCenterY());
        p = new Point((int) (currentPoint.x + xDiff * PREDICT_DIST), (int) (currentPoint.y + yDiff * PREDICT_DIST));
        if (p.getX() > x) {
            x++;
        }
        if (p.getX() < x) {
            x--;
        }
        if (p.getY() > y) {
            y++;
        }
        if (p.getY() < y) {
            y--;
        }
        if (contains(p)) {
            p = new Point((int) target.getCenterX(), (int) target.getCenterY());
        }
        targetPast = currentPoint;
    }

    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(color);
        g.drawLine(p.x - 5, p.y - 5, p.x + 5, p.y + 5);
        g.drawLine(p.x - 5, p.y + 5, p.x + 5, p.y - 5);
        g.drawLine(p.x - 5, p.y - 5, 10, 10);
    }
}
    */