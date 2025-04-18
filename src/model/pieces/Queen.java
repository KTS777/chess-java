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
        Square[][] squares = board.getSquareArray();
        int x = getPosition().getXNum();
        int y = getPosition().getYNum();

        List<Square> moves = new LinkedList<>();
        int[] bounds = getLinearOccupations(squares, x, y);


        for (int i = bounds[0]; i < y; i++) moves.add(squares[i][x]);         // Up
        for (int i = y + 1; i <= bounds[1]; i++) moves.add(squares[i][x]);    // Down
        for (int i = bounds[2]; i < x; i++) moves.add(squares[y][i]);         // Left
        for (int i = x + 1; i <= bounds[3]; i++) moves.add(squares[y][i]);    // Right

        moves.addAll(getDiagonalOccupations(squares, x, y));

        return moves;
    }


    private boolean isInBounds(int i) {
        return i >= 0 && i < 8;
    }

}
