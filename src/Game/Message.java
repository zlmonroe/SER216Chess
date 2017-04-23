package Game;

import java.io.Serializable;

/**
 * Created by tjcup on 4/19/2017.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -6694558632548688228L;
    public final boolean isWhite;
    public final Move newMove;
    public final String newMessage;
    public final String newGameInfo;
    public final long timeLeft;
    public int[][] boardLayout;

    /**
     * Creates a new message to be passed between the server and client
     * @param isWhite True if it is the white player's turn
     * @param newMove Representation of an attempted move
     * @param newMessage New message to send out
     * @param newGameInfo New information about the game
     * @param timeLeft Amount of time remaining for the current player, in ms
     * @param boardLayout 8x8 int array representing layout of chessboard 0,0 corresponds to A8, 0,7 to H8
     */
    public Message(boolean isWhite, Move newMove, String newMessage, String newGameInfo, long timeLeft, int[][] boardLayout) {
        this.isWhite = isWhite;
        this.newMove = newMove;
        this.newMessage = newMessage;
        this.newGameInfo = newGameInfo;
        this.timeLeft = timeLeft;
        this.boardLayout = boardLayout;
    }
}
