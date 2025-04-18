package model.pieces;

import controller.GameController;
import controller.MoveService;
import model.Piece;
import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Board;
import view.GameWindow;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    private Board board;
    private MoveService moveService;

    @BeforeEach
    void setup() {
        board = new Board(null);
        board.setupEmptyBoard();
        moveService = new MoveService();
    }

    @Test
    void testPawnSingleMoveForward() {
        Pawn pawn = new Pawn(1, board.getSquare(4, 6), "wp.png");
        board.getSquare(4, 6).setOccupyingPiece(pawn);

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(4, 5)));
    }

    @Test
    void testPawnDoubleMoveInitial() {
        Pawn pawn = new Pawn(1, board.getSquare(4, 6), "wp.png");
        board.getSquare(4, 6).setOccupyingPiece(pawn);

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(4, 4)));
    }

    @Test
    void testPawnBlockedByPiece() {
        Pawn pawn = new Pawn(1, board.getSquare(4, 6), "wp.png");
        board.getSquare(4, 6).setOccupyingPiece(pawn);

        Piece blocker = new Rook(0, board.getSquare(4, 5), "br.png");
        board.getSquare(4, 5).setOccupyingPiece(blocker);

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertFalse(legalMoves.contains(board.getSquare(4, 5)));
        assertFalse(legalMoves.contains(board.getSquare(4, 4)));
    }

    @Test
    void testPawnCapturesDiagonally() {
        Pawn pawn = new Pawn(1, board.getSquare(4, 6), "wp.png");
        board.getSquare(4, 6).setOccupyingPiece(pawn);

        Piece enemy1 = new Rook(0, board.getSquare(3, 5), "br.png");
        Piece enemy2 = new Rook(0, board.getSquare(5, 5), "br.png");

        board.getSquare(3, 5).setOccupyingPiece(enemy1);
        board.getSquare(5, 5).setOccupyingPiece(enemy2);

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(3, 5)));
        assertTrue(legalMoves.contains(board.getSquare(5, 5)));
    }

    @Test
    void testPawnCannotCaptureForward() {
        Pawn pawn = new Pawn(1, board.getSquare(4, 6), "wp.png");
        board.getSquare(4, 6).setOccupyingPiece(pawn);

        Piece enemy = new Rook(0, board.getSquare(4, 5), "br.png");
        board.getSquare(4, 5).setOccupyingPiece(enemy);

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertFalse(legalMoves.contains(board.getSquare(4, 5)));
    }

    @Test
    void testPawnPromotionPossible() {
        Pawn pawn = new Pawn(1, board.getSquare(0, 1), "wp.png");
        board.getSquare(0, 1).setOccupyingPiece(pawn);

        List<Square> legalMoves = pawn.getLegalMoves(board);
        assertTrue(legalMoves.contains(board.getSquare(0, 0)));
    }

    @Test
    void testWhiteEnPassantCaptureRight() {
        Board board = new Board(new GameWindow("Black", "White", 0, 0, 0));
        Square[][] squares = board.getSquareArray();
        GameController controller = board.getGameController();

        Pawn whitePawn = new Pawn(1, squares[3][4], "wp.png");
        squares[3][4].setOccupyingPiece(whitePawn);
        board.getWhitePieces().add(whitePawn);

        Pawn blackPawn = new Pawn(0, squares[1][5], "bp.png");
        squares[1][5].setOccupyingPiece(blackPawn);
        board.getBlackPieces().add(blackPawn);

        squares[1][5].removePiece();
        squares[3][5].setOccupyingPiece(blackPawn);
        blackPawn.setPosition(squares[3][5]);
        blackPawn.setWasMoved(true);

        controller.setLastDoubleStepSquare(squares[3][5]);

        List<Square> legalMoves = whitePawn.getLegalMoves(board);
        assertTrue(legalMoves.contains(squares[2][5]), "White should be able to capture en passant to f6");
    }



    @Test
    void testWhitePawnPromotionToQueen() {
        Square start = board.getSquare(0, 1);
        Square end = board.getSquare(0, 0);

        Pawn whitePawn = new Pawn(1, start, "wp.png");
        board.getWhitePieces().add(whitePawn);
        start.setOccupyingPiece(whitePawn);

        boolean moved = moveService.applyMove(whitePawn, end, board);

        assertTrue(moved, "Move should succeed");
        assertTrue(end.getOccupyingPiece() instanceof Queen, "Pawn should promote to Queen");
        assertEquals(1, end.getOccupyingPiece().getColor(), "Promoted Queen should be white");
    }

    @Test
    void testBlackPawnPromotionToQueen() {
        Square start = board.getSquare(0, 6);
        Square end = board.getSquare(0, 7);

        Pawn blackPawn = new Pawn(0, start, "bp.png");
        board.getBlackPieces().add(blackPawn);
        start.setOccupyingPiece(blackPawn);

        boolean moved = moveService.applyMove(blackPawn, end, board);

        assertTrue(moved, "Move should succeed");
        assertTrue(end.getOccupyingPiece() instanceof Queen, "Pawn should promote to Queen");
        assertEquals(0, end.getOccupyingPiece().getColor(), "Promoted Queen should be black");
    }

}
