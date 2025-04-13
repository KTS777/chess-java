package controller;

import model.Piece;
import model.Square;
import view.Board;

import java.util.List;

public class GameController {
    private boolean whiteTurn;
    private Piece currPiece;
    private CheckmateDetector checkmateDetector;
    private boolean gameOver = false;
    private int winningColor = -1;
    private final Board board;



    public GameController(CheckmateDetector checkmateDetector, Board board) {
        this.checkmateDetector = checkmateDetector;
        this.board = board;
    }


    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void switchTurn() {
        whiteTurn = !whiteTurn;
    }

    public Piece getCurrentPiece() {
        return currPiece;
    }

    public void setCurrentPiece(Piece currPiece) {
        this.currPiece = currPiece;
    }

    public CheckmateDetector getCheckmateDetector() {
        return checkmateDetector;
    }

    public boolean isCorrectPlayerTurn(Piece piece) {
        return (piece.getColor() == 1 && whiteTurn) ||
                (piece.getColor() == 0 && !whiteTurn);
    }

    public void selectPiece(Square square) {
        if (!square.isOccupied()) return;

        Piece piece = square.getOccupyingPiece();
        if (!isCorrectPlayerTurn(piece)) return;

        this.currPiece = piece;
        square.setDisplay(false);
    }

    public boolean handlePieceDrop(Square targetSquare) {
        if (currPiece == null || !isCorrectPlayerTurn(currPiece)) {
            return false;
        }

        List<Square> legalMoves = currPiece.getLegalMoves(board);
        List<Square> movableSquares = checkmateDetector.getAllowableSquares(whiteTurn);

        boolean validMove = legalMoves.contains(targetSquare)
                && movableSquares.contains(targetSquare)
                && checkmateDetector.testMove(currPiece, targetSquare);

        if (validMove) {
            applyMove(targetSquare);
            return true;
        } else {
            cancelMove();
            return false;
        }
    }

    private void applyMove(Square targetSquare) {
        targetSquare.setDisplay(true);
        currPiece.move(targetSquare, board);
        checkmateDetector.update();

        if (checkmateDetector.blackCheckMated()) {
            finishGame(0);
        } else if (checkmateDetector.whiteCheckMated()) {
            finishGame(1);
        } else {
            currPiece = null;
            switchTurn();
        }
    }

    private void cancelMove() {
        if (currPiece != null) {
            currPiece.getPosition().setDisplay(true);
            currPiece = null;
        }
    }

    private void finishGame(int winningColor) {
        this.gameOver = true;
        this.winningColor = winningColor;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWinningColor() {
        return winningColor;
    }


}
