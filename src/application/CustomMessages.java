/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.chess.Color;

/**
 *
 * @author maroli.junior
 */
public class CustomMessages {
		// mensagens
		public static final String INVALID_POSITION = "Posição inválida! Digite apenas valores válidos (A1 - H8)";
		public static final String THERES_NO_PIECE_POSITION =  "Não existe peça nesta posição!";
		public static final String BOARD_WITHOUT_POSITIONS = "Não é possível criar tabuleiro sem posições!";
		public static final String NON_EXISTENT_POSITIONS = "Esta posição não existe!";
		public static final String OCCUPIED_POSITION = "Já existe uma peça nesta posição!";
		public static final String CHOOSE_PIECE = "Escolha uma peça: ";
		public static final String CHOOSE_POSITION = "Escolha a posição: ";
		public static final String WITHOUT_MOVE = "Não há movimentos possíveis para esta peça!";
		public static final String CANNOT_MOVE_TARGET_POSITION = "Esta peça não pode mover-se à posição de destino!";
		public static final String PEACE_NOT_BELONG = "A peça escolhida não é sua!";
		public static final String WAITING_PLAYER = "Aguardando o jogador: ";
		public static final String CAPTURE_PIECES = "Peças capturadas: ";

		public static final String CHECK = "Check!";
		public static final String YOURSELF_CHECK = "Você colocou-se em CHECK! Movimento não possível";
		public static final String THERE_IS_NO_KING(Color kingColor) {
			return "Não existe rei " + kingColor + " no tabuleiro";
		}
		 // formatações
		public static final String BLANK = "";
		public static final String BLANK_SPACE = " ";
		public static final String TRACE = "-";
		public static final String CLEAR = "\033[H\033[2J";
		public static final String BOARD_BASE = "  a b c d e f g h";
		
}
