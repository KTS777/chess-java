import model.pieces.King;
import model.pieces.Queen;
import model.pieces.Rook;
import model.Square;
import view.Board;
import controller.CheckmateDetector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(null);
        board.setupEmptyBoard();
    }

    @Test
    public void testKingInCheck() {
        King whiteKing = new King(1, board.getSquare(4, 7), "wk.png");
        board.getSquare(4, 7).setOccupyingPiece(whiteKing);

        Rook blackRook = new Rook(0, board.getSquare(4, 0), "br.png");
        board.getSquare(4, 0).setOccupyingPiece(blackRook);

        CheckmateDetector detector = new CheckmateDetector(
                board,
                new LinkedList<>(List.of(whiteKing)),
                new LinkedList<>(List.of(blackRook)),
                whiteKing,
                null
        );


        assertTrue(detector.whiteInCheck());
    }

    @Test
    public void testWhiteKingCheckmate() {
        King whiteKing = new King(1, board.getSquare(0, 0), "wk.png");
        board.getSquare(0, 0).setOccupyingPiece(whiteKing);

        Rook blackRook1 = new Rook(0, board.getSquare(0, 1), "br.png");
        Rook blackRook2 = new Rook(0, board.getSquare(1, 0), "br.png");
        board.getSquare(0, 1).setOccupyingPiece(blackRook1);
        board.getSquare(1, 0).setOccupyingPiece(blackRook2);

        CheckmateDetector detector = new CheckmateDetector(
                board,
                new LinkedList<>(List.of(whiteKing)),
                new LinkedList<>(List.of(blackRook1, blackRook2)),
                whiteKing,
                null
        );

        assertTrue(detector.whiteCheckMated());
    }

    @Test
    public void testWhiteKingStalemate() {
        King whiteKing = new King(1, board.getSquare(0, 0), "wk.png");
        board.getSquare(0, 0).setOccupyingPiece(whiteKing);

        Queen blackQueen = new Queen(0, board.getSquare(1, 2), "bq.png");
        board.getSquare(1, 2).setOccupyingPiece(blackQueen);

        King blackKing = new King(0, board.getSquare(2, 1), "bk.png");
        board.getSquare(2, 1).setOccupyingPiece(blackKing);

        CheckmateDetector detector = new CheckmateDetector(
                board,
                new LinkedList<>(List.of(whiteKing)),
                new LinkedList<>(List.of(blackQueen, blackKing)),
                whiteKing,
                blackKing
        );

        assertTrue(detector.whiteStalemated());
    }


}
