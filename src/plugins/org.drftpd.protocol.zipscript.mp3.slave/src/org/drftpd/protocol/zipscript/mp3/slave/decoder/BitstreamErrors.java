/*
 * This file is part of DrFTPD, Distributed FTP Daemon.
 *
 * DrFTPD is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * DrFTPD is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * DrFTPD; if not, write to the Free Software Foundation, Inc., 59 Temple Place,
 * Suite 330, Boston, MA 02111-1307 USA
 */
package org.drftpd.protocol.zipscript.mp3.slave.decoder;

/**
 * This interface describes all error codes that can be thrown 
 * in <code>BistreamException</code>s.
 * 
 * @see BitstreamException
 * 
 * @author Originally taken from JLayer - http://www.javazoom.net/javalayer/javalayer.html
 * @version $Id: BitstreamErrors.java 1925 2009-06-15 21:46:05Z tdsoul $
 */

public interface BitstreamErrors extends JavaLayerErrors {

	/**
	 * An undeterminable error occurred. 
	 */
	static public final int UNKNOWN_ERROR = BITSTREAM_ERROR + 0;

	/**
	 * The header describes an unknown sample rate.
	 */
	static public final int UNKNOWN_SAMPLE_RATE = BITSTREAM_ERROR + 1;

	/**
	 * A problem occurred reading from the stream.
	 */
	static public final int STREAM_ERROR = BITSTREAM_ERROR + 2;

	/**
	 * The end of the stream was reached prematurely. 
	 */
	static public final int UNEXPECTED_EOF = BITSTREAM_ERROR + 3;

	/**
	 * The end of the stream was reached. 
	 */
	static public final int STREAM_EOF = BITSTREAM_ERROR + 4;

	/**
	 * Frame data are missing. 
	 */
	static public final int INVALIDFRAME = BITSTREAM_ERROR + 5;

	/**
	 * 
	 */
	static public final int BITSTREAM_LAST = 0x1ff;

}