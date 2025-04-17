import model.Piece;
import model.Square;
import model.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;
import model.pieces.King;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(null); // no GUI needed for logic
        board.setupEmptyBoard();
    }

    @Test
    public void testKingMovesAllDirections() {
        King king = new King(1, board.getSquare(4, 4), "wk.png");
        board.getSquare(4, 4).setOccupyingPiece(king);

        List<Square> legalMoves = king.getLegalMoves(board);

        int[][] deltas = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1,  0},          {1,  0},
                {-1,  1}, {0,  1}, {1,  1}
        };

        for (int[] d : deltas) {
            int x = 4 + d[0];
            int y = 4 + d[1];
            assertTrue(legalMoves.contains(board.getSquare(x, y)),
                    "Expected move to (" + x + "," + y + ")");
        }
    }

    @Test
    public void testKingCornerMoves() {
        King king = new King(1, board.getSquare(0, 0), "wk.png"); // a1
        board.getSquare(0, 0).setOccupyingPiece(king);

        List<Square> legalMoves = king.getLegalMoves(board);

        assertEquals(3, legalMoves.size(), "King in corner should have exactly 3 legal moves");
        assertTrue(legalMoves.contains(board.getSquare(1, 0))); // b1
        assertTrue(legalMoves.contains(board.getSquare(0, 1))); // a2
        assertTrue(legalMoves.contains(board.getSquare(1, 1))); // b2
    }

    @Test
    public void testKingBlockedByFriendlyPieces() {
        King king = new King(1, board.getSquare(4, 4), "wk.png");
        board.getSquare(4, 4).setOccupyingPiece(king);

        int[][] deltas = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},          {0, 1},
                {1, -1},  {1, 0}, {1, 1}
        };

        for (int[] d : deltas) {
            int x = 4 + d[0], y = 4 + d[1];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                board.getSquare(x, y).setOccupyingPiece(new Pawn(1, board.getSquare(x, y), "wp.png")); // friendly
            }
        }

        List<Square> legalMoves = king.getLegalMoves(board);

        // Should have no legal moves
        assertEquals(0, legalMoves.size(), "King should not be able to move to any square occupied by friendly pieces");
    }


    @Test
    public void testKingCanCaptureEnemyPieces() {
        King king = new King(1, board.getSquare(4, 4), "wk.png"); // e4
        board.getSquare(4, 4).setOccupyingPiece(king);

        // Place enemy pawns around the king
        int[][] deltas = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},          {0, 1},
                {1, -1},  {1, 0}, {1, 1}
        };

        for (int[] d : deltas) {
            int x = 4 + d[0], y = 4 + d[1];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                board.getSquare(x, y).setOccupyingPiece(new Pawn(0, board.getSquare(x, y), "bp.png")); // enemy
            }
        }

        List<Square> legalMoves = king.getLegalMoves(board);

        // Should have 8 legal capture moves
        assertEquals(8, legalMoves.size(), "King should be able to capture all 8 enemy pieces");

        for (int[] d : deltas) {
            int x = 4 + d[0], y = 4 + d[1];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                assertTrue(legalMoves.contains(board.getSquare(x, y)),
                        "King should be able to move to (" + x + "," + y + ")");
            }
        }
    }

    @Test
    public void testKingEdgeOfBoard() {
        King king = new King(1, board.getSquare(0, 0), "wk.png"); // a1
        board.getSquare(0, 0).setOccupyingPiece(king);

        List<Square> legalMoves = king.getLegalMoves(board);

        assertEquals(3, legalMoves.size(), "King in corner should have 3 legal moves");

        assertTrue(legalMoves.contains(board.getSquare(1, 0))); // b1
        assertTrue(legalMoves.contains(board.getSquare(0, 1))); // a2
        assertTrue(legalMoves.contains(board.getSquare(1, 1))); // b2
    }


}
