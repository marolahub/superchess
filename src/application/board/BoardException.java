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
public class BoardException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BoardException(String msg) {
		super(msg);
	}
}
