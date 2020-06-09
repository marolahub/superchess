/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author maroli.junior
 */
public class CustomMessages {
		// mensagens
		public static final String  INVALID_POSITION = "Posição inválida! Digite apenas valores válidos (A1 - H8)";
		public static final String THERES_NO_PIECE_POSITION =  "Não existe peça nesta posição!";
		public static final String BOARD_WITHOUT_POSITIONS = "Não é possível criar tabuleiro sem posições!";
		public static final String NON_EXISTENT_POSITIONS = "Esta posição não existe!";
		public static final String OCCUPIED_POSITION = "Já existe uma peça nesta posição!";
		public static final String CHOOSE_PIECE = "Escolha uma peça: ";
		public static final String CHOOSE_POSITION = "Escolha a posição: ";
		public static final String WITHOUT_MOVE = "Não há movimentos possíveis para esta peça!";
		 
		 // formatações
		public static final String SPACE = "";
		public static final String BLANK_SPACE = " ";
		public static final String TRACE = "-";
		public static final String CLEAR = "\033[H\033[2J";
		public static final String BOARD_BASE = "  a b c d e f g h";
		
}
