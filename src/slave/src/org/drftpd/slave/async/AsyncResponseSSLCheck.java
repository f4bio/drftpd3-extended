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
package org.drftpd.slave.async;

/**
 * @author fr0w
 * @version $Id: AsyncResponseSSLCheck.java 1945 2009-07-25 18:32:01Z djb61 $
 */
@SuppressWarnings("serial")
public class AsyncResponseSSLCheck extends AsyncResponse {
	private boolean _sslCheck = false;
	
	public AsyncResponseSSLCheck(String index, boolean sslCheck) {
		super(index);
		_sslCheck = sslCheck;	
	}
	
	public boolean isSSLReady() {
		return _sslCheck;
	}
	
	public String toString() {
		return getClass().getName() + "[isSSLReady=" + isSSLReady() + "]";
	}
}
