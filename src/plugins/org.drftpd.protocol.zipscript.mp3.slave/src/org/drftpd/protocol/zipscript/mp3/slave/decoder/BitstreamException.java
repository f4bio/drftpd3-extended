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
 * @author Originally taken from JLayer - http://www.javazoom.net/javalayer/javalayer.html
 * @version $Id: BitstreamException.java 1945 2009-07-25 18:32:01Z djb61 $
 */
@SuppressWarnings("serial")
public class BitstreamException extends JavaLayerException implements BitstreamErrors {

	private int errorcode = UNKNOWN_ERROR;

	public BitstreamException(String msg, Throwable t) {
		super(msg, t);	
	}

	public BitstreamException(int errorcode, Throwable t) {
		this(getErrorString(errorcode), t);
		this.errorcode = errorcode;
	}

	public int getErrorCode() {
		return errorcode;	
	}

	static public String getErrorString(int errorcode) {
		return "Bitstream errorcode "+Integer.toHexString(errorcode);
	}


}
