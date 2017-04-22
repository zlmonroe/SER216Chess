package Game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Created by jon on 4/22/2017.
 */
class BoardStateTest {
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
        bs.move(new Point(0, 7), new Point(0, 3));
        assertEquals(3, bs.getPieceAt(new Point(0,3)).getIdentifier());
    }

    @Test
    void getPieces() {

    }

    @Test
    void move() {

    }

}