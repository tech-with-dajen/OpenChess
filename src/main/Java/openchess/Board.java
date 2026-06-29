<<<<<<< HEAD
=======
package main.java.openchess;

public class Board{ 
        Piece[][] board = new Piece[8][8];

        public  Board() {
            resetBoard();
        }

        private void resetBoard() {
        //Board setup
        
        board[0][0] = new Piece(Piece.Type.ROOK, Piece.Color.BLACK);
        board[0][1] = new Piece(Piece.Type.KNIGHT, Piece.Color.BLACK);
        board[0][2] = new Piece(Piece.Type.BISHOP, Piece.Color.BLACK);
        board[0][3] = new Piece(Piece.Type.QUEEN, Piece.Color.BLACK);
        board[0][4] = new Piece(Piece.Type.KING, Piece.Color.BLACK);
        board[0][5] = new Piece(Piece.Type.BISHOP, Piece.Color.BLACK);
        board[0][6] = new Piece(Piece.Type.KNIGHT, Piece.Color.BLACK);
        board[0][7] = new Piece(Piece.Type.ROOK, Piece.Color.BLACK);
        board[1][0] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        board[1][1] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        board[1][2] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        board[1][3] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        board[1][4] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        board[1][5] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        board[1][6] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        board[1][7] = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null; // Empty squares
            }
        }
        board[6][0] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        board[6][1] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        board[6][2] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        board[6][3] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        board[6][4] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        board[6][5] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        board[6][6] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        board[6][7] = new Piece(Piece.Type.PAWN, Piece.Color.WHITE);
        board[7][0] = new Piece(Piece.Type.ROOK, Piece.Color.WHITE);
        board[7][1] = new Piece(Piece.Type.KNIGHT, Piece.Color.WHITE);
        board[7][2] = new Piece(Piece.Type.BISHOP, Piece.Color.WHITE);
        board[7][3] = new Piece(Piece.Type.QUEEN, Piece.Color.WHITE);
        board[7][4] = new Piece(Piece.Type.KING, Piece.Color.WHITE);
        board[7][5] = new Piece(Piece.Type.BISHOP, Piece.Color.WHITE);
        board[7][6] = new Piece(Piece.Type.KNIGHT, Piece.Color.WHITE);
        board[7][7] = new Piece(Piece.Type.ROOK, Piece.Color.WHITE);
        System.out.println("Initial board setup:");
    }}
>>>>>>> parent of c48325e (Remove wrong Java folder (case fix))
