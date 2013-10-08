/*
 * This file is part of DrFTPD, Distributed FTP Daemon.
 *
 * DrFTPD is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * DrFTPD is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DrFTPD; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.drftpd.protocol.zipscript.mp3.common;

import org.apache.log4j.Logger;

/**
 * @author djb61
 * @version $Id: ID3GenreList.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class ID3GenreList {

	private static final Logger logger = Logger.getLogger(ID3GenreList.class);

	public static final String[] genres = {
		"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk",
		"Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies",
		"Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno",
		"Industrial", "Alternative", "Ska", "Death Metal", "Pranks",
		"Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal",
		"Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental",
		"Acid", "House", "Game", "Sound Clip", "Gospel", "Noise",
		"Alternative Rock", "Bass", "Soul", "Punk", "Space",
		"Meditative", "Instrumental Pop", "Instrumental Rock",
		"Ethnic", "Gothic", "Darkwave", "Techno-Industrial",
		"Electronic", "Pop-Folk", "Eurodance", "Dream",
		"Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40",
		"Christian Rap", "Pop/Funk", "Jungle", "Native US", "Cabaret",
		"New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer",
		"Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro",
		"Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock",
		"National Folk", "Swing", "Fast Fusion", "Bebob", "Latin",
		"Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock",
		"Progressive Rock", "Psychedelic Rock", "Symphonic Rock",
		"Slow Rock", "Big Band", "Chorus", "Easy Listening",
		"Acoustic", "Humour", "Speech", "Chanson", "Opera",
		"Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus",
		"Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba",
		"Folklore", "Ballad", "Power Ballad", "Rhytmic Soul",
		"Freestyle", "Duet", "Punk Rock", "Drum Solo", "Acapella",
		"Euro-House", "Dance Hall", "Goa", "Drum & Bass",
		"Club-House", "Hardcore", "Terror", "Indie", "BritPop",
		"Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta",
		"Heavy Metal", "Black Metal", "Crossover", "Contemporary C",
		"Christian Rock", "Merengue", "Salsa", "Thrash Metal",
		"Anime", "JPop", "SynthPop"
	};

	/** Returns the Genre for given number
	 *        @return Genre as String
	 */
	public static String getGenre(int number) {
		int index = number & 0xff;
		try {
			return genres[index];
		} catch(ArrayIndexOutOfBoundsException e) {
			logger.warn("Unknown genre number: "+index);
			return "";
		}
	}
}
