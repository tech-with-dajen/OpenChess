package main.java.openchess;

public class Board {

    public Piece[][] board = new Piece[8][8];
    private Move lastMove;

    public Board() {
        resetBoard();
    }

    /** Creates an independent board array for testing a possible move. */
    public Board(Board original) {
        for (int y = 0; y < 8; y++) {
            System.arraycopy(original.board[y], 0, board[y], 0, 8);
        }

        this.lastMove = original.lastMove;
    }

    public Move getLastMove() {
        return lastMove;
    }

    private void resetBoard() {
        // Black pieces
        board[0][0] = new Piece(Piece.Type.ROOK, Piece.Color.BLACK);
        board[0][1] = new Piece(Piece.Type.KNIGHT, Piece.Color.BLACK);
        board[0][2] = new Piece(Piece.Type.BISHOP, Piece.Color.BLACK);
        board[0][3] = new Piece(Piece.Type.QUEEN, Piece.Color.BLACK);
        board[0][4] = new Piece(Piece.Type.KING, Piece.Color.BLACK);
        board[0][5] = new Piece(Piece.Type.BISHOP, Piece.Color.BLACK);
        board[0][6] = new Piece(Piece.Type.KNIGHT, Piece.Color.BLACK);
        board[0][7] = new Piece(Piece.Type.ROOK, Piece.Color.BLACK);

        for (int x = 0; x < 8; x++) {
            board[1][x] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        }

        // Empty middle rows
        for (int y = 2; y < 6; y++) {
            for (int x = 0; x < 8; x++) {
                board[y][x] = null;
            }
        }

        // White pieces
        for (int x = 0; x < 8; x++) {
            board[6][x] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        }

        board[7][0] = new Piece(Piece.Type.ROOK, Piece.Color.WHITE);
        board[7][1] = new Piece(Piece.Type.KNIGHT, Piece.Color.WHITE);
        board[7][2] = new Piece(Piece.Type.BISHOP, Piece.Color.WHITE);
        board[7][3] = new Piece(Piece.Type.QUEEN, Piece.Color.WHITE);
        board[7][4] = new Piece(Piece.Type.KING, Piece.Color.WHITE);
        board[7][5] = new Piece(Piece.Type.BISHOP, Piece.Color.WHITE);
        board[7][6] = new Piece(Piece.Type.KNIGHT, Piece.Color.WHITE);
        board[7][7] = new Piece(Piece.Type.ROOK, Piece.Color.WHITE);

        lastMove = null;
    }

    public boolean movePiece(Move move, Piece.Color currentPlayer) {
        if (!Rules.isLegalMove(this, move, currentPlayer)) {
            return false;
        }

        Piece movedPiece = board[move.fromY][move.fromX];
        boolean enPassant = Rules.isEnPassantCapture(this, move);

        // Regular move or normal capture.
        board[move.toY][move.toX] = movedPiece;
        board[move.fromY][move.fromX] = null;

        // En passant captures the pawn beside the destination square.
        if (enPassant) {
            board[move.fromY][move.toX] = null;
        }

        // Automatically promote a pawn to a queen.
        if (movedPiece.type == Piece.Type.PAWN
                && (move.toY == 0 || move.toY == 7)) {
            board[move.toY][move.toX] =
                    new Piece(Piece.Type.QUEEN, movedPiece.color);
        }

        lastMove = move;
        return true;
    }
}