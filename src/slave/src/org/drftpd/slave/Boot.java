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
package org.drftpd.slave;

import org.java.plugin.boot.Application;
import org.java.plugin.boot.ApplicationPlugin;
import org.java.plugin.util.ExtendedProperties;

import org.drftpd.slave.Slave;

/**
 * @author djb61
 * @version $Id: Boot.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class Boot extends ApplicationPlugin implements Application {

	protected void doStart() throws Exception {
		// no-op
	}

	protected void doStop() throws Exception {
		// no-op
	}
	protected Application initApplication(final ExtendedProperties config, String[] args) throws Exception {
		return this;
	}
	public void startApplication() throws Exception {
		Slave.boot();
	}
}
