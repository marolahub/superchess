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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

	private int turn;
	private Color currentPlayerColor;
	private Board board;

	private Boolean check;

	private List<Piece> piecesOnTheBoard;
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		// nesta classe - principal - defini-se tamanho do tabuleiro
		board = new Board(8, 8);
		turn = 1;
		piecesOnTheBoard = new ArrayList<>();
		currentPlayerColor = Color.YELLOW;
		check = false;
		initialSetup();
	}

	public int getTurn() {
		return this.turn;
	}

	public Color getCurrentPlayerColor() {
		return this.currentPlayerColor;
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

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		// valida as posições da peça no local de origem
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	// recebe como parâmetros a posição de origem e a posição de destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
			Position source = sourcePosition.toPosition();
			Position target = targetPosition.toPosition();
			// valida a posição de origem
			validateSourcePosition(source);
			// valida a posição de destino
			validateTargetPosition(source, target);
			// executa o movimento
			Piece capturePiece = makeMove(source, target);
			// testa se o movimento executado não colocou o próprio jogador em check
			if (testIsCheck(currentPlayerColor)) {
				undoMove(source, target, capturePiece);
				throw new ChessException(CustomMessages.YOURSELF_CHECK);
			}
			// testa também se o oponente ficou em check com esta jogada executada - atualizando a property CHECK
			check = (testIsCheck(opponent(currentPlayerColor))?true:false);
			// troca de jogadores
			nextTurn();
			return (ChessPiece) capturePiece;		
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
				throw new ChessException(CustomMessages.THERES_NO_PIECE_POSITION);
		}
		// feito downcast de Piece para ChessPiece para ter acesso ao método getColor
		if (currentPlayerColor != ((ChessPiece)board.piece(position)).getColor()) {
				throw new ChessException(CustomMessages.PEACE_NOT_BELONG);
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

			if(capturedPiece != null) {
				// se a posição de destino possui alguma peça adversária e remove do tabuleiro
				piecesOnTheBoard.remove(capturedPiece);
				// adiciona na lista de peças capturadas.
				capturedPieces.add(capturedPiece);
			}

			return capturedPiece;			
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		// a peça que chegou na posição de destino é removida do Board
		Piece piece = board.removePiece(target);
		// devolve a peça à sua posição de origem
		board.placePiece(piece, source);

		// se esta peça havia sido capturada, devolve a peça ao tabuleiro removendo da lista de peças capturadas
		if (capturedPieces != null) {
			// estorna a pela capturada à posição de destino, isto é, onde a peça estava anteriormente
			board.placePiece(capturedPiece, target);
			// remove a peça da lista de peças capturadas
			capturedPieces.remove(capturedPiece);
			// adiciona novamente no tabuleiro
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece chessPiece) {
		this.board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
		// adiciona peças no tabuleiro
		piecesOnTheBoard.add(chessPiece);
	}

	private void nextTurn() {
		turn++;
		currentPlayerColor = (currentPlayerColor == Color.YELLOW ? Color.RED : Color.YELLOW);
	}

	private Color opponent(Color oponentColor) {
		return (oponentColor == Color.RED)? Color.YELLOW: Color.RED;
	}

	private ChessPiece kingPiece(Color color) {
		// faz uma busca (filter) dentro da lista que está no tabuleiro, Lambda: x recebe a cor que está no método caso seja igual a cor do argumento
		List<Piece> listPiece = piecesOnTheBoard.stream().filter(xPiece -> ((ChessPiece)xPiece).getColor() == color).collect(Collectors.toList());
		for (Piece piece: listPiece) {
			// instanceof é um método que verifica se a classe instanciada é igual a comparativa
			if (piece instanceof King) {
				return ((ChessPiece) piece);
			}
		}
		throw new IllegalStateException(CustomMessages.THERE_IS_NO_KING(color));
	}

	private boolean testIsCheck(Color color) {
		// obtém a posição do rei
		Position kingPosition = kingPiece(color).getChessPosition().toPosition();
		// obtém todas as posições da cor adversária (fazendo filtro com lambda)
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		// varre a lista de posições possíveis do opositor verificando se existe nela a posição (row - column) do rei
		for (Piece piece: opponentPieces) {
			boolean[][] possibilitiesOpponentPiece = piece.possibleMoves();
			// verifica se a posição do rei está em alguma posição da lista
			if (possibilitiesOpponentPiece[kingPosition.getRow()][kingPosition.getColumn()]) {
				// se houver, retorna que está em Check
				return true;
			}
		}
		// caso fique fora do laço retorna que não está em Check
		return false;
	}

	public Boolean getCheck() {
		return check;
	}

	// método setup inicial da partida (repõe as peças sobre o tabuleiro colocando cada uma no seu local inicial)
	private void initialSetup() {
		
		placeNewPiece('a', 1, new Rook(board, Color.YELLOW));
	//	placeNewPiece('b', 1, new Knight(board, Color.YELLOW));
	//	placeNewPiece('c', 1, new Bishop(board, Color.YELLOW));
	//	placeNewPiece('d', 1, new Queen(board, Color.YELLOW));
		placeNewPiece('e', 1, new King(board, Color.YELLOW));
	//	placeNewPiece('f', 1, new Bishop(board, Color.YELLOW));
	//	placeNewPiece('g', 1, new Knight(board, Color.YELLOW));
		placeNewPiece('h', 1, new Rook(board, Color.YELLOW));
	/*	placeNewPiece('a', 2, new Pawn(board, Color.YELLOW));
		placeNewPiece('b', 2, new Pawn(board, Color.YELLOW));
		placeNewPiece('c', 2, new Pawn(board, Color.YELLOW));
		placeNewPiece('d', 2, new Pawn(board, Color.YELLOW));
		placeNewPiece('e', 2, new Pawn(board, Color.YELLOW));
		placeNewPiece('f', 2, new Pawn(board, Color.YELLOW));
		placeNewPiece('g', 2, new Pawn(board, Color.YELLOW));
		placeNewPiece('h', 2, new Pawn(board, Color.YELLOW)); */

		placeNewPiece('a', 8, new Rook(board, Color.RED));
	/*	placeNewPiece('b', 8, new Knight(board, Color.RED));
		placeNewPiece('c', 8, new Bishop(board, Color.RED));
		placeNewPiece('d', 8, new Queen(board, Color.RED));*/
		placeNewPiece('e', 8, new King(board, Color.RED));
		/* placeNewPiece('f', 8, new Bishop(board, Color.RED));
		placeNewPiece('g', 8, new Knight(board, Color.RED)); */
		placeNewPiece('h', 8, new Rook(board, Color.RED));
		/* placeNewPiece('a', 7, new Pawn(board, Color.RED));
		placeNewPiece('b', 7, new Pawn(board, Color.RED));
		placeNewPiece('c', 7, new Pawn(board, Color.RED));
		placeNewPiece('d', 7, new Pawn(board, Color.RED));
		placeNewPiece('e', 7, new Pawn(board, Color.RED));
		placeNewPiece('f', 7, new Pawn(board, Color.RED));
		placeNewPiece('g', 7, new Pawn(board, Color.RED));
		placeNewPiece('h', 7, new Pawn(board, Color.RED));
		*/
	}
	
}
