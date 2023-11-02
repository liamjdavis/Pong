public class AIController {
    public Player player;
    public Ball ball;
    public AIController(Player player, Ball ball) {
        this.player = player;
        this.ball = ball;
    }

    public void update() {
        // stop if it is on target
        // give delay to nerf AI
        if (ball.pos.y <= player.pos.y + player.HEIGHT * 0.75 && ball.pos.y >= player.pos.y + player.HEIGHT * 0.25) {
            player.stopMoving();
        }

        // move up to hit ball
        if (ball.pos.y < player.pos.y) {
            player.moveUp();
        }

        // move down to hit ball
        if (ball.pos.y > player.pos.y + player.HEIGHT) {
            player.moveDown();
        }
    }
}
