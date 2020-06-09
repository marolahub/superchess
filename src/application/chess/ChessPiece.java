/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess;

import application.board.Board;
import application.board.Piece;

/**
 *
 * @author maroli.junior
 */
public abstract class ChessPiece extends Piece {
		
	private Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}		
}
