package main.java.openchess.ui;

import javax.swing.*;
import java.awt.*;

import main.java.openchess.Game;
import main.java.openchess.Move;
import main.java.openchess.Piece;
import main.java.openchess.ChessAI;

public class UserInterface {

    private static final PieceImageLoader pieceImageLoader = new PieceImageLoader();
    private static final Game game = new Game();
    private static final ChessAI chessAI = new ChessAI();

    private static final JButton[][] buttons = new JButton[8][8];

    private static int selectedX = -1;
    private static int selectedY = -1;

    public static void main(String[] args) {
        pieceImageLoader.loadPieceImages();
        SwingUtilities.invokeLater(UserInterface::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("OpenChess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 8));

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(100, 100));

                if ((x + y) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.GRAY);
                }

                buttons[y][x] = button;

                final int buttonX = x;
                final int buttonY = y;

                button.addActionListener(e ->
                    handleSquareClick(buttonX, buttonY)
                );

                panel.add(button);
            }
        }

        updateBoard();

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void handleSquareClick(int x, int y) {
        if (selectedX == -1) {
            // Erster Klick: Nur ein Feld mit Figur auswählen.
            Piece piece = game.board.board[y][x];
            boolean isCurrentPlayersPiece = piece != null
                    && (game.whiteToMove == (piece.color == Piece.Color.WHITE));
            if (isCurrentPlayersPiece) {
                selectedX = x;
                selectedY = y;
            }
            return;
        }

        // Ein zweiter Klick auf dieselbe Figur hebt die Auswahl auf.
        if (selectedX == x && selectedY == y) {
            selectedX = -1;
            selectedY = -1;
            return;
        }

        // Zweiter Klick: Den Zug ausführen und Schwarz antworten lassen.
        Move move = new Move(selectedX, selectedY, x, y);
        boolean humanMoved = game.makeMove(move);

        if (humanMoved && !game.whiteToMove) {
            Move computerMove = chessAI.findRandomMove(game.board, Piece.Color.BLACK);
            if (computerMove != null) {
                game.makeMove(computerMove);
            }
        }

        selectedX = -1;
        selectedY = -1;

        updateBoard();
    }

    private static void updateBoard() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Piece piece = game.board.board[y][x];
                int index = PieceImageLoader.getPieceIndex(piece);

                if (index == -1) {
                    buttons[y][x].setIcon(null);
                } else {
                    buttons[y][x].setIcon(
                        new ImageIcon(pieceImageLoader.pieceImages[index])
                    );
                }
            }
        }
    }
}
