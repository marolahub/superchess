package application.chess.pieces;

import application.board.Position;
import application.chess.ChessPiece;

public class PiecesMoves {

    public static void getMoves(ChessPiece chessPiece, Position position, boolean[][] moves, DirectionMoves directionMoves) {

        switch (directionMoves) {
            // acima
            case A: { position.setValues(position.getRow() - 1, position.getColumn()); }
            // abaixo
            case B: { position.setValues(position.getRow() + 1, position.getColumn()); }
            // esquerda
            case L: { position.setValues(position.getRow(), position.getColumn() - 1); }
            // direita
            case R: { position.setValues(position.getRow(), position.getColumn() + 1); }
            // nordeste
            case NE: { position.setValues(position.getRow() - 1, position.getColumn() + 1); }
            // noroeste
            case NW: { position.setValues(position.getRow() - 1, position.getColumn() - 1); }
            // suldeste
            case SE: { position.setValues(position.getRow() + 1, position.getColumn() + 1); }
            // suldoeste
            case SW: { position.setValues(position.getRow() + 1, position.getColumn() - 1); }
        }
        if (chessPiece.getBoard().positionExists(position) && chessPiece.canMove(position)) {
            moves[position.getRow()][position.getColumn()] = true;
        }
    }
}
