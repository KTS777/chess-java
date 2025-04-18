package model.pieces;

import model.Piece;
import model.Square;
import view.Board;

import java.util.List;
import java.util.LinkedList;

public class Pawn extends Piece {
    private boolean wasMoved;
    
    public Pawn(int color, Square initSq, String img_file) {
        super(color, initSq, img_file);
    }

    public boolean wasMoved() {
        return wasMoved;
    }

    public void setWasMoved(boolean moved) {
        this.wasMoved = moved;
    }


    @Override
    public List<Square> getLegalMoves(Board b) {
        LinkedList<Square> legalMoves = new LinkedList<>();
        Square[][] board = b.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();
        int dir = (getColor() == 0) ? 1 : -1; // Black = down, White = up


        if (isInBounds(y + dir) && !board[y + dir][x].isOccupied()) {
            legalMoves.add(board[y + dir][x]);


            if (!wasMoved()) {
                int doubleStepY = y + 2 * dir;
                if (isInBounds(doubleStepY) && !board[doubleStepY][x].isOccupied()) {
                    legalMoves.add(board[doubleStepY][x]);
                }
            }
        }


        if (isInBounds(x + 1) && isInBounds(y + dir)) {
            if (board[y + dir][x + 1].isOccupied()) {
                legalMoves.add(board[y + dir][x + 1]);
            }
        }
        if (isInBounds(x - 1) && isInBounds(y + dir)) {
            if (board[y + dir][x - 1].isOccupied()) {
                legalMoves.add(board[y + dir][x - 1]);
            }
        }

        return legalMoves;
    }

    private boolean isInBounds(int i) {
        return i >= 0 && i < 8;
    }

}
