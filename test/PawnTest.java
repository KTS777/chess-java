import model.Piece;
import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;
import model.pieces.Pawn;
import model.pieces.Rook;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(null); // no GameWindow needed for logic tests
        board.setupEmptyBoard(); // helper you should implement to initialize 8x8 empty board
    }

    @Test
    public void testWhitePawnInitialDoubleMove() {
        Square startSquare = board.getSquare(3, 1); // d7
        Pawn pawn = new Pawn(0, startSquare, "bp.png");

        pawn.setPosition(startSquare);                 // ← Make sure position is set
        board.getSquare(3, 1).setOccupyingPiece(pawn); // ← Place pawn on board

        List<Square> legalMoves = pawn.getLegalMoves(board);
        System.out.println("Legal moves for black pawn at (3,1):");
        for (Square sq : legalMoves) {
            System.out.println("(" + sq.getXNum() + "," + sq.getYNum() + ")");
        }

        assertTrue(legalMoves.contains(board.getSquare(4, 5))); // e3
        assertTrue(legalMoves.contains(board.getSquare(4, 4))); // e4
    }

    @Test
    public void testBlackPawnInitialDoubleMove() {
        Pawn pawn = new Pawn(0, board.getSquare(3, 1), "bp.png");
        pawn.setPosition(board.getSquare(3, 1)); // ADD THIS LINE
        board.getSquare(3, 1).setOccupyingPiece(pawn);

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(3, 2))); // d6
        assertTrue(legalMoves.contains(board.getSquare(3, 3))); // d5
    }

    @Test
    public void testWhitePawnBlockedByPiece() {
        Pawn pawn = new Pawn(1, board.getSquare(4, 6), "wp.png"); // e2
        board.getSquare(4, 6).setOccupyingPiece(pawn);

        Piece blocker = new Rook(1, board.getSquare(4, 5), "wr.png");
        board.getSquare(4, 5).setOccupyingPiece(blocker); // e3 blocked

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertFalse(legalMoves.contains(board.getSquare(4, 5))); // can't go e3
        assertFalse(legalMoves.contains(board.getSquare(4, 4))); // can't go e4 either
    }

    @Test
    public void testPawnCapturesDiagonally() {
        Pawn pawn = new Pawn(1, board.getSquare(4, 4), "wp.png"); // e4
        board.getSquare(4, 4).setOccupyingPiece(pawn);

        Piece enemy1 = new Rook(0, board.getSquare(3, 3), "br.png");
        Piece enemy2 = new Rook(0, board.getSquare(5, 3), "br.png");
        board.getSquare(3, 3).setOccupyingPiece(enemy1); // d5
        board.getSquare(5, 3).setOccupyingPiece(enemy2); // f5

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(3, 3)));
        assertTrue(legalMoves.contains(board.getSquare(5, 3)));
    }

    @Test
    public void testPawnCannotCaptureForward() {
        Pawn pawn = new Pawn(1, board.getSquare(4, 4), "wp.png");
        board.getSquare(4, 4).setOccupyingPiece(pawn);

        Piece enemy = new Rook(0, board.getSquare(4, 3), "br.png");
        board.getSquare(4, 3).setOccupyingPiece(enemy); // directly in front

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertFalse(legalMoves.contains(board.getSquare(4, 3)));
    }
}
