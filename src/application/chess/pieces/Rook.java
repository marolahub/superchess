/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess.pieces;

import application.board.Board;
import application. chess.ChessPiece;
import application.chess.Color;

// torre
public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		// apenas adiona a chamada para a superclasse
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}

		@Override
		public boolean[][] possibleMoves() {
				boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
				return moves;
		}
		
}
