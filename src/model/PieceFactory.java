package model;

import model.pieces.*;
import java.util.List;
import static view.PieceImages.*;

public class PieceFactory {

    private static final int WHITE = 1;
    private static final int BLACK = 0;
    private static final int ROW_WHITE_BACK = 7;
    private static final int ROW_WHITE_PAWN = 6;
    private static final int ROW_BLACK_BACK = 0;
    private static final int ROW_BLACK_PAWN = 1;

    public static King[] createStandardSetup(Square[][] board, List<Piece> Wpieces, List<Piece> Bpieces) {

        // Pawns
        for (int x = 0; x < 8; x++) {
            placePiece(new Pawn(BLACK, board[ROW_BLACK_PAWN][x], BPAWN), board[ROW_BLACK_PAWN][x], Bpieces);
            placePiece(new Pawn(WHITE, board[ROW_WHITE_PAWN][x], WPAWN), board[ROW_WHITE_PAWN][x], Wpieces);
        }

        // Queens
        Queen bq = placePiece(new Queen(BLACK, board[ROW_BLACK_BACK][3], BQUEEN), board[ROW_BLACK_BACK][3], Bpieces);
        Queen wq = placePiece(new Queen(WHITE, board[ROW_WHITE_BACK][3], WQUEEN), board[ROW_WHITE_BACK][3], Wpieces);

        // Kings
        King bk = placePiece(new King(BLACK, board[ROW_BLACK_BACK][4], BKING), board[ROW_BLACK_BACK][4], Bpieces);
        King wk = placePiece(new King(WHITE, board[ROW_WHITE_BACK][4], WKING), board[ROW_WHITE_BACK][4], Wpieces);

        // Rooks
        placePiece(new Rook(BLACK, board[ROW_BLACK_BACK][0], BROOK), board[ROW_BLACK_BACK][0], Bpieces);
        placePiece(new Rook(BLACK, board[ROW_BLACK_BACK][7], BROOK), board[ROW_BLACK_BACK][7], Bpieces);
        placePiece(new Rook(WHITE, board[ROW_WHITE_BACK][0], WROOK), board[ROW_WHITE_BACK][0], Wpieces);
        placePiece(new Rook(WHITE, board[ROW_WHITE_BACK][7], WROOK), board[ROW_WHITE_BACK][7], Wpieces);

        // Knights
        placePiece(new Knight(BLACK, board[ROW_BLACK_BACK][1], BKNIGHT), board[ROW_BLACK_BACK][1], Bpieces);
        placePiece(new Knight(BLACK, board[ROW_BLACK_BACK][6], BKNIGHT), board[ROW_BLACK_BACK][6], Bpieces);
        placePiece(new Knight(WHITE, board[ROW_WHITE_BACK][1], WKNIGHT), board[ROW_WHITE_BACK][1], Wpieces);
        placePiece(new Knight(WHITE, board[ROW_WHITE_BACK][6], WKNIGHT), board[ROW_WHITE_BACK][6], Wpieces);

        // Bishops
        placePiece(new Bishop(BLACK, board[ROW_BLACK_BACK][2], BBISHOP), board[ROW_BLACK_BACK][2], Bpieces);
        placePiece(new Bishop(BLACK, board[ROW_BLACK_BACK][5], BBISHOP), board[ROW_BLACK_BACK][5], Bpieces);
        placePiece(new Bishop(WHITE, board[ROW_WHITE_BACK][2], WBISHOP), board[ROW_WHITE_BACK][2], Wpieces);
        placePiece(new Bishop(WHITE, board[ROW_WHITE_BACK][5], WBISHOP), board[ROW_WHITE_BACK][5], Wpieces);

        return new King[]{ wk, bk };
    }

    private static <T extends Piece> T placePiece(T piece, Square square, List<Piece> collection) {
        square.setOccupyingPiece(piece);
        collection.add(piece);
        return piece;
    }
}
