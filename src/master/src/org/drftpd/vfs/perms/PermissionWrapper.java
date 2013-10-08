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
package org.drftpd.vfs.perms;

import java.lang.reflect.Method;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * Wraps the PermissionHandler instance and its "handling" method.
 * @author fr0w
 * @version $Id: PermissionWrapper.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class PermissionWrapper {

	private static final Logger logger = Logger.getLogger(PermissionWrapper.class);
	
	private VFSPermHandler _permHandler;
	private Method _method;
	
	public PermissionWrapper(VFSPermHandler permHandler, Method method) {
		_permHandler = permHandler;
		_method = method;
	}
	
	/**
	 * Invoke, passing the right parameters, the PermissionHandler for given line.
	 * @param directive
	 * @param st
	 */
	public void handle(String directive, StringTokenizer st) {
		try {
			_method.invoke(_permHandler, directive, st);
		} catch (Exception e) {
			logger.error("Unable to handle '"+directive+"'", e);
		}
	}
}
