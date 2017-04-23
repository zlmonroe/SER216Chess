package Game;

import Game.Pieces.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Created by jon on 4/22/2017.
 */
class TestBoardState {
    BoardState bs;

    @BeforeEach
    void setUp() {
        bs = new BoardState();
    }

    @AfterEach
    void tearDown() {
        bs = null;
    }

    @Test
    void getPieceAt() {
        assertEquals(0,bs.getPieceAt(new Point(0,1)).getIdentifier());
        BoardState newBS = bs.move(new Point(0,1),new Point(0,3));
        assertNotEquals(null, bs.getPieceAt(new Point(0,1)));
        assertEquals(null, newBS.getPieceAt(new Point(0,1)));
        BoardState nnewBS = newBS.move(new Point(0, 7), new Point(0, 3));
        assertEquals(3, nnewBS.getPieceAt(new Point(0,3)).getIdentifier());
    }

    @Test
    void getPieces() {
        HashSet<Piece> prev = new HashSet<>(bs.getPieces(true));
        BoardState newBs = bs.move(new Point(0,0), new Point(0, 1));
        assertNotEquals(new HashSet<Piece>(bs.getPieces(true)), new HashSet<Piece>(newBs.getPieces(true)));
        assertEquals(prev, new HashSet<Piece>(bs.getPieces(true)));
    }

    @Test
    void move() {
        BoardState sameSpot = bs.move(new Point(1,1), new Point(1,1));
        assertEquals(bs, sameSpot);
        assertEquals(0,bs.getPieceAt(new Point(0,1)).getIdentifier());
        BoardState newBS = bs.move(new Point(0,1),new Point(0,3));
        assertNotEquals(null, bs.getPieceAt(new Point(0,1)));
        assertEquals(null, newBS.getPieceAt(new Point(0,1)));
        BoardState nnewBS = newBS.move(new Point(0, 7), new Point(0, 3));
        assertEquals(3, nnewBS.getPieceAt(new Point(0,3)).getIdentifier());
    }

}