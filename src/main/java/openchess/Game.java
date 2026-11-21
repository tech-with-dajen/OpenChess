package main.java.openchess;
public class Game {

    public Board board;
    public boolean whiteToMove = true;

    public Game() {
        board = new Board();
    }

public boolean makeMove(Move move) {
    Piece.Color currentColor = whiteToMove
            ? Piece.Color.WHITE
            : Piece.Color.BLACK;

    if (!board.movePiece(move, currentColor)) {
        return false;
    }

    whiteToMove = !whiteToMove;
    return true;
}
}
