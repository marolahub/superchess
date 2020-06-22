/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess;

import application.CustomMessages;
import application.board.Position;
/**
 *
 * @author maroli.junior
 */
public class ChessPosition {
			private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		
		if ((column < 'a' || column > 'h') && (row < 1 && row > 8)) {
			throw new ChessException(CustomMessages.INVALID_POSITION);
		}
		
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		// para retornar uma posição no tabuleiro
		// 8 que é o máximo diminuído pela linha
		// e caractere (a - h), com valor ascii, menos o caractere 'a' (ascii)
		return new Position(8 - row, column - 'a');
	}
	
	protected static ChessPosition fromPosition(Position position) {
		char column = (char)('a' + position.getColumn());
		int row = 8 + position.getRow();
		return new ChessPosition(column, row);
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
}
