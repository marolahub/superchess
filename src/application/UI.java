/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.chess.ChessPiece;
import application.chess.ChessPosition;
import application.chess.Color;
import java.util.InputMismatchException;
import java.util.Scanner;

// UI = using interface
public class UI {
		
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
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
					throw new InputMismatchException("Posição inválida! Digite apenas valores válidos (A1 - H8)");
			}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int lines = 0; lines < pieces.length; lines++) {
			System.out.print((8 - lines) + " ");
			for (int column = 0; column < pieces.length; column++) {
				printPiece(pieces[lines][column], false);
			}
			// quebra de linha
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	// impressão de uma única peça
	private static void printPiece(ChessPiece piece, boolean background) {
			
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {	
				System.out.print((piece.getColor()==Color.WHITE?ANSI_WHITE:ANSI_YELLOW) + piece + ANSI_RESET);
		}				
		System.out.print(" ");	
	}
}
