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
		Position positionStart = new Position(0, 0);
		// move para CIMA
		moves = moveRow(moves, positionStart, true);
		// move para BAIXO
		moves = moveRow(moves, positionStart, false);
		// move para ESQUERDA
		moves = moveColumn(moves, positionStart, true);
		// move para DIREITA
		moves = moveColumn(moves, positionStart, false);
		return moves;
	}
	
	private boolean[][] moveRow(boolean move[][], Position position, boolean above) {
			
		// para ACIMA = decrementa o número de linhas e para BAIXO incrementa	
		if (above == true)	{	
				position.setValues(position.getRow() - 1, position.getColumn());
		} else {
				position.setValues(position.getRow() + 1, position.getColumn());		
		}
		
				// positionExists verifica se a posição é válida e se não existe nenhuma peça nesta posição
		while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
						// ao move-se ele põe a peça nesta próxima posição
				move[position.getRow()][position.getColumn()] = true;
				if (above == true) { 
					position.setRow(position.getRow() - 1); 
				}
				else { 
					position.setRow(position.getRow() + 1); 
				}
		}
				// verifica se existe peça nesta posição e se é um oponente
		if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				move[position.getRow()][position.getColumn()] = true;
		}
		return move;
	}
	
	private boolean[][] moveColumn(boolean[][] move, Position position, boolean left) {
		// para ESQUERDA = decrementa o número de colunas e para DIREITA incrementa	
		if (left == true)	{	
			position.setValues(position.getRow(), position.getColumn() - 1);
		} else {
			position.setValues(position.getRow(), position.getColumn() + 1);		
		}
		
				// positionExists verifica se a posição é válida e se não existe nenhuma peça nesta posição
		while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
						// ao move-se ele põe a peça nesta próxima posição
				move[position.getRow()][position.getColumn()] = true;
				if (left == true) { 
					position.setColumn(position.getColumn()- 1); 
				}
				else { 
					position.setColumn(position.getColumn() + 1); 
				}
		}
				// verifica se existe peça nesta posição e se é um oponente
		if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				move[position.getRow()][position.getColumn()] = true;
		}
		return move;

	}
		
}
