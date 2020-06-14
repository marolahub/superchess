/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.chess.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// UI = using interface
public class UI {
	
	public static ChessPosition readChessPosition(Scanner scanner) {
			// trata de uma possível exceção de entrada de dados
			try {
				String input = scanner.nextLine();
				// obtém do input a coluna (índice zero)
				char column = input.charAt(0);
				// obtém do input a linha (índice um)
				int row = Integer.parseInt(input.substring(1));
				// define uma posição com os dados inputados
				return new ChessPosition(column, row);
			} catch (RuntimeException error) {
					throw new InputMismatchException(CustomMessages.INVALID_POSITION);
			}
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedChessPiece) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(capturedChessPiece);
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		System.out.println(CustomMessages.WAITING_PLAYER + chessMatch.getCurrentPlayerColor());
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int lines = 0; lines < pieces.length; lines++) {
			System.out.print((8 - lines) + CustomMessages.BLANK_SPACE);
			for (int column = 0; column < pieces.length; column++) {
				printPiece(pieces[lines][column], false);
			}
			// quebra de linha
			System.out.println();
		}
		System.out.println(CustomMessages.BOARD_BASE);
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int lines = 0; lines < pieces.length; lines++) {
			System.out.print((8 - lines) + CustomMessages.BLANK_SPACE);
			for (int column = 0; column < pieces.length; column++) {
				printPiece(pieces[lines][column], possibleMoves[lines][column]);
			}
			// quebra de linha
			System.out.println();
		}
		System.out.println(CustomMessages.BOARD_BASE);
	}
	// método para limpar a tela
	public static void clearScreen() {
			//  \033[H\033[2J
			System.out.print(CustomMessages.CLEAR);
			// 
			System.out.flush();
	}
	
	// impressão de uma única peça
	private static void printPiece(ChessPiece piece, boolean background) {
					
		if (background) {
			System.out.print(ConsoleColors.BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ConsoleColors.RESET);
		} else {	
			System.out.print((piece.getColor()==Color.YELLOW ?ConsoleColors.RED:ConsoleColors.YELLOW) + piece + ConsoleColors.RESET);
		}		
		// não alterar para não estragar a estrutura de alinhamento do tabuleiro
		System.out.print(" ");	
	}

	private static void printCapturedPieces(List<ChessPiece> capChessPieces) {
		                                                       // método lâmbda: filtra da lista dos que tem cor vermelha
		List<ChessPiece> red = capChessPieces.stream().filter(predicated -> predicated.getColor() == Color.YELLOW).collect(Collectors.toList());
															   // método lambda: fitra da lista dos que tem cor amarela
		List<ChessPiece> yellow = capChessPieces.stream().filter(predicated -> predicated.getColor() == Color.RED).collect(Collectors.toList());

		System.out.println(CustomMessages.CAPTURE_PIECES);

		System.out.print("Vermelhas: ");
		System.out.println(Arrays.toString(red.toArray()));
		System.out.println(ConsoleColors.RESET);

		System.out.print("Amarelas: ");
		System.out.println(Arrays.toString(yellow.toArray()));
		System.out.println(ConsoleColors.RESET);

	}
}
