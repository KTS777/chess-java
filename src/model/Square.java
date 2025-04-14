package model;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Square extends JComponent {
    private final int color;
    private final int xNum;
    private final int yNum;

    private Piece occupyingPiece;
    private boolean dispPiece = true;

    public Square(int color, int xNum, int yNum) {
        this.color = color;
        this.xNum = xNum;
        this.yNum = yNum;
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    public void setOccupyingPiece(Piece piece) {
        this.occupyingPiece = piece;
        if (piece != null) {
            piece.setPosition(this);
        }
    }

    public Piece removePiece() {
        Piece temp = this.occupyingPiece;
        this.occupyingPiece = null;
        return temp;
    }

    public boolean isOccupied() {
        return this.occupyingPiece != null;
    }

    public int getXNum() {
        return this.xNum;
    }

    public int getYNum() {
        return this.yNum;
    }

    public void setDisplay(boolean value) {
        this.dispPiece = value;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.color == 1 ? new Color(221, 192, 127) : new Color(101, 67, 33));
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        if (occupyingPiece != null && dispPiece) {
            occupyingPiece.draw(g);
        }
    }

    @Override
    public int hashCode() {
        int result = 31 * xNum + yNum;
        return result;
    }
}