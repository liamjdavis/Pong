import javax.swing.*;
import java.awt.*;

public class World {
    // frame
    public int width;
    public int height;

    // players
    public Player player1;
    public Player player2;
    public Ball ball;

    // AI
    public AIController player1AI;
    public AIController player2AI;

    // score
    public Score score;
    public int totalScore = 0;
    public World(int initWidth, int initHeight) {
        String AIChoice = JOptionPane.showInputDialog("Do you want player 1, player 2, or neither to be controlled by AI? " +
                "Type '1', '2' or 'N'");

        width = initWidth;
        height = initHeight;

        score = new Score();

        player1 = new Player(0);
        player2 = new Player(775);
        ball = new Ball();

        // set AI
        if (AIChoice.equals("1")) {
            player1AI = new AIController(player1, ball);
            player2AI = null;
        }
        else if (AIChoice.equals("2")) {
            player2AI = new AIController(player2, ball);
            player1AI = null;
        }
        else {
            player1AI = null;
            player2AI = null;
        }

    }

    public void draw(Graphics g) {
        // set background to blue
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, width, height);

        // draw notes on top
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Paddle shrinks everytime you score", 250, 40);
        g.drawString("Ball speeds up every time a point is scored", 225, 80);

        // draw players
        player1.draw(g);
        player2.draw(g);

        // draw ball
        ball.draw(g);

        // draw score
        String p1Score = String.valueOf(score.player1Score);
        String p2Score = String.valueOf(score.player2Score);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 104));
        g.drawString(p1Score + "                 " + p2Score, 100, 100);

    }

    public void update(double time) {
        ball.update(this, time, player1, player2);
        player1.update(time);
        player2.update(time);

        if (player1AI != null) player1AI.update();
        if (player2AI != null) player2AI.update();

        checkForScore();
    }

    // check for scoring collision
    public void checkForScore() {
        if (ball.pos.x < 0) {
            score.incrementPlayer2Score();
            totalScore++;

            ball.pos = new Pair(400, 300);
            ball.vel = new Pair(250 + (40 * totalScore), 0);

            // decrease height of player 2
            if (player2.HEIGHT > 80) player2.setHEIGHT(player2.HEIGHT - 40);

            // reset player position and velocity
            player1.resetPos();
            player2.resetPos();

            player1.resetVel();
            player2.resetVel();
        }

        if (ball.pos.x > 800) {
            score.incrementPlayer1Score();
            totalScore++;

            ball.pos = new Pair(400, 300);
            ball.vel = new Pair(-250 - (40 * totalScore), 0);

            // decrease height of player 1
            if (player1.HEIGHT > 80) player1.setHEIGHT(player1.HEIGHT - 40);

            // reset player position and velocity
            player1.resetPos();
            player2.resetPos();

            player1.resetVel();
            player2.resetVel();
        }
    }
}
