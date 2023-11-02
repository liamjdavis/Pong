public class Score {
    public int player1Score;
    public int player2Score;

    public Score() {
        player1Score = 0;
        player2Score = 0;
    }

    public void incrementPlayer1Score() {
        player1Score++;
    }

    public void incrementPlayer2Score() {
        player2Score++;
    }
}
