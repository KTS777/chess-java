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

        // Directions: {dx, dy}
        int[][] directions = {
                {0, -1},  // up
                {0, 1},   // down
                {-1, 0},  // left
                {1, 0},   // right
                {-1, -1}, // top-left
                {-1, 1},  // bottom-left
                {1, -1},  // top-right
                {1, 1}    // bottom-right
        };

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];
            int currX = x + dx;
            int currY = y + dy;

            while (isInBounds(currX) && isInBounds(currY)) {
                Square target = squares[currY][currX];
                if (!target.isOccupied()) {
                    legalMoves.add(target);
                } else {
                    if (target.getOccupyingPiece().getColor() != getColor()) {
                        legalMoves.add(target);
                    }
                    break;
                }

                currX += dx;
                currY += dy;
            }
        }

        return legalMoves;
    }

    private boolean isInBounds(int i) {
        return i >= 0 && i < 8;
    }

}
