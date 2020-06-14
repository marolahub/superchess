/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.chess.ChessException;
import application.chess.ChessMatch;
import application.chess.ChessPiece;
import application.chess.ChessPosition;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

		 
	public static void main(String[] args) {	
		
		Scanner input	= new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
			// UI = using interface
			try {

				UI.clearScreen();
				UI.printMatch(chessMatch);
				System.out.println();		
				/*  usuário digita a posição de origem e  usuário digita a posição de destino 	*/
				ChessPosition source = playingChess(CustomMessages.CHOOSE_PIECE,  input);

				boolean [][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				ChessPosition target = playingChess(CustomMessages.CHOOSE_POSITION,  input);
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			} catch (ChessException e) { 
				System.out.println(e.getMessage());
				input.nextLine();				
			} catch (InputMismatchException e) { 
				System.out.println(e.getMessage());
				input.nextLine();				
			}
			System.out.println();
		}
	}
	
	private static ChessPosition playingChess(String msg, Scanner in) {
			System.out.print(CustomMessages.SPACE);
			System.out.print(msg);
			return UI.readChessPosition(in);
	}
	
}
