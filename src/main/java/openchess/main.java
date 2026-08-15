package main.java.openchess;

public class main {
    
public static void main(String[] args) {
    Game game = new Game();
    Move move = new Move(4, 6, 4, 4);
    game.makeMove(move);
}
}
