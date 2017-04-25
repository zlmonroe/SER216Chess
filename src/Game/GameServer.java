package Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tjcup on 4/21/2017.
 */
public class GameServer {
    private ObjectOutputStream whiteOut;
    private ObjectInputStream whiteIn;
    private ObjectOutputStream blackOut;
    private ObjectInputStream blackIn;
    Player whitePlayer, blackPlayer;

    public static void main(String[] args) {
        if (args.length > 0) {
            new GameServer(Integer.parseInt(args[0]));
        } else
            new GameServer(5000);
    }

    public GameServer(int portNum) {
        boolean whiteConnected = false;
        boolean blackConnected = false;
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
        try {
            ServerSocket serverSocket = new ServerSocket(portNum);
            Socket whiteSocket = serverSocket.accept();
            whiteConnected = true;
            Socket blackSocket = serverSocket.accept();
            blackConnected = true;
            System.out.println("All Connected");

            whiteOut = new ObjectOutputStream(whiteSocket.getOutputStream());
            whiteIn = new ObjectInputStream(whiteSocket.getInputStream());

            blackOut = new ObjectOutputStream(blackSocket.getOutputStream());
            blackIn = new ObjectInputStream(blackSocket.getInputStream());
            whiteOut.writeObject(new Message(true, null, null, null, 0));
            blackOut.writeObject(new Message(false, null, null, null, 0));
            blackSocket.setSoTimeout(500);
            whiteSocket.setSoTimeout(500);
        } catch (Throwable e) {
        }
        boolean isWhiteTurn = true;
        State gameState = State.PROGRESS;
        while(whiteConnected && blackConnected && gameState == State.PROGRESS) {
            Message whiteMessage = null;
            Message blackMessage = null;
            boolean newInfo = false;

            try {
                whiteMessage = (Message) whiteIn.readObject();
            } catch (Exception e) {
            }

            try {
                blackMessage = (Message) blackIn.readObject();
            } catch (Exception e) {
            }

            try {
                if (whiteMessage != null) {
                    System.out.println("The move in the new message says: " + whiteMessage.newMove.oldPoint + " to " + whiteMessage.newMove.newPoint);
                    if (processMove(whiteMessage)) {
                        isWhiteTurn = !isWhiteTurn;
                        gameState = checkGameState(isWhiteTurn);
                    }
                }
                if (blackMessage != null) {
                    if (processMove(blackMessage)) {
                        isWhiteTurn = !isWhiteTurn;
                        gameState = checkGameState(isWhiteTurn);
                    }
                }
            }
            catch (IOException e) {
                System.err.println("The move could not be processed (likely due to a connection error)!");
            }
        }

    }

    boolean processMove(Message message) throws IOException {
        if (message.newMessage != null) {
            //move is just a message
            if (message.isWhite) {
                //if the message is from white
                blackOut.writeObject(message);
                blackOut.reset();
            } else {
                whiteOut.writeObject(message);
                whiteOut.reset();
            }
        } else {
            //move contains info to be processed
            Move move = message.newMove;
                if (message.isWhite) {
                    if (whitePlayer.move(move.oldPoint, move.newPoint)) {
                        if (blackPlayer.inCheck()) {
                            //the move has happened and the black player is now in check
                            message = message.setNewGameInfo("Black is now in check!");
                        }
                        informMoveUpdate(message);
                        return true;
                    }
                    else{
                        whiteOut.writeObject(new Message(true,null,null,"Invalid Move",0));
                        whiteOut.reset();
                    }
                } else {
                    if (blackPlayer.move(move.oldPoint, move.newPoint)) {
                        if (whitePlayer.inCheck()) {
                            //the move has happened and the white player is now in check
                            message = message.setNewGameInfo("White is now in check!");
                        }
                        informMoveUpdate(message);
                        return true;
                    }
                    else{
                        blackOut.writeObject(new Message(false,null,null,"Invalid Move",0));
                        blackOut.reset();
                    }
                }
            }

        return false;
    }

    private enum State {
        STALEMATE   (0b000),
        PROGRESS    (0b001),
        WHITELOSES  (0b110),
        BLACKLOSES  (0b010),
        ERROR       (0b011);

        public int val;

        State(int val) {
            this.val = val;
        }
    }

    private State checkGameState(boolean turn) {
        int player = turn ? 1:0;
        int checkMate, hasMoves;
        if(turn) {
            checkMate = whitePlayer.inCheckMate() ? 1 : 0;
            hasMoves = whitePlayer.hasMoves() ? 1:0;
        }
        else {
            checkMate = blackPlayer.inCheckMate() ? 1 : 0;
            hasMoves = blackPlayer.hasMoves() ? 1:0;
        }

        int stateValue = (player << 2) | (checkMate << 1) | hasMoves;
        if(stateValue > 0b011 && stateValue != 0b110) stateValue &= 0b011;
        return State.values()[stateValue];
    }

    private void informMoveUpdate(Message message) {
        try {
            blackOut.writeObject(message);
            blackOut.reset();
            whiteOut.writeObject(message);
            whiteOut.reset();
        }
        catch (IOException e) {
            System.err.println("Could not inform players (likely due to a connection error)");
        }

    }
}