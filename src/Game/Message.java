package Game;

import java.io.Serializable;

/**
 * Created by tjcup on 4/19/2017.
 */
public class Message implements Serializable {
    public final boolean isWhite;
    public final Move newMove;
    public final String newMessage;
    public final String newGameInfo;
    public final long timeLeft;

    /**
     * Creates a new message to be passed between the server and client
     * @param isWhite True if it is the white player's turn
     * @param newMove Representation of an attempted move
     * @param newMessage New message to send out
     * @param newGameInfo New information about the game
     * @param timeLeft Amount of time remaining for the current player, in ms
     */
    public Message(boolean isWhite, Move newMove, String newMessage, String newGameInfo, long timeLeft) {
        this.isWhite = isWhite;
        this.newMove = newMove;
        this.newMessage = newMessage;
        this.newGameInfo = newGameInfo;
        this.timeLeft = timeLeft;
    }
}
