package model.pieces;

import model.Piece;
import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(null); // No GUI needed for tests
        board.setupEmptyBoard(); // Your helper to create 8x8 board
    }

    @Test
    public void testKnightBasicMoves() {
        Knight knight = new Knight(1, board.getSquare(4, 4), "wn.png");
        board.getSquare(4, 4).setOccupyingPiece(knight);

        List<Square> legalMoves = knight.getLegalMoves(board);

        int[][] expectedOffsets = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] offset : expectedOffsets) {
            int x = 4 + offset[0];
            int y = 4 + offset[1];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                Square expected = board.getSquare(x, y);
                assertTrue(legalMoves.contains(expected),
                        "Missing move to (" + x + "," + y + ")");
            }
        }

        assertEquals(8, legalMoves.size(), "Knight should have 8 moves from center");
    }

    @Test
    public void testKnightCanCaptureEnemy() {
        Knight knight = new Knight(1, board.getSquare(3, 3), "wn.png");
        board.getSquare(3, 3).setOccupyingPiece(knight);

        Piece enemy = new Rook(0, board.getSquare(5, 4), "br.png");
        board.getSquare(5, 4).setOccupyingPiece(enemy);

        List<Square> legalMoves = knight.getLegalMoves(board);

        assertTrue(legalMoves.contains(board.getSquare(5, 4)));
    }

    @Test
    public void testKnightBlockedByAlly() {
        Knight knight = new Knight(1, board.getSquare(3, 3), "wn.png");
        board.getSquare(3, 3).setOccupyingPiece(knight);

        Piece ally = new Rook(1, board.getSquare(5, 4), "wr.png");
        board.getSquare(5, 4).setOccupyingPiece(ally);

        List<Square> legalMoves = knight.getLegalMoves(board);

        assertFalse(legalMoves.contains(board.getSquare(5, 4)));
    }

    @Test
    public void testKnightFromCorner() {
        Knight knight = new Knight(1, board.getSquare(0, 0), "wn.png");
        board.getSquare(0, 0).setOccupyingPiece(knight);

        List<Square> legalMoves = knight.getLegalMoves(board);

        assertTrue(legalMoves.contains(board.getSquare(1, 2))); // b3
        assertTrue(legalMoves.contains(board.getSquare(2, 1))); // c2
        assertEquals(2, legalMoves.size(), "Knight from a1 should have only 2 valid moves");
    }
}
