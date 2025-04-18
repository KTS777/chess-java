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
    private Knight knight;

    @BeforeEach
    public void setUp() {
        board = new Board(null);
        board.setupEmptyBoard();
        knight = new Knight(1, board.getSquare(4, 4), "wn.png");
        board.getSquare(4, 4).setOccupyingPiece(knight);
    }

    @Test
    public void testKnightMovesFromCenter() {
        List<Square> legalMoves = knight.getLegalMoves(board);
        int[][] expectedMoves = {
                {2, 3}, {2, 5}, {3, 2}, {3, 6},
                {5, 2}, {5, 6}, {6, 3}, {6, 5}
        };
        for (int[] move : expectedMoves) {
            assertTrue(legalMoves.contains(board.getSquare(move[0], move[1])),
                    "Expected move: (" + move[0] + ", " + move[1] + ")");
        }
        assertEquals(8, legalMoves.size());
    }

    @Test
    public void testKnightCanCaptureEnemy() {
        Piece enemy = new Rook(0, board.getSquare(2, 3), "br.png");
        board.getSquare(2, 3).setOccupyingPiece(enemy);

        List<Square> legalMoves = knight.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(2, 3)));
    }

    @Test
    public void testKnightCannotCaptureFriendly() {
        Piece friendly = new Rook(1, board.getSquare(2, 3), "wr.png");
        board.getSquare(2, 3).setOccupyingPiece(friendly);

        List<Square> legalMoves = knight.getLegalMoves(board);
        assertFalse(legalMoves.contains(board.getSquare(2, 3)));
    }

    @Test
    public void testKnightMovesFromCorner() {
        knight.setPosition(board.getSquare(0, 0));
        board.getSquare(4, 4).removePiece();
        board.getSquare(0, 0).setOccupyingPiece(knight);

        List<Square> legalMoves = knight.getLegalMoves(board);
        assertEquals(2, legalMoves.size());
        assertTrue(legalMoves.contains(board.getSquare(1, 2)));
        assertTrue(legalMoves.contains(board.getSquare(2, 1)));
    }
}