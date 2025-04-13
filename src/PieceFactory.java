import java.util.List;

public class PieceFactory {

    public static King[] createStandardSetup(Square[][] board, List<Piece> Wpieces, List<Piece> Bpieces) {
        // Pawns
        for (int x = 0; x < 8; x++) {
            Pawn bp = new Pawn(0, board[1][x], PieceImages.BPAWN);
            Pawn wp = new Pawn(1, board[6][x], PieceImages.WPAWN);
            board[1][x].put(bp); Bpieces.add(bp);
            board[6][x].put(wp); Wpieces.add(wp);
        }

        // Queens
        Queen bq = new Queen(0, board[0][3], PieceImages.BQUEEN);
        Queen wq = new Queen(1, board[7][3], PieceImages.WQUEEN);
        board[0][3].put(bq); Bpieces.add(bq);
        board[7][3].put(wq); Wpieces.add(wq);

        // Kings
        King bk = new King(0, board[0][4], PieceImages.BKING);
        King wk = new King(1, board[7][4], PieceImages.WKING);
        board[0][4].put(bk); Bpieces.add(bk);
        board[7][4].put(wk); Wpieces.add(wk);

        // Rooks
        Rook br1 = new Rook(0, board[0][0], PieceImages.BROOK);
        Rook br2 = new Rook(0, board[0][7], PieceImages.BROOK);
        Rook wr1 = new Rook(1, board[7][0], PieceImages.WROOK);
        Rook wr2 = new Rook(1, board[7][7], PieceImages.WROOK);
        board[0][0].put(br1); Bpieces.add(br1);
        board[0][7].put(br2); Bpieces.add(br2);
        board[7][0].put(wr1); Wpieces.add(wr1);
        board[7][7].put(wr2); Wpieces.add(wr2);

        // Knights
        Knight bk1 = new Knight(0, board[0][1], PieceImages.BKNIGHT);
        Knight bk2 = new Knight(0, board[0][6], PieceImages.BKNIGHT);
        Knight wk1 = new Knight(1, board[7][1], PieceImages.WKNIGHT);
        Knight wk2 = new Knight(1, board[7][6], PieceImages.WKNIGHT);
        board[0][1].put(bk1); Bpieces.add(bk1);
        board[0][6].put(bk2); Bpieces.add(bk2);
        board[7][1].put(wk1); Wpieces.add(wk1);
        board[7][6].put(wk2); Wpieces.add(wk2);

        // Bishops
        Bishop bb1 = new Bishop(0, board[0][2], PieceImages.BBISHOP);
        Bishop bb2 = new Bishop(0, board[0][5], PieceImages.BBISHOP);
        Bishop wb1 = new Bishop(1, board[7][2], PieceImages.WBISHOP);
        Bishop wb2 = new Bishop(1, board[7][5], PieceImages.WBISHOP);
        board[0][2].put(bb1); Bpieces.add(bb1);
        board[0][5].put(bb2); Bpieces.add(bb2);
        board[7][2].put(wb1); Wpieces.add(wb1);
        board[7][5].put(wb2); Wpieces.add(wb2);

        return new King[]{ wk, bk };
    }
}
