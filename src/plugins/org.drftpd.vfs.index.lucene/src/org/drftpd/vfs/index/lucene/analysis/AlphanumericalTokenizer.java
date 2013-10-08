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
package org.drftpd.vfs.index.lucene.analysis;

import java.io.Reader;

import org.apache.lucene.analysis.CharTokenizer;
import org.apache.lucene.util.Version;

/**
 * @author fr0w
 * @version $Id: AlphanumericalTokenizer.java 2457 2011-06-10 15:20:30Z cyber1331 $
 */
public class AlphanumericalTokenizer extends CharTokenizer {
	public AlphanumericalTokenizer(Reader input) {
		super(Version.LUCENE_32, input);
	}

	@Override
	protected boolean isTokenChar(int c) {
		return Character.isLetter(c) || Character.isDigit(c) || isWildcardChar(c);
	}
	
	private boolean isWildcardChar(int c) {
		return c == '?' || c == '*';
	}
	
	@Override
	protected int normalize(int c) {
	    return (Character.isLetter(c) ? Character.toLowerCase(c) : c);
	}
}
