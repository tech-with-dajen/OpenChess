package main.java.openchess;
from main.java.openchess.Piece import Piece;
public class Board {
    public static void main(String[] args) {
        // Backend representation of the chess board using a 2D array
        // Each number represents a piece, with negative numbers for black pieces and positive numbers for white pieces
        // The pieces are represented as follows:
        // 1: Pawn, 7: Knight, 6: Bishop, 8: Rook, 5: Queen, 4: King
        int[][] Board = {{-8, -7, -6, -5, -4, -6,-7,-8},
                         {-1 ,-1, -1, -1, -1, -1,-1,-1},
                         { 0,  0,  0,  0,  0,  0 ,0,0},
                         { 0,  0,  0,  0,  0,  0 ,0,0},
                         { 0,  0,  0,  0,  0,  0 ,0,0},
                         { 0,  0,  0,  0,  0,  0 ,0,0},
                         { 1,  1,  1,  1,  1,  1, 1,1},
                         { 8,  7,  6,  5,  4,  6, 7,8}};
                
    }
}
