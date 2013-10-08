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
package org.drftpd.commands.find.action;

import org.drftpd.commandmanager.CommandRequest;
import org.drftpd.commandmanager.ImproperUsageException;
import org.drftpd.vfs.InodeHandle;

/**
 * @author scitz0
 * @version $Id: ActionInterface.java 2482 2011-06-28 10:20:44Z scitz0 $
 */
public interface ActionInterface {

	public void initialize(String action, String[] args) throws ImproperUsageException;

	public String exec(CommandRequest request, InodeHandle inode);

	public boolean execInDirs();

	public boolean execInFiles();
	
	public boolean failed();
}
