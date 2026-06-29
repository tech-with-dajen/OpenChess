package main.java.openchess;

public class Game {
    public Board board;
    public boolean isWhiteTurn;

    public Game() {
        board = new Board();
        isWhiteTurn = true; // White starts first
    }
    public void makeMove(Move move){
        Piece p=board.board[move.startX][move.startY];
        board.board[move.endX][move.endY] = p;
        board.board[move.startX][move.startY] = null;
        isWhiteTurn = !isWhiteTurn; // Switch turns
    }
}
