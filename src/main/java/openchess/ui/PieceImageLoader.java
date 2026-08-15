package main.java.openchess.ui;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.openchess.Piece;

public class PieceImageLoader {

    public BufferedImage[] pieceImages = new BufferedImage[12];

    public void loadPieceImages() {
        try {
            pieceImages[0] = ImageIO.read(getClass().getResourceAsStream("/assets/w-pawn.png"));
            pieceImages[1] = ImageIO.read(getClass().getResourceAsStream("/assets/w-rook.png"));
            pieceImages[2] = ImageIO.read(getClass().getResourceAsStream("/assets/w-knight.png"));
            pieceImages[3] = ImageIO.read(getClass().getResourceAsStream("/assets/w-bishop.png"));
            pieceImages[4] = ImageIO.read(getClass().getResourceAsStream("/assets/w-queen.png"));
            pieceImages[5] = ImageIO.read(getClass().getResourceAsStream("/assets/w-king.png"));

            pieceImages[6] = ImageIO.read(getClass().getResourceAsStream("/assets/b-pawn.png"));
            pieceImages[7] = ImageIO.read(getClass().getResourceAsStream("/assets/b-rook.png"));
            pieceImages[8] = ImageIO.read(getClass().getResourceAsStream("/assets/b-knight.png"));
            pieceImages[9] = ImageIO.read(getClass().getResourceAsStream("/assets/b-bishop.png"));
            pieceImages[10] = ImageIO.read(getClass().getResourceAsStream("/assets/b-queen.png"));
            pieceImages[11] = ImageIO.read(getClass().getResourceAsStream("/assets/b-king.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPieceIndex(Piece piece) {
    if (piece == null) return -1;

    int base = piece.color == Piece.Color.WHITE ? 0 : 6;

    return switch (piece.type) {
        case PAWN -> base + 0;
        case ROOK -> base + 1;
        case KNIGHT -> base + 2;
        case BISHOP -> base + 3;
        case QUEEN -> base + 4;
        case KING -> base + 5;
    };
}

}
