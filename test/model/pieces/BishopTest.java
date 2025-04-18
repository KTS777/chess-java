package model.pieces;

import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {
    private Board board;
    private Bishop whiteBishop;

    @BeforeEach
    public void setUp() {
        SwingUtilities.invokeLater(() -> {});
        board = new Board(null);
        board.setupEmptyBoard();
        whiteBishop = new Bishop(1, board.getSquare(3, 3), "wbishop.png");
        board.getSquare(3, 3).setOccupyingPiece(whiteBishop);
    }

    @Test
    public void testEmptyBoardDiagonalMoves() {
        List<Square> moves = whiteBishop.getLegalMoves(board);

        assertTrue(moves.contains(board.getSquare(2, 2)));
        assertTrue(moves.contains(board.getSquare(1, 1)));
        assertTrue(moves.contains(board.getSquare(0, 0)));

        assertTrue(moves.contains(board.getSquare(4, 4)));
        assertTrue(moves.contains(board.getSquare(5, 5)));
        assertTrue(moves.contains(board.getSquare(6, 6)));
        assertTrue(moves.contains(board.getSquare(7, 7)));

        assertTrue(moves.contains(board.getSquare(2, 4)));
        assertTrue(moves.contains(board.getSquare(1, 5)));
        assertTrue(moves.contains(board.getSquare(0, 6)));

        assertTrue(moves.contains(board.getSquare(4, 2)));
        assertTrue(moves.contains(board.getSquare(5, 1)));
        assertTrue(moves.contains(board.getSquare(6, 0)));
    }

    @Test
    public void testBishopBlockedByOwnPiece() {
        Bishop blocker = new Bishop(1, board.getSquare(5, 5), "wbishop.png");
        board.getSquare(5, 5).setOccupyingPiece(blocker);

        List<Square> moves = whiteBishop.getLegalMoves(board);
        assertFalse(moves.contains(board.getSquare(5, 5)));
        assertFalse(moves.contains(board.getSquare(6, 6)));
    }

    @Test
    public void testBishopCanCaptureOpponent() {
        Bishop blackBishop = new Bishop(0, board.getSquare(1, 1), "bbishop.png");
        board.getSquare(1, 1).setOccupyingPiece(blackBishop);

        List<Square> moves = whiteBishop.getLegalMoves(board);
        assertTrue(moves.contains(board.getSquare(1, 1)));
    }
}