import java.awt.*;

public class Ball {
    private int radius;
    public Pair pos;
    public Pair vel;
    public Ball() {
        radius = 25;
        pos = new Pair(250, 300);
        vel = new Pair(250, 0);
    }

    public void update(World w, double time, Player p1, Player p2) {
        pos = pos.add(vel.times(time));
        bounce(w, p1);
        bounce(w, p2);
    }

    public void bounce(World w, Player p) {
        // bounce off top and bottom walls
        // "predict" bounce beforehand
        double newY = pos.y + 0.1 * vel.y;

        if (newY < 0 || newY >= w.height){
            vel.y = -vel.y;
        }

        // bounce off of paddles
        // check y position
        if (pos.y >= p.pos.y  && pos.y <= p.pos.y + p.HEIGHT) {
            // check x position
            if (pos.x >= p.pos.x + p.WIDTH - 15 && pos.x <= p.pos.x + p.WIDTH + 15) {
                // calculate bounce angle
                double bouncePointFromMiddle = pos.y - (p.pos.y + ((double) p.HEIGHT / 2));
                double normalizedBouncePoint = bouncePointFromMiddle / ((double) p.HEIGHT / 2);

                double bounceAngle = normalizedBouncePoint * (75 * (Math.PI / 180));

                // change velocity according to angle
                double velMagnitude = Math.sqrt((vel.x * vel.x) + (vel.y * vel.y));

                double new_xVel = velMagnitude * Math.cos(bounceAngle);
                double new_yVel = velMagnitude * Math.sin(bounceAngle);

                // set velocity
                if (vel.x < 0) {
                    vel = new Pair(new_xVel, new_yVel);
                } else {
                    vel = new Pair(-new_xVel, new_yVel);
                }
            }
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawOval((int) pos.x - radius, (int) pos.y - radius, radius, radius);
        g.setColor(Color.white);
        g.fillOval((int) pos.x - radius, (int) pos.y - radius, radius, radius);
    }
}