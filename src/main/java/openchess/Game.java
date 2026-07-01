package main.java.openchess;

public class Game {

    public Board board;
    public boolean whiteToMove = true;

    public Game() {
        board = new Board();
    }

    public void makeMove(Move move) {
        Piece p = board.board[move.fromY][move.fromX];
        board.board[move.toY][move.toX] = p;
        board.board[move.fromY][move.fromX] = null;

        whiteToMove = !whiteToMove;
    }
}
