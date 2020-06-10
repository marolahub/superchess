/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess;

import application.CustomMessages;
import application.board.Board;
import application.board.Piece;
import application.board.Position;
import application.chess.pieces.Bishop;
import application.chess.pieces.King;
import application.chess.pieces.Knight;
import application.chess.pieces.Pawn;
import application.chess.pieces.Queen;
import application.chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		// nesta classe - principal - defini-se tamanho do tabuleiro
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] match = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// faz-se um downcast
				match[i][j] = (ChessPiece) board.piece(i, j);
			}
		}		
		return match;
	}	
	// recebe como parâmetros a posição de origem e a posição de destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
			Position source = sourcePosition.toPosition();
			Position target = targetPosition.toPosition();
			// valida a posição de origem
			validateSourcePosition(source);
			// valida a posição de destino
			validateTargetPosition(source, target);
			Piece capturePiece = makeMove(source, target);
			return (ChessPiece) capturePiece;		
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
				throw new ChessException(CustomMessages.THERES_NO_PIECE_POSITION);
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
				throw new ChessException(CustomMessages.WITHOUT_MOVE);
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		// testa os movimentos possíveis da peça da posição de origem em relação a posição de destino
		if  (!board.piece(source).possibleMoves(target)) {
				throw new ChessException(CustomMessages.CANNOT_MOVE_TARGET_POSITION);
		}
	}
	
	private Piece makeMove(Position source, Position target) {
			// peça que está chegando na posição
			Piece pieceSource = board.removePiece(source);
			// remove a possível peça que está na posição de destino (capturada)
			Piece capturedPiece = board.removePiece(target);
			// põe a peça que chegou na posição de destino
			board.placePiece(pieceSource, target);
			return capturedPiece;			
	}
	
	private void placeNewPiece(char column, int row, ChessPiece chessPiece) {
		this.board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
	}
	
	// método setup inicial da partida (repõe as peças sobre o tabuleiro colocando cada uma no seu local inicial)
	private void initialSetup() {
		
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
		
	}
	
}
