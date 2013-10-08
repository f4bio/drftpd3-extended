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
package org.drftpd.event;

import org.drftpd.usermanager.User;
import org.drftpd.vfs.DirectoryHandle;

/**
 * @author mog
 * 
 * @version $Id: DirectoryFtpEvent.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class DirectoryFtpEvent extends ConnectionEvent {
	private DirectoryHandle directory;

	public DirectoryFtpEvent(User user, String command,
			DirectoryHandle directory) {
		this(user, command, directory, System.currentTimeMillis());
	}

	public DirectoryFtpEvent(User user, String command,
			DirectoryHandle directory, long time) {
		super(user, command, time);
		this.directory = directory;
	}

	public DirectoryHandle getDirectory() {
		return directory;
	}

	public String toString() {
		return getClass().getName() + "[user=" + getUser() + ",cmd="
				+ getCommand() + ",directory=" + directory.getPath() + "]";
	}
}
