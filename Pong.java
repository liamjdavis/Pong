import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends JPanel implements KeyListener {
    public static final int FPS = 60;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    World world;

    public Pong() {
        world = new World(WIDTH, HEIGHT);;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }

    class Runner implements Runnable {

        @Override
        public void run() {
            while (true) {
                world.update(1.0 / (double) FPS);
                repaint();

                try {
                    Thread.sleep(1000/FPS);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    // keyListener

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyChar();

        // paddle movement player 1
        if (c == 'r') {
            world.player1.moveUp();
        }

        if (c == 'v') {
            world.player1.moveDown();
        }

        if (c == 'f') {
            world.player1.stopMoving();
        }

        // paddle movement player 2
        if (c == 'u') {
            world.player2.moveUp();
        }

        if (c == 'n') {
            world.player2.moveDown();
        }

        if (c == 'j') {
            world.player2.stopMoving();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    // main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Pong());
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        world.draw(g);
    }

}

/* Resources Used
https://stackoverflow.com/questions/17034235/make-an-in-game-window
https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
 */