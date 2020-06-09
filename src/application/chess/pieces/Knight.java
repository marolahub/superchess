/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess.pieces;

import application.board.Board;
import application.chess.ChessPiece;
import application.chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}
	@Override
	public String toString() {
		return "H";
	}	

	@Override
	public boolean[][] possibleMoves() {
	      	boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
			return moves;
	}
}
