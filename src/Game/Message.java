package Game;

/**
 * Created by tjcup on 4/19/2017.
 */
public class Message {
    public boolean isWhite;
    public Move newMove;
    public String newMessage;
    public String newGameInfo;
    public Long timeLeft;

    public Message(boolean isWhite, Move newMove, String newMessage, String newGameInfo, Long timeLeft) {
        this.isWhite = isWhite;
        this.newMove = newMove;
        this.newMessage = newMessage;
        this.newGameInfo = newGameInfo;
        this.timeLeft = timeLeft;
    }
}
