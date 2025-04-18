package model.pieces;

import model.Piece;
import model.Square;
import view.Board;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece {

    private static final int[][] KNIGHT_MOVES = {
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };

    public Knight(int color, Square initSq, String imgFile) {
        super(color, initSq, imgFile);
    }

    @Override
    public List<Square> getLegalMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] squares = board.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();

        for (int[] move : KNIGHT_MOVES) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (isInBounds(newX, newY)) {
                Square target = squares[newY][newX];
                if (!target.isOccupied() || target.getOccupyingPiece().getColor() != getColor()) {
                    legalMoves.add(target);
                }
            }

        }

        return legalMoves;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
