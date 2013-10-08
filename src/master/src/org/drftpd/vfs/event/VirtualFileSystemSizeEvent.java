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
package org.drftpd.vfs.event;

import org.drftpd.vfs.VirtualFileSystemInode;

/**
 * This event is fired whenever the last modified timestamp of an inode changes
 * @author scitz0
 * @version $Id: VirtualFileSystemSizeEvent.java 2394 2011-04-12 21:29:52Z djb61 $
 */
public class VirtualFileSystemSizeEvent extends VirtualFileSystemEvent {

	private long _size;

	public VirtualFileSystemSizeEvent(VirtualFileSystemInode inode, String path, long size) {
		super(inode, path);
		
		_size = size;
	}

	/**
	 * @return the new size of the inode
	 */
	public long getSize() {
		return _size;
	}

}
