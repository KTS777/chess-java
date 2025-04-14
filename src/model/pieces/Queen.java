package model.pieces;

import model.Piece;
import model.Square;
import view.Board;

import java.util.LinkedList;
import java.util.List;

public class Queen extends Piece {

    public Queen(int color, Square initSq, String imgFile) {
        super(color, initSq, imgFile);
    }

    @Override
    public List<Square> getLegalMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] squares = board.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();

        int[] linearLimits = getLinearOccupations(squares, x, y);
        int top = linearLimits[0];
        int bottom = linearLimits[1];
        int left = linearLimits[2];
        int right = linearLimits[3];

        // Vertical moves
        for (int i = top; i <= bottom; i++) {
            if (i != y) legalMoves.add(squares[i][x]);
        }

        // Horizontal moves
        for (int i = left; i <= right; i++) {
            if (i != x) legalMoves.add(squares[y][i]);
        }

        // Diagonal moves
        legalMoves.addAll(getDiagonalOccupations(squares, x, y));

        return legalMoves;
    }
}
