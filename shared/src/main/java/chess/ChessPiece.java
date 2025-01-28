package chess;

import java.util.Collection;
import java.util.ArrayList;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    PieceType type;
    ChessGame.TeamColor pieceColor;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.type = type;
        this.pieceColor = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    public void addValidMove(ArrayList<ChessMove> validMoves, ChessPosition startPosition, ChessPosition newPosition,
                             PieceType[] promotionPiece) {
        if (promotionPiece != null) {
            for (PieceType pieceType : promotionPiece) {
                validMoves.add(new ChessMove(startPosition, newPosition, pieceType));
            }
        } else {
            validMoves.add(new ChessMove(startPosition, newPosition, null));
        }
    }

    public ArrayList<ChessMove> kingCalculator(ChessBoard board, ChessPosition myPosition) {
        ChessPiece king = board.getPiece(myPosition);
        ChessPiece boardSpace = board.getPiece(myPosition);
        ChessPosition newPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        boolean up = row != 8;
        boolean down = row != 1;
        boolean left = col != 1;
        boolean right = col != 8;

        if (up) {
            newPosition = new ChessPosition(row + 1, col);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, null);
            } else if (boardSpace.getTeamColor() != king.getTeamColor()) {
                addValidMove(validMoves, myPosition, newPosition, null);
            }
        }
        if (down) {
            newPosition = new ChessPosition(row - 1, col);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, null);
            } else if (boardSpace.getTeamColor() != king.getTeamColor()) {
                addValidMove(validMoves, myPosition, newPosition, null);
            }
        }
        if (left) {
            newPosition = new ChessPosition(row, col - 1);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, null);
            } else if (boardSpace.getTeamColor() != king.getTeamColor()) {
                addValidMove(validMoves, myPosition, newPosition, null);
            }
        }
        if (right) {

            newPosition = new ChessPosition(row, col + 1);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, null);
            } else if (boardSpace.getTeamColor() != king.getTeamColor()) {
                addValidMove(validMoves, myPosition, newPosition, null);
            }

        }
        if (up && right) {
            newPosition = new ChessPosition(row + 1, col + 1);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, null);
            } else if (boardSpace.getTeamColor() != king.getTeamColor()) {
                addValidMove(validMoves, myPosition, newPosition, null);
            }
        }
        if (up && left) {
            newPosition = new ChessPosition(row + 1, col - 1);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, null);
            } else if (boardSpace.getTeamColor() != king.getTeamColor()) {
                addValidMove(validMoves, myPosition, newPosition, null);
            }
        }
        if (down && right) {
            newPosition = new ChessPosition(row - 1, col + 1);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, null);
            } else if (boardSpace.getTeamColor() != king.getTeamColor()) {
                addValidMove(validMoves, myPosition, newPosition, null);
            }
        }
        if (down && left) {
            newPosition = new ChessPosition(row - 1, col - 1);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, null);
            } else if (boardSpace.getTeamColor() != king.getTeamColor()) {
                addValidMove(validMoves, myPosition, newPosition, null);
            }
        }
        return validMoves;
    }

    public ArrayList<ChessMove> whitePawnCalculator(ChessBoard board, ChessPosition myPosition) {
        ChessPiece pawn = board.getPiece(myPosition);
        ChessPiece boardSpace = board.getPiece(myPosition);
        ChessPosition newPosition;
        ChessPiece.PieceType[] promotionPiece;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        if (row + 1 == 8) {
            promotionPiece = new PieceType[4];
            promotionPiece[0] = PieceType.QUEEN;
            promotionPiece[1] = PieceType.KNIGHT;
            promotionPiece[2] = PieceType.ROOK;
            promotionPiece[3] = PieceType.BISHOP;
        } else {
            promotionPiece = null;
        }
        if (row != 8) {
            newPosition = new ChessPosition(row + 1, col);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, promotionPiece);
                if (row == 2) {
                    newPosition = new ChessPosition(row + 2, col);
                    boardSpace = board.getPiece(newPosition);
                    if (boardSpace == null) {
                        addValidMove(validMoves, myPosition, newPosition, promotionPiece);
                    }
                }
            }
            if (col != 1) {

                newPosition = new ChessPosition(row + 1, col - 1);
                boardSpace = board.getPiece(newPosition);
                if (boardSpace != null) {
                    if (boardSpace.getTeamColor() != pawn.getTeamColor()) {
                        addValidMove(validMoves, myPosition, newPosition, promotionPiece);
                    }
                }

            }
            if (col != 8) {
                newPosition = new ChessPosition(row + 1, col + 1);
                boardSpace = board.getPiece(newPosition);
                if (boardSpace != null) {
                    if (boardSpace.getTeamColor() != pawn.getTeamColor()) {
                        addValidMove(validMoves, myPosition, newPosition, promotionPiece);
                    }
                }
            }
        }
        return validMoves;
    }

    public ArrayList<ChessMove> blackPawnCalculator(ChessBoard board, ChessPosition myPosition) {
        ChessPiece pawn = board.getPiece(myPosition);
        ChessPiece boardSpace = board.getPiece(myPosition);
        ChessPosition newPosition;
        ChessPiece.PieceType[] promotionPiece;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        if (row - 1 == 1) {
            promotionPiece = new PieceType[4];
            promotionPiece[0] = PieceType.QUEEN;
            promotionPiece[1] = PieceType.KNIGHT;
            promotionPiece[2] = PieceType.ROOK;
            promotionPiece[3] = PieceType.BISHOP;
        } else {
            promotionPiece = null;
        }
        if (row != 1) {
            newPosition = new ChessPosition(row - 1, col);
            boardSpace = board.getPiece(newPosition);
            if (boardSpace == null) {
                addValidMove(validMoves, myPosition, newPosition, promotionPiece);
                if (row == 7) {
                    newPosition = new ChessPosition(row - 2, col);
                    boardSpace = board.getPiece(newPosition);
                    if (boardSpace == null) {
                        addValidMove(validMoves, myPosition, newPosition, promotionPiece);
                    }
                }
            }
            if (col != 1) {

                newPosition = new ChessPosition(row - 1, col - 1);
                boardSpace = board.getPiece(newPosition);
                if (boardSpace != null) {
                    if (boardSpace.getTeamColor() != pawn.getTeamColor()) {
                        addValidMove(validMoves, myPosition, newPosition, promotionPiece);
                    }
                }

            }
            if (col != 8) {
                newPosition = new ChessPosition(row - 1, col + 1);
                boardSpace = board.getPiece(newPosition);
                if (boardSpace != null) {
                    if (boardSpace.getTeamColor() != pawn.getTeamColor()) {
                        addValidMove(validMoves, myPosition, newPosition, promotionPiece);
                    }
                }
            }
        }
        return validMoves;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece myPiece = board.getPiece(myPosition);
        if (myPiece.getPieceType() ==  PieceType.KING){
            return kingCalculator(board, myPosition);
        }
        if (myPiece.getPieceType() == PieceType.PAWN && myPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
            return whitePawnCalculator(board, myPosition);
        }
        if (myPiece.getPieceType() == PieceType.PAWN && myPiece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            return blackPawnCalculator(board, myPosition);
        }
        throw new RuntimeException("Not implemented");
    }
}
