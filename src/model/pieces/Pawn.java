package model.pieces;

import controller.GameController;
import model.Piece;
import model.Square;
import view.Board;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(int color, Square initSq, String imgFile) {
        super(color, initSq, imgFile);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        LinkedList<Square> legalMoves = new LinkedList<>();
        Square[][] board = b.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();
        int dir = (getColor() == 0) ? 1 : -1;

        Square enPassantTarget = null;
        if (b.getGameController() != null) {
            enPassantTarget = b.getGameController().getLastDoubleStepSquare();
        }

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
            Square rightDiag = board[y + dir][x + 1];
            if (rightDiag.isOccupied() && rightDiag.getOccupyingPiece().getColor() != getColor()) {
                legalMoves.add(rightDiag);
            }
            if (enPassantTarget != null && enPassantTarget.getXNum() == x + 1 && enPassantTarget.getYNum() == y) {
                legalMoves.add(board[y + dir][x + 1]);
            }
        }


        if (isInBounds(x - 1) && isInBounds(y + dir)) {
            Square leftDiag = board[y + dir][x - 1];
            if (leftDiag.isOccupied() && leftDiag.getOccupyingPiece().getColor() != getColor()) {
                legalMoves.add(leftDiag);
            }
            if (enPassantTarget != null && enPassantTarget.getXNum() == x - 1 && enPassantTarget.getYNum() == y) {
                legalMoves.add(board[y + dir][x - 1]);
            }
        }

        return legalMoves;
    }


    public boolean isPromotionRank() {
        int y = getPosition().getYNum();
        return (getColor() == 0 && y == 7) || (getColor() == 1 && y == 0);
    }


    private boolean isInBounds(int index) {
        return index >= 0 && index < 8;
    }
}
