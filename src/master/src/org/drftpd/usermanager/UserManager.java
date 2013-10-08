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
package org.drftpd.usermanager;

import java.util.Collection;

import org.drftpd.master.cron.TimeEventInterface;

/**
 * @author mog
 * @version $Id: UserManager.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public interface UserManager extends TimeEventInterface {
	public abstract void init() throws UserFileException;
	
	public abstract User create(String username) throws UserFileException;

	public abstract Collection<String> getAllGroups();

	/**
	 * Get all user names in the system.
	 */
	public abstract Collection<User> getAllUsers();

	public abstract Collection<User> getAllUsersByGroup(String group);

	/**
	 * Get user by name.
	 */
	public abstract User getUserByName(String username)
			throws NoSuchUserException, UserFileException;

	public abstract User getUserByIdent(String ident, String botName)
			throws NoSuchUserException;

	public abstract User getUserByNameUnchecked(String username)
			throws NoSuchUserException, UserFileException;

	public abstract User getUserByNameIncludeDeleted(String argument)
			throws NoSuchUserException, UserFileException;
}
