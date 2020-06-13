/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.board;

public abstract class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}
	
	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMoves(Position position) {
			return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] possibleMove = possibleMoves();
			
		for (int row = 0; row < possibleMove.length; row++) {
			for (int column = 0; column < possibleMove.length; column++) {
				if (possibleMove[row][column]) {
					return true;
				}
			}
		}
		return false;
	}
}
