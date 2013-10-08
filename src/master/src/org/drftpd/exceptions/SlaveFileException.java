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
package org.drftpd.exceptions;

/**
 * @author zubov
 * @version $Id: SlaveFileException.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class SlaveFileException extends Exception {

	private static final long serialVersionUID = 1053787666019821920L;

	public SlaveFileException() {
		super();
	}

	public SlaveFileException(String message) {
		super(message);
	}

	public SlaveFileException(Throwable cause) {
		super(cause);
	}

	public SlaveFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
