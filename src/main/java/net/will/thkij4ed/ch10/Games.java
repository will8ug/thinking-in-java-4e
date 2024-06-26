/**
 * 
 */
package net.will.thkij4ed.ch10;

/**
 * Using anonymous inner classes with the Game framework. An
 * improved version of interfaces/Games.java.
 *
 * @author Will
 * @version 2012-9-4
 */
public class Games {

	public static void main(String[] args) {
		playGame(Checkers.factory);
		playGame(Chess.factory);
	}
	
	public static void playGame(GameFactory factory) {
		Game s = factory.getGame();
		while ( s.move() )
			;
	}

}

interface Game { boolean move(); }
interface GameFactory { Game getGame(); }

class Checkers implements Game {
	private Checkers() {}
	private int moves = 0;
	private static final int MOVES = 3;

	@Override
	public boolean move() {
		System.out.println("Checkers move " + moves);
		return ++moves != MOVES;
	}
	
	public static GameFactory factory = new GameFactory() {
		@Override
		public Game getGame() {
			return new Checkers();
		}
	};
	
}

class Chess implements Game {
	private Chess() {}
	private int moves = 0;
	private static final int MOVES = 4;

	@Override
	public boolean move() {
		System.out.println("Chess move " + moves);
		return ++moves != MOVES;
	}
	
	public static GameFactory factory = new GameFactory() {
		@Override
		public Game getGame() {
			return new Chess();
		}
	};
	
}
