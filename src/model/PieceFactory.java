package model;

import model.pieces.*;


import java.util.List;

import static view.PieceImages.*;

public class PieceFactory {

    public static King[] createStandardSetup(Square[][] board, List<Piece> Wpieces, List<Piece> Bpieces) {
        // Pawns
        for (int x = 0; x < 8; x++) {
            Pawn bp = new Pawn(0, board[1][x], BPAWN);
            Pawn wp = new Pawn(1, board[6][x], WPAWN);
            board[1][x].put(bp); Bpieces.add(bp);
            board[6][x].put(wp); Wpieces.add(wp);
        }

        // Queens
        Queen bq = new Queen(0, board[0][3], BQUEEN);
        Queen wq = new Queen(1, board[7][3], WQUEEN);
        board[0][3].put(bq); Bpieces.add(bq);
        board[7][3].put(wq); Wpieces.add(wq);

        // Kings
        King bk = new King(0, board[0][4], BKING);
        King wk = new King(1, board[7][4], WKING);
        board[0][4].put(bk); Bpieces.add(bk);
        board[7][4].put(wk); Wpieces.add(wk);

        // Rooks
        Rook br1 = new Rook(0, board[0][0], BROOK);
        Rook br2 = new Rook(0, board[0][7], BROOK);
        Rook wr1 = new Rook(1, board[7][0], WROOK);
        Rook wr2 = new Rook(1, board[7][7], WROOK);
        board[0][0].put(br1); Bpieces.add(br1);
        board[0][7].put(br2); Bpieces.add(br2);
        board[7][0].put(wr1); Wpieces.add(wr1);
        board[7][7].put(wr2); Wpieces.add(wr2);

        // Knights
        Knight bk1 = new Knight(0, board[0][1], BKNIGHT);
        Knight bk2 = new Knight(0, board[0][6], BKNIGHT);
        Knight wk1 = new Knight(1, board[7][1], WKNIGHT);
        Knight wk2 = new Knight(1, board[7][6], WKNIGHT);
        board[0][1].put(bk1); Bpieces.add(bk1);
        board[0][6].put(bk2); Bpieces.add(bk2);
        board[7][1].put(wk1); Wpieces.add(wk1);
        board[7][6].put(wk2); Wpieces.add(wk2);

        // Bishops
        Bishop bb1 = new Bishop(0, board[0][2], BBISHOP);
        Bishop bb2 = new Bishop(0, board[0][5], BBISHOP);
        Bishop wb1 = new Bishop(1, board[7][2], WBISHOP);
        Bishop wb2 = new Bishop(1, board[7][5], WBISHOP);
        board[0][2].put(bb1); Bpieces.add(bb1);
        board[0][5].put(bb2); Bpieces.add(bb2);
        board[7][2].put(wb1); Wpieces.add(wb1);
        board[7][5].put(wb2); Wpieces.add(wb2);

        return new King[]{ wk, bk };
    }
}
