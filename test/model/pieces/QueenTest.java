package model.pieces;

import model.Square;
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
        board.setupEmptyBoard();
    }

    @Test
    public void testQueenMovesDiagonallyAndStraight() {
        Queen queen = new Queen(1, board.getSquare(3, 3), "wq.png");
        board.getSquare(3, 3).setOccupyingPiece(queen);

        List<Square> moves = queen.getLegalMoves(board);

        // Diagonal moves
        assertTrue(moves.contains(board.getSquare(2, 2)));
        assertTrue(moves.contains(board.getSquare(4, 4)));
        assertTrue(moves.contains(board.getSquare(2, 4)));
        assertTrue(moves.contains(board.getSquare(4, 2)));

        // Horizontal and vertical
        assertTrue(moves.contains(board.getSquare(3, 2)));
        assertTrue(moves.contains(board.getSquare(3, 4)));
        assertTrue(moves.contains(board.getSquare(2, 3)));
        assertTrue(moves.contains(board.getSquare(4, 3)));
    }

    @Test
    public void testQueenBlockedByFriendlyPiece() {
        Queen queen = new Queen(1, board.getSquare(3, 3), "wq.png");
        board.getSquare(3, 3).setOccupyingPiece(queen);
        board.getSquare(3, 4).setOccupyingPiece(new Pawn(1, board.getSquare(3, 4), "wp.png")); // friendly pawn

        List<Square> moves = queen.getLegalMoves(board);

        assertFalse(moves.contains(board.getSquare(3, 4)));
    }

    @Test
    public void testQueenCanCaptureEnemyPiece() {
        Queen queen = new Queen(1, board.getSquare(3, 3), "wq.png");
        board.getSquare(3, 3).setOccupyingPiece(queen);
        board.getSquare(3, 4).setOccupyingPiece(new Pawn(0, board.getSquare(3, 4), "bp.png")); // enemy pawn

        List<Square> moves = queen.getLegalMoves(board);

        assertTrue(moves.contains(board.getSquare(3, 4)));
    }
}