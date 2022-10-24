import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.lang.*;
import java.util.ArrayList;

public class Bulletrunner implements ActionListener, KeyListener {

    javax.swing.Timer timer;
    JFrame frame;
    JPanel display;
    ArrayList<Bullet> bullets;
    Dude guy;
    Dude me;
    final int BULLET_MAX_TIME = 10;
    Enemy tom;
    final int MAX_BULLETS = 75;
    int bulletTime, bulletTime2;

    public static void main(String[] args) throws Exception {
        new Bulletrunner();
    }

    public Bulletrunner() {
        frame = new JFrame("Insert Title Here");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        display = new DisplayPanel();
        frame.add(display);
        //put constructor code here
        me = new Dude(200, 300);
        me.setPlayer1(true);
        guy = new Dude(400, 300);
        guy.setPlayer1(false);
        tom = new Enemy(0, 0, guy);
        bullets = new ArrayList<Bullet>();
        bulletTime = 0;
        bulletTime2 = 0;
        //end your constructor code
        timer = new javax.swing.Timer(10, this);
        timer.start();
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //type what needs to be performed every time the timer ticks
        me.move();
        guy.move();
        if (bulletTime == BULLET_MAX_TIME) {
            Bullet f = new Bullet(guy.xSpeed, guy.ySpeed, (int) guy.getCenterX(), (int) guy.getCenterY());
            for (int i = 0; i < me.width; i++) {
                f.move();
            }
            bullets.add(f);
        }
        if (bullets.size() > MAX_BULLETS) {
            bullets.remove(0);
        }

        bulletTime--;
        bulletTime = Math.max(0, bulletTime);
        for (Bullet bullet : bullets) {
            for (int i = 0; i < 4; i++) {
                bullet.move();
            }

        }
        if (bulletTime2 == BULLET_MAX_TIME) {
            Bullet b = new Bullet(me.xSpeed, me.ySpeed, (int) me.getCenterX(), (int) me.getCenterY());
            for (int i = 0; i < me.width; i++) {
                b.move();
            }
            bullets.add(b);
        }
        if (bullets.size() > MAX_BULLETS) {
            bullets.remove(0);
        }
        bulletTime2--;
        bulletTime2 = Math.max(0, bulletTime2);
        for (Bullet bullet : bullets) {
            for (int i = 0; i < 4; i++) {
                bullet.move();
            }

        }

        tom.move();
        //end your code for timer tick code
        display.repaint();
        for (Bullet bullet : bullets) {
            if (guy.intersects(bullet)) {
                guy.getHit();
            }
        }
        for (Bullet bullet : bullets) {
            if (me.intersects(bullet)) {
                me.getHit();
            }
        }
        if (me.dead()) {
            JOptionPane.showMessageDialog(null, "Green won");
            System.exit(0);
        }
        if (guy.dead()) {
            JOptionPane.showMessageDialog(null, "Red won");
            System.exit(0);
        }

        display.repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            guy.setUp(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            guy.setDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_CONTROL && bulletTime < 1) {
            bulletTime = BULLET_MAX_TIME;

        }
        if (e.getKeyCode() == KeyEvent.VK_Q && bulletTime2 < 1) {
            bulletTime2 = BULLET_MAX_TIME;

        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            me.setUp(true);
            System.out.println("up on");
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            me.setDown(true);
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            guy.setUp(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            guy.setDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            guy.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            guy.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);

        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            me.setUp(false);
            System.out.println("up off");
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            me.setDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            me.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            me.moveLeft();
        }
    }

    class DisplayPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //draw your graphics here
            Image img = new ImageIcon("yes.png").getImage();
             g.drawImage(img, 0, 4, 1920, 500, null);
             guy.draw(g);
            me.draw(g);
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
            g.setColor(Color.pink);
            g.drawLine(245, 245, 255, 255);
            g.drawLine(245, 255, 255, 245);
            g.drawOval(245, 245, 10, 10);
            
           
        }
    }
}
