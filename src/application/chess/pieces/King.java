/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess.pieces;

import application. board.Board;
import application.board.Position;
import application.chess.ChessPiece;
import application.chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position position = new Position(0,0);
		// acima (above)
		PiecesMoves.getMoves(this, position, moves, DirectionMoves.A);
		// esquerda (left)
		PiecesMoves.getMoves(this, position, moves, DirectionMoves.L);
		// abaixo (below)
		PiecesMoves.getMoves(this, position, moves, DirectionMoves.B);
		// direita (right)
		PiecesMoves.getMoves(this, position, moves, DirectionMoves.R);
		return moves;
	}
}
