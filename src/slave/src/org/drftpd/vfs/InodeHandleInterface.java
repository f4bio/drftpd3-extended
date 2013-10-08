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
package org.drftpd.vfs;

import java.io.FileNotFoundException;


/**
 * Lowest level of InodeHandles.<br>
 * This class provides more flexibility and organization to the VFS.
 * @author zubov
 * @version $Id: InodeHandleInterface.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public interface InodeHandleInterface {
	public String getGroup() throws FileNotFoundException;

	public String getName();

	public long getSize() throws FileNotFoundException;

	public String getUsername() throws FileNotFoundException;

	public long lastModified() throws FileNotFoundException;

	public boolean isDirectory() throws FileNotFoundException;
	
	public boolean isFile() throws FileNotFoundException;
	
	public boolean isLink() throws FileNotFoundException;
	
	public String getPath();
}
