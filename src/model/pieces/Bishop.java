package model.pieces;

import model.Piece;
import model.Square;
import view.Board;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(int color, Square initSq, String imgFile) {
        super(color, initSq, imgFile);
    }

    @Override
    public List<Square> getLegalMoves(Board board) {
        Square[][] squares = board.getSquareArray();
        int x = getPosition().getXNum();
        int y = getPosition().getYNum();

        return getDiagonalOccupations(squares, x, y);
    }
}
