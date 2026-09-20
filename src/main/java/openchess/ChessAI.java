package main.java.openchess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** A first computer opponent that selects one of its legal moves at random. */
public class ChessAI {

    private final Random random = new Random();

    /**
     * Returns a random legal move for the given color, or null when no move exists.
     */
    public Move findRandomMove(Board board, Piece.Color color) {
        List<Move> legalMoves = new ArrayList<>();

        for (int fromY = 0; fromY < 8; fromY++) {
            for (int fromX = 0; fromX < 8; fromX++) {
                Piece piece = board.board[fromY][fromX];
                if (piece == null || piece.color != color) {
                    continue;
                }

                for (int toY = 0; toY < 8; toY++) {
                    for (int toX = 0; toX < 8; toX++) {
                        Move move = new Move(fromX, fromY, toX, toY);
                        if (Rules.isLegalMove(board, move, color)) {
                            legalMoves.add(move);
                        }
                    }
                }
            }
        }

        if (legalMoves.isEmpty()) {
            return null;
        }
        return legalMoves.get(random.nextInt(legalMoves.size()));
    }
}
