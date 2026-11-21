package main.java.openchess;

/** Validates chess piece movement rules. */
public final class Rules {

    private Rules() {
    }

    public static boolean isValidMove(Board board, Move move) {
        if (!isOnBoard(move.fromX, move.fromY)
                || !isOnBoard(move.toX, move.toY)) {
            return false;
        }

        Piece piece = board.board[move.fromY][move.fromX];
        Piece target = board.board[move.toY][move.toX];

        if (piece == null || (target != null && target.color == piece.color)) {
            return false;
        }

        int deltaX = move.toX - move.fromX;
        int deltaY = move.toY - move.fromY;

        return switch (piece.type) {
            case ROOK -> (deltaX == 0 || deltaY == 0)
                    && isPathClear(board, move, deltaX, deltaY);

            case BISHOP -> Math.abs(deltaX) == Math.abs(deltaY)
                    && isPathClear(board, move, deltaX, deltaY);

            case QUEEN -> (deltaX == 0 || deltaY == 0
                    || Math.abs(deltaX) == Math.abs(deltaY))
                    && isPathClear(board, move, deltaX, deltaY);

            case KNIGHT -> (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1)
                    || (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2);

            case KING -> Math.max(Math.abs(deltaX), Math.abs(deltaY)) == 1;

            case PAWN -> isValidPawnMove(board, move, piece, target, deltaX, deltaY);
        };
    }

    public static boolean isLegalMove(Board board, Move move, Piece.Color color) {
        if (!isValidMove(board, move)) {
            return false;
        }

        Piece piece = board.board[move.fromY][move.fromX];

        return piece.color == color
                && !wouldLeaveKingInCheck(board, move, color);
    }

    public static boolean isEnPassantCapture(Board board, Move move) {
        Piece piece = board.board[move.fromY][move.fromX];

        if (piece == null || piece.type != Piece.Type.PAWN) {
            return false;
        }

        int direction = piece.color == Piece.Color.WHITE ? -1 : 1;
        Move lastMove = board.getLastMove();

        // The pawn moves diagonally into an empty square.
        if (Math.abs(move.toX - move.fromX) != 1
                || move.toY - move.fromY != direction
                || board.board[move.toY][move.toX] != null
                || lastMove == null) {
            return false;
        }

        // The adjacent piece must be an opposing pawn.
        Piece adjacentPawn = board.board[move.fromY][move.toX];
        if (adjacentPawn == null
                || adjacentPawn.type != Piece.Type.PAWN
                || adjacentPawn.color == piece.color) {
            return false;
        }

        // That pawn must have just advanced two squares.
        return lastMove.toX == move.toX
                && lastMove.toY == move.fromY
                && lastMove.fromY == move.fromY + (2 * direction);
    }

    public static boolean isInCheck(Board board, Piece.Color color) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Piece piece = board.board[y][x];

                if (piece != null
                        && piece.type == Piece.Type.KING
                        && piece.color == color) {
                    return isSquareAttacked(board, x, y, opposite(color));
                }
            }
        }

        return true;
    }

    public static boolean isCheckmate(Board board, Piece.Color color) {
        return isInCheck(board, color) && !hasAnyLegalMove(board, color);
    }

    public static boolean isStalemate(Board board, Piece.Color color) {
        return !isInCheck(board, color) && !hasAnyLegalMove(board, color);
    }

    private static boolean wouldLeaveKingInCheck(
            Board board, Move move, Piece.Color color) {

        Board simulatedBoard = new Board(board);

        simulatedBoard.board[move.toY][move.toX] =
                simulatedBoard.board[move.fromY][move.fromX];
        simulatedBoard.board[move.fromY][move.fromX] = null;

        // En passant captures the pawn beside the destination square.
        if (isEnPassantCapture(board, move)) {
            simulatedBoard.board[move.fromY][move.toX] = null;
        }

        return isInCheck(simulatedBoard, color);
    }

    private static boolean hasAnyLegalMove(Board board, Piece.Color color) {
        for (int fromY = 0; fromY < 8; fromY++) {
            for (int fromX = 0; fromX < 8; fromX++) {
                Piece piece = board.board[fromY][fromX];

                if (piece == null || piece.color != color) {
                    continue;
                }

                for (int toY = 0; toY < 8; toY++) {
                    for (int toX = 0; toX < 8; toX++) {
                        if (isLegalMove(board,
                                new Move(fromX, fromY, toX, toY), color)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static boolean isSquareAttacked(
            Board board, int targetX, int targetY, Piece.Color attacker) {

        for (int fromY = 0; fromY < 8; fromY++) {
            for (int fromX = 0; fromX < 8; fromX++) {
                Piece piece = board.board[fromY][fromX];

                if (piece != null
                        && piece.color == attacker
                        && attacksSquare(board, piece,
                        fromX, fromY, targetX, targetY)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean attacksSquare(
            Board board, Piece piece,
            int fromX, int fromY, int targetX, int targetY) {

        int deltaX = targetX - fromX;
        int deltaY = targetY - fromY;
        Move attack = new Move(fromX, fromY, targetX, targetY);

        return switch (piece.type) {
            case PAWN -> Math.abs(deltaX) == 1
                    && deltaY == (piece.color == Piece.Color.WHITE ? -1 : 1);

            case KNIGHT -> (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1)
                    || (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2);

            case BISHOP -> Math.abs(deltaX) == Math.abs(deltaY)
                    && isPathClear(board, attack, deltaX, deltaY);

            case ROOK -> (deltaX == 0 || deltaY == 0)
                    && isPathClear(board, attack, deltaX, deltaY);

            case QUEEN -> (deltaX == 0 || deltaY == 0
                    || Math.abs(deltaX) == Math.abs(deltaY))
                    && isPathClear(board, attack, deltaX, deltaY);

            case KING -> Math.max(Math.abs(deltaX), Math.abs(deltaY)) == 1;
        };
    }

    private static boolean isValidPawnMove(
            Board board, Move move, Piece piece, Piece target,
            int deltaX, int deltaY) {

        int direction = piece.color == Piece.Color.WHITE ? -1 : 1;
        int startRow = piece.color == Piece.Color.WHITE ? 6 : 1;

        if (deltaX == 0 && target == null) {
            if (deltaY == direction) {
                return true;
            }

            return move.fromY == startRow
                    && deltaY == 2 * direction
                    && board.board[move.fromY + direction][move.fromX] == null;
        }

        return Math.abs(deltaX) == 1
                && deltaY == direction
                && (target != null || isEnPassantCapture(board, move));
    }

    private static boolean isPathClear(
            Board board, Move move, int deltaX, int deltaY) {

        int stepX = Integer.compare(deltaX, 0);
        int stepY = Integer.compare(deltaY, 0);
        int x = move.fromX + stepX;
        int y = move.fromY + stepY;

        while (x != move.toX || y != move.toY) {
            if (board.board[y][x] != null) {
                return false;
            }

            x += stepX;
            y += stepY;
        }

        return true;
    }

    private static Piece.Color opposite(Piece.Color color) {
        return color == Piece.Color.WHITE
                ? Piece.Color.BLACK
                : Piece.Color.WHITE;
    }

    private static boolean isOnBoard(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}