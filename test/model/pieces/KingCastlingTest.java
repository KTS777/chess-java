package model.pieces;

import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KingCastlingTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(null);
        board.setupEmptyBoard();
    }

    // === WHITE CASTLING ===

    @Test
    public void testWhiteKingSideCastling() {
        King king = new King(1, board.getSquare(4, 7), "wk.png"); // e1
        Rook rook = new Rook(1, board.getSquare(7, 7), "wr.png"); // h1

        board.getSquare(4, 7).setOccupyingPiece(king);
        board.getSquare(7, 7).setOccupyingPiece(rook);

        board.getSquare(5, 7).setOccupyingPiece(null); // f1
        board.getSquare(6, 7).setOccupyingPiece(null); // g1

        king.setWasMoved(false);
        rook.setWasMoved(false);

        List<Square> legalMoves = king.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(6, 7)), "White king should be able to castle kingside to g1");
    }

    @Test
    public void testWhiteQueenSideCastling() {
        King king = new King(1, board.getSquare(4, 7), "wk.png"); // e1
        Rook rook = new Rook(1, board.getSquare(0, 7), "wr.png"); // a1

        board.getSquare(4, 7).setOccupyingPiece(king);
        board.getSquare(0, 7).setOccupyingPiece(rook);

        board.getSquare(1, 7).setOccupyingPiece(null); // b1
        board.getSquare(2, 7).setOccupyingPiece(null); // c1
        board.getSquare(3, 7).setOccupyingPiece(null); // d1

        king.setWasMoved(false);
        rook.setWasMoved(false);

        List<Square> legalMoves = king.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(2, 7)), "White king should be able to castle queenside to c1");
    }

    @Test
    public void testWhiteNoCastlingIfKingMoved() {
        King king = new King(1, board.getSquare(4, 7), "wk.png");
        Rook rook = new Rook(1, board.getSquare(7, 7), "wr.png");

        board.getSquare(4, 7).setOccupyingPiece(king);
        board.getSquare(7, 7).setOccupyingPiece(rook);

        board.getSquare(5, 7).setOccupyingPiece(null);
        board.getSquare(6, 7).setOccupyingPiece(null);

        king.setWasMoved(true);
        rook.setWasMoved(false);

        List<Square> legalMoves = king.getLegalMoves(board);
        assertFalse(legalMoves.contains(board.getSquare(6, 7)), "Castling should be disallowed if white king moved");
    }

    // === BLACK CASTLING ===

    @Test
    public void testBlackKingSideCastling() {
        King king = new King(0, board.getSquare(4, 0), "bk.png"); // e8
        Rook rook = new Rook(0, board.getSquare(7, 0), "br.png"); // h8

        board.getSquare(4, 0).setOccupyingPiece(king);
        board.getSquare(7, 0).setOccupyingPiece(rook);

        board.getSquare(5, 0).setOccupyingPiece(null); // f8
        board.getSquare(6, 0).setOccupyingPiece(null); // g8

        king.setWasMoved(false);
        rook.setWasMoved(false);

        List<Square> legalMoves = king.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(6, 0)), "Black king should be able to castle kingside to g8");
    }

    @Test
    public void testBlackQueenSideCastling() {
        King king = new King(0, board.getSquare(4, 0), "bk.png"); // e8
        Rook rook = new Rook(0, board.getSquare(0, 0), "br.png"); // a8

        board.getSquare(4, 0).setOccupyingPiece(king);
        board.getSquare(0, 0).setOccupyingPiece(rook);

        board.getSquare(1, 0).setOccupyingPiece(null); // b8
        board.getSquare(2, 0).setOccupyingPiece(null); // c8
        board.getSquare(3, 0).setOccupyingPiece(null); // d8

        king.setWasMoved(false);
        rook.setWasMoved(false);

        List<Square> legalMoves = king.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(2, 0)), "Black king should be able to castle queenside to c8");
    }

    @Test
    public void testBlackNoCastlingIfKingMoved() {
        King king = new King(0, board.getSquare(4, 0), "bk.png");
        Rook rook = new Rook(0, board.getSquare(7, 0), "br.png");

        board.getSquare(4, 0).setOccupyingPiece(king);
        board.getSquare(7, 0).setOccupyingPiece(rook);

        board.getSquare(5, 0).setOccupyingPiece(null);
        board.getSquare(6, 0).setOccupyingPiece(null);

        king.setWasMoved(true);
        rook.setWasMoved(false);

        List<Square> legalMoves = king.getLegalMoves(board);
        assertFalse(legalMoves.contains(board.getSquare(6, 0)), "Castling should be disallowed if black king moved");
    }
}
