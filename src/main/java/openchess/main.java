package main.java.openchess;

public class main {
    
    public static void Main(String[] args) {
        Game game = new Game();
        // Example move: Move a white pawn from e2 to e4
        Move move = new Move(4, 6, 4, 4); // Coordinates are (x, y) with (0,0) at top-left
        game.makeMove(move);
    }
}
