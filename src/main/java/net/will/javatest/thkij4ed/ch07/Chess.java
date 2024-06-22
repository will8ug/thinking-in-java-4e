/**
 * 
 */
package net.will.javatest.thkij4ed.ch07;

import java.util.logging.Logger;

/**
 * @author Will
 * @version 2011-9-12
 */
public class Chess extends BoardGame {
	private Logger logger = Logger.getLogger(getClass().getName());
	Chess() {
		super(11);
//		System.out.println("Chess constructor");
		logger.info("empty");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Chess();
	}

}

class BoardGame extends Game {
	private Logger logger = Logger.getLogger(getClass().getName());
	BoardGame(int i) {
		super(i);
//		System.out.println("BoardGame constructor");
		logger.info(i+"");
	}
}

class Game {
	private Logger logger = Logger.getLogger(getClass().getName());
	Game(int i) {
//		System.out.println("Game constructor");
		logger.info(i+"");
	}
}