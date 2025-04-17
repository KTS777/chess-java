import model.Piece;
import model.Square;
import model.pieces.Queen;
import model.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(null);
        board.setupEmptyBoard(); // Make sure this sets an 8x8 board of empty squares
    }

    @Test
    public void testQueenCenterMoves() {
        Queen queen = new Queen(1, board.getSquare(4, 4), "wq.png"); // Place on e5
        board.getSquare(4, 4).setOccupyingPiece(queen);

        List<Square> legalMoves = queen.getLegalMoves(board);

        // Diagonal
        assertTrue(legalMoves.contains(board.getSquare(3, 3)));
        assertTrue(legalMoves.contains(board.getSquare(5, 5)));
        // Horizontal
        assertTrue(legalMoves.contains(board.getSquare(0, 4)));
        assertTrue(legalMoves.contains(board.getSquare(7, 4)));
        // Vertical
        assertTrue(legalMoves.contains(board.getSquare(4, 1)));
        assertTrue(legalMoves.contains(board.getSquare(4, 3)));
    }

    @Test
    public void testQueenBlockedByAlly() {
        Queen queen = new Queen(1, board.getSquare(4, 4), "wq.png");
        board.getSquare(4, 4).setOccupyingPiece(queen);

        Piece ally = new Rook(1, board.getSquare(4, 5), "wr.png");
        board.getSquare(4, 5).setOccupyingPiece(ally);

        List<Square> moves = queen.getLegalMoves(board);

        assertFalse(moves.contains(board.getSquare(4, 5))); // blocked by ally
        assertFalse(moves.contains(board.getSquare(4, 6))); // can't move past ally
    }

    @Test
    public void testQueenCapturesEnemy() {
        Queen queen = new Queen(1, board.getSquare(4, 4), "wq.png");
        board.getSquare(4, 4).setOccupyingPiece(queen);

        Piece enemy = new Rook(0, board.getSquare(4, 5), "br.png");
        board.getSquare(4, 5).setOccupyingPiece(enemy);

        List<Square> moves = queen.getLegalMoves(board);

        assertTrue(moves.contains(board.getSquare(4, 5))); // can capture
        assertFalse(moves.contains(board.getSquare(4, 6))); // can't go past enemy
    }

    @Test
    public void testQueenAtEdge() {
        Queen queen = new Queen(1, board.getSquare(0, 0), "wq.png");
        board.getSquare(0, 0).setOccupyingPiece(queen);

        List<Square> moves = queen.getLegalMoves(board);

        // should only move right, down, and diagonally bottom-right
        assertTrue(moves.contains(board.getSquare(0, 1))); // down
        assertTrue(moves.contains(board.getSquare(1, 0))); // right
        assertTrue(moves.contains(board.getSquare(1, 1))); // diagonal

    }

    @Test
    public void testQueenPinnedByRook() {
        // King at e1 (4, 7), Queen at e2 (4,6), Enemy Rook at e8 (4, 0)
        Queen queen = new Queen(1, board.getSquare(4, 6), "wq.png");
        Piece whiteKing = new Rook(1, board.getSquare(4, 7), "wk.png"); // use Rook to represent King for testing
        Piece enemyRook = new Rook(0, board.getSquare(4, 0), "br.png");

        board.getSquare(4, 6).setOccupyingPiece(queen);
        board.getSquare(4, 7).setOccupyingPiece(whiteKing);
        board.getSquare(4, 0).setOccupyingPiece(enemyRook);

        List<Square> moves = queen.getLegalMoves(board);

        // Queen should only be allowed to move along the same file (vertical), nothing diagonal/horizontal
        assertTrue(moves.contains(board.getSquare(4, 5))); // forward
        assertTrue(moves.contains(board.getSquare(4, 4))); // forward
        assertFalse(moves.contains(board.getSquare(3, 6))); // diagonal not allowed
        assertFalse(moves.contains(board.getSquare(5, 6))); // diagonal not allowed
    }

    @Test
    public void testQueenHasNoLegalMoves() {
        // Queen surrounded on all sides
        Queen queen = new Queen(1, board.getSquare(4, 4), "wq.png");
        board.getSquare(4, 4).setOccupyingPiece(queen);

        // All 8 surrounding squares blocked by own pieces
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                int x = 4 + dx;
                int y = 4 + dy;
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    board.getSquare(x, y).setOccupyingPiece(new Rook(1, board.getSquare(x, y), "wr.png"));
                }
            }
        }

        List<Square> moves = queen.getLegalMoves(board);
        assertEquals(0, moves.size());
    }


}
