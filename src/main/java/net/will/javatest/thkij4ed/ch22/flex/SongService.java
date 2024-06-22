/**
 * @(#)SongService.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.flex;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Will
 * @version 2012-4-19
 */
public class SongService {
	private List<Song> songs = new ArrayList<Song>();

	public SongService() {
		fillTestData();
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void addSong(Song song) {
		songs.add(song);
	}

	public void removeSong(Song song) {
		songs.remove(song);
	}

	private void fillTestData() {
		addSong(new Song("Chocolate", "Snow Patrol", "Final Straw",
				"sp-final-straw.jpg", "chocolate.mp3"));
		addSong(new Song("Concerto No. 2 in E", "Hilary Hahn",
				"Bach: Violin Concertos", "hahn.jpg", "bachviolin2.mp3"));
		addSong(new Song("‘Round Midnight", "Wes Montgomery",
				"The Artistry of Wes Montgomery", "wesmontgomery.jpg",
				"roundmidnight.mp3"));
	}
}
