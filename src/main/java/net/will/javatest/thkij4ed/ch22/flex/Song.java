/**
 * @(#)Song.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.flex;

import java.io.Serializable;

/**
 * 
 * 
 * @author Will
 * @version 2012-4-19
 */
public class Song implements Serializable {
	private static final long serialVersionUID = 4352816902877067533L;

	private String name;
	private String artist;
	private String album;
	private String albumImageUrl;
	private String songMediaUrl;

	public Song() {
	}

	public Song(String name, String artist, String album, String albumImageUrl,
			String songMediaUrl) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.albumImageUrl = albumImageUrl;
		this.songMediaUrl = songMediaUrl;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbumImageUrl(String albumImageUrl) {
		this.albumImageUrl = albumImageUrl;
	}

	public String getAlbumImageUrl() {
		return albumImageUrl;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSongMediaUrl(String songMediaUrl) {
		this.songMediaUrl = songMediaUrl;
	}

	public String getSongMediaUrl() {
		return songMediaUrl;
	}
}
