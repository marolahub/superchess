/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess;

import application.board.Board;
import application.board.Piece;
import application.board.Position;

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

	protected boolean isThereOpponentPiece(Position position) {
			ChessPiece chessPiece = (ChessPiece)getBoard().piece(position);
			// testa se a cor da peça é diferente da cor da peça que está na posição
			return (chessPiece != null) && (chessPiece.getColor() != color);
	}

	public boolean canMove(Position position) {
		ChessPiece chessPiece = (ChessPiece)getBoard().piece(position);
		return ((chessPiece == null) || (chessPiece.getColor() != getColor()));
	}
	
}
