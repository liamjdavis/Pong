import java.awt.*;

public class Player {
    public Pair pos;
    public Pair vel;
    public int HEIGHT;
    public int WIDTH;
    private Color color;
    public Player(int initX) {
        HEIGHT = 160;
        WIDTH = 20;
        pos = new Pair(initX, 300 - HEIGHT / 2);
        vel = new Pair(0, 0);
        this.color = Color.RED;
    }

    public void update(double time) {
        // stop from going over edge
        if (pos.y < 0) {
            pos.y = 0;
        }

        if (pos.y > 600 - HEIGHT) {
            pos.y = 600 - HEIGHT;
        }

        // update position
        pos.x += vel.x * time;
        pos.y += vel.y * time;
    }

    public void setVel(Pair vel) {
        this.vel = vel;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void resetPos() {
        pos = new Pair(pos.x, 300 - HEIGHT / 2);
    }

    public void resetVel() {
        vel = new Pair(0, 0);
    }

    public void moveUp() {
        setVel(new Pair(0, -250));
    }

    public void moveDown() {
        setVel(new Pair(0, 250));
    }

    public void stopMoving() {
        setVel(new Pair(0, 0));
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) pos.x, (int) pos.y, WIDTH, HEIGHT);
    }
}
