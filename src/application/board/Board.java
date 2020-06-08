/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.board;

/**
 *
 * @author maroli.junior
 */
public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Não é possível criar tabuleiro sem posições!");
		}
		
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		// caso a posição passada não exista
		if(!positionExists(row, column)) {
			throw new BoardException("Esta posição não existe");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if(!positionExists(position.getRow(), position.getColumn())) {
			throw new BoardException("Esta posição não existe");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Já existe uma peça nesta posição" + position);
		}
		
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	private boolean positionExists(int row, int column) {
		// retorna verdadeiro caso o a linha não seja menor do zero e nem maior que o nº de linhas
		// e se a coluna não for menor que zero e nem maior que o nº de colunas do tabuleiro
		return (row >= 0 && row < rows) && (column >= 0 && column < columns);
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	// aqui há uma peça
	public boolean thereIsAPiece(Position position) {
		// verifica se existe ou não algo nesta posição
		if (!positionExists(position)) {
			throw new BoardException("Esta posição não existe");
		}
		return (piece(position) != null);
	}
	
	public Piece removePiece(Position position) {
			if(!positionExists(position)) {
					throw new BoardException("Posição inexistente!");
			}
			if(piece(position) == null) {
					return null;
			}
			Piece pieceAux  = piece(position);
			pieceAux.position = null;
			// atribui nulo à matriz de peças, isto é, o tabuleiro
			pieces[position.getRow()][position.getColumn()] = null;
			return pieceAux;					
	}
		
}
