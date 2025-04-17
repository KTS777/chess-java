import model.Piece;
import model.Square;
import model.pieces.Bishop;
import model.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(null);
        board.setupEmptyBoard(); // assumes you've added this helper
    }

    @Test
    public void testBishopMovesDiagonally() {
        Bishop bishop = new Bishop(1, board.getSquare(3, 3), "wb.png"); // d4
        board.getSquare(3, 3).setOccupyingPiece(bishop);

        List<Square> legalMoves = bishop.getLegalMoves(board);

        assertTrue(legalMoves.contains(board.getSquare(4, 4))); // e5
        assertTrue(legalMoves.contains(board.getSquare(2, 2))); // c3
        assertTrue(legalMoves.contains(board.getSquare(4, 2))); // e3
        assertTrue(legalMoves.contains(board.getSquare(2, 4))); // c5
    }

    @Test
    public void testBishopBlockedByAlly() {
        Bishop bishop = new Bishop(1, board.getSquare(3, 3), "wb.png"); // d4
        board.getSquare(3, 3).setOccupyingPiece(bishop);

        // Place ally pawns diagonally
        Piece ally1 = new Pawn(1, board.getSquare(4, 4), "wp.png"); // e5
        Piece ally2 = new Pawn(1, board.getSquare(2, 4), "wp.png"); // c5
        board.getSquare(4, 4).setOccupyingPiece(ally1);
        board.getSquare(2, 4).setOccupyingPiece(ally2);

        List<Square> legalMoves = bishop.getLegalMoves(board);

        // Should not include squares occupied by allies
        assertFalse(legalMoves.contains(board.getSquare(4, 4)));
        assertFalse(legalMoves.contains(board.getSquare(2, 4)));

        // Should still allow other directions
        assertTrue(legalMoves.contains(board.getSquare(2, 2))); // c3
        assertTrue(legalMoves.contains(board.getSquare(4, 2))); // e3
    }

    @Test
    public void testBishopCanCaptureEnemy() {
        Bishop bishop = new Bishop(1, board.getSquare(3, 3), "wb.png"); // d4
        board.getSquare(3, 3).setOccupyingPiece(bishop);

        Piece enemy = new Pawn(0, board.getSquare(5, 5), "bp.png"); // f6
        board.getSquare(5, 5).setOccupyingPiece(enemy);

        List<Square> legalMoves = bishop.getLegalMoves(board);

        // Should include the square with the enemy
        assertTrue(legalMoves.contains(board.getSquare(5, 5)));

        // But not squares beyond it
        assertFalse(legalMoves.contains(board.getSquare(6, 6)));
    }

    @Test
    public void testBishopCannotMoveStraight() {
        Bishop bishop = new Bishop(1, board.getSquare(3, 3), "wb.png"); // d4
        board.getSquare(3, 3).setOccupyingPiece(bishop);

        List<Square> legalMoves = bishop.getLegalMoves(board);

        assertFalse(legalMoves.contains(board.getSquare(3, 4))); // d5 (vertical)
        assertFalse(legalMoves.contains(board.getSquare(4, 3))); // e4 (horizontal)
    }

    @Test
    public void testBishopFromCorner() {
        Bishop bishop = new Bishop(1, board.getSquare(0, 0), "wb.png"); // a1
        board.getSquare(0, 0).setOccupyingPiece(bishop);

        List<Square> legalMoves = bishop.getLegalMoves(board);

        // Should move along top-right diagonal
        assertTrue(legalMoves.contains(board.getSquare(1, 1))); // b2
        assertTrue(legalMoves.contains(board.getSquare(2, 2))); // c3
        assertTrue(legalMoves.contains(board.getSquare(3, 3))); // d4
    }


}
