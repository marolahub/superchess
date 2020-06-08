/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.chess.ChessMatch;
import application.chess.ChessPiece;
import application.chess.ChessPosition;
import java.util.Scanner;

public class Program {
  //   private Scanner input	= new Scanner(System.in);
		 
	public static void main(String[] args) {	
		
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
			// UI = using interface
			UI.printBoard(chessMatch.getPieces());
			System.out.println("");
			/*
			// usuário digita a posição de origem
			System.out.println("Selecione a peça:");
			ChessPosition source = UI.readChessPosition(input);
			System.out.println("");
		     // usuário digita a posição de destino
			System.out.println("Em qual posição?");
			ChessPosition target = UI.readChessPosition(input);
			*/
			ChessPosition source = playingChess("Selecione a peça!",  new Scanner(System.in));
			ChessPosition target = playingChess("Qual a posição?",  new Scanner(System.in));
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
	}
	
	private static ChessPosition playingChess(String msg, Scanner in) {
			System.out.println("");
			System.out.println(msg);
			return UI.readChessPosition(in);
	}
	
}
