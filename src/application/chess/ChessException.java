/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.chess;

import application.board.BoardException;

/**
 * * @author maroli.junior
 */
public class ChessException extends BoardException {
		
	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {
			super(msg);
	}	
}
