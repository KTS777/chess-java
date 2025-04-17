import model.pieces.Rook;
import model.Piece;
import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(null);
        board.setupEmptyBoard();
    }

    @Test
    public void testRookMovesOnEmptyBoard() {
        Rook rook = new Rook(1, board.getSquare(4, 4), "wr.png");
        board.getSquare(4, 4).setOccupyingPiece(rook);

        List<Square> legalMoves = rook.getLegalMoves(board);

        // Check it can move in all 4 directions
        assertTrue(legalMoves.contains(board.getSquare(4, 3))); // Up
        assertTrue(legalMoves.contains(board.getSquare(4, 5))); // Down
        assertTrue(legalMoves.contains(board.getSquare(3, 4))); // Left
        assertTrue(legalMoves.contains(board.getSquare(5, 4))); // Right
    }

    @Test
    public void testRookBlockedByFriendlyPiece() {
        Rook rook = new Rook(1, board.getSquare(4, 4), "wr.png");
        board.getSquare(4, 4).setOccupyingPiece(rook);

        Piece blocker = new Rook(1, board.getSquare(4, 6), "wr.png");
        board.getSquare(4, 6).setOccupyingPiece(blocker);

        List<Square> legalMoves = rook.getLegalMoves(board);

        assertTrue(legalMoves.contains(board.getSquare(4, 5)));
        assertFalse(legalMoves.contains(board.getSquare(4, 6)));
    }


    @Test
    public void testRookCanCaptureEnemy() {
        Rook rook = new Rook(1, board.getSquare(4, 4), "wr.png");
        board.getSquare(4, 4).setOccupyingPiece(rook);

        Piece enemy = new Rook(0, board.getSquare(4, 6), "br.png");
        board.getSquare(4, 6).setOccupyingPiece(enemy);

        List<Square> legalMoves = rook.getLegalMoves(board);

        System.out.println("Legal moves:");
        for (Square sq : legalMoves) {
            System.out.println("(" + sq.getXNum() + "," + sq.getYNum() + ")");
        }
        assertTrue(legalMoves.contains(board.getSquare(4, 6)));
        assertTrue(legalMoves.contains(board.getSquare(4, 5)));
    }

    @Test
    public void testRookCannotJumpOverPieces() {
        Rook rook = new Rook(1, board.getSquare(4, 4), "wr.png");
        board.getSquare(4, 4).setOccupyingPiece(rook);

        Piece blocker = new Rook(0, board.getSquare(4, 5), "br.png");
        board.getSquare(4, 5).setOccupyingPiece(blocker);

        List<Square> legalMoves = rook.getLegalMoves(board);

        assertTrue(legalMoves.contains(board.getSquare(4, 5)));
        assertFalse(legalMoves.contains(board.getSquare(4, 6)));
    }


    @Test
    public void testRookFromCorner() {
        Rook rook = new Rook(1, board.getSquare(0, 0), "wr.png");
        board.getSquare(0, 0).setOccupyingPiece(rook);

        List<Square> legalMoves = rook.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(1, 0)));
        assertTrue(legalMoves.contains(board.getSquare(0, 1)));
    }

}
