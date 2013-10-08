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

import java.io.IOException;

/**
 * @author mog
 * 
 * @version $Id: IllegalTargetException.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class IllegalTargetException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9073839433603984027L;

	/**
	 * 
	 */
	public IllegalTargetException() {
		super();
	}

	/**
	 * @param message
	 */
	public IllegalTargetException(String message) {
		super(message);
	}
}
