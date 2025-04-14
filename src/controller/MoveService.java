package controller;

import model.Piece;
import model.Square;
import model.pieces.Pawn;
import view.Board;

public class MoveService {

    /**
     * Attempts to move a piece to a new square, handling captures.
     * Returns true if the move was successful.
     */
    public boolean applyMove(Piece piece, Square destination, Board board) {
        Piece target = destination.getOccupyingPiece();

        if (target != null) {
            if (target.getColor() == piece.getColor()) {
                return false; // Can't capture own piece
            } else {
                capturePiece(destination, piece, board);
            }
        }

        Square origin = piece.getPosition();
        origin.removePiece();

        destination.setOccupyingPiece(piece); // replaces put()
        piece.setPosition(destination);

        if (piece instanceof Pawn pawn) {
            pawn.setWasMoved(true);
        }

        return true;
    }

    /**
     * Handles removing the captured piece from the board and placing the attacker.
     */
    public void capturePiece(Square target, Piece attacker, Board board) {
        Piece defender = target.getOccupyingPiece();
        if (defender == null) return;

        if (defender.getColor() == 0) {
            board.getBlackPieces().remove(defender);
        } else {
            board.getWhitePieces().remove(defender);
        }

        target.setOccupyingPiece(attacker);
    }
}
