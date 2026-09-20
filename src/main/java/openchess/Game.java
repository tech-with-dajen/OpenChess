package main.java.openchess;

public class Game {

    public Board board;
    public boolean whiteToMove = true;

    public Game() {
        board = new Board();
    }

    public boolean makeMove(Move move) {
        Piece.Color currentColor = whiteToMove ? Piece.Color.WHITE : Piece.Color.BLACK;
        if (!Rules.isLegalMove(board, move, currentColor)) {
            return false;
        }

        Piece p = board.board[move.fromY][move.fromX];
        board.board[move.toY][move.toX] = p;
        board.board[move.fromY][move.fromX] = null;

        whiteToMove = !whiteToMove;
        return true;
    }
}
