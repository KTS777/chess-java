package view;

import controller.CheckmateDetector;
import controller.GameController;
import model.Piece;
import model.PieceFactory;
import model.Square;
import model.pieces.King;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel {

	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
    
    // List of pieces and whether they are movable
    private final LinkedList<Piece> Bpieces;
    private final LinkedList<Piece> Wpieces;
    public List<Square> movable;
    

    private int dragX;
    private int dragY;

    private GameController gameController;

    private final BoardRenderer renderer = new BoardRenderer();


    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        Bpieces = new LinkedList<Piece>();
        Wpieces = new LinkedList<Piece>();

        setLayout(new GridLayout(8, 8, 0, 0));
        registerInputListeners();

        initializeBoardSquares();
        initializePieces();
        configureBoardSize();
    }

    private void registerInputListeners() {
        BoardMouseHandler handler = new BoardMouseHandler(this);
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);

    }

    private void configureBoardSize() {
        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));
    }

    private void initializeBoardSquares() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int xMod = x % 2;
                int yMod = y % 2;

                if ((xMod == 0 && yMod == 0) || (xMod == 1 && yMod == 1)) {
                    board[x][y] = new Square(this, 1, y, x);
                    this.add(board[x][y]);
                } else {
                    board[x][y] = new Square(this, 0, y, x);
                    this.add(board[x][y]);
                }
            }
        }
    }

    private void initializePieces() {

        King[] kings = PieceFactory.createStandardSetup(board, Wpieces, Bpieces);

        CheckmateDetector cmd = new CheckmateDetector(this, Wpieces, Bpieces, kings[0], kings[1]);
        gameController = new GameController(cmd, this);

    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return gameController.isWhiteTurn();
    }

    public void setCurrPiece(Piece p) {
        gameController.setCurrentPiece(p);
    }

    public Piece getCurrPiece() {
        return gameController.getCurrentPiece();
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameWindow getGameWindow() {
        return g;
    }

    public void setDragCoordinates(int x, int y) {
        this.dragX = x;
        this.dragY = y;
    }


    @Override
    public void paintComponent(Graphics g) {
        renderer.render(g, board, gameController.getCurrentPiece(), gameController.isWhiteTurn(), dragX, dragY);
    }

    public List<Piece> getWhitePieces() {
        return Wpieces;
    }

    public List<Piece> getBlackPieces() {
        return Bpieces;
    }


}