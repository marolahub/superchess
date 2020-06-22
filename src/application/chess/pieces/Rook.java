/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess.pieces;

import application.board.Board;
import application.board.Position;
import application. chess.ChessPiece;
import application.chess.Color;

// torre
public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		// apenas adiciona a chamada para a superclasse
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position position = new Position(0, 0);

		// above
		position.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
			matMoves[position.getRow()][position.getColumn()] = true;
			position.setRow(position.getRow() - 1);
		}
		if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
			matMoves[position.getRow()][position.getColumn()] = true;
		}

		// left
		position.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
			matMoves[position.getRow()][position.getColumn()] = true;
			position.setColumn(position.getColumn() - 1);
		}
		if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
			matMoves[position.getRow()][position.getColumn()] = true;
		}

		// right
		position.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
			matMoves[position.getRow()][position.getColumn()] = true;
			position.setColumn(position.getColumn() + 1);
		}
		if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
			matMoves[position.getRow()][position.getColumn()] = true;
		}

		// below
		position.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
			matMoves[position.getRow()][position.getColumn()] = true;
			position.setRow(position.getRow() + 1);
		}
		if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
			matMoves[position.getRow()][position.getColumn()] = true;
		}

		return matMoves;
	}
		
}
