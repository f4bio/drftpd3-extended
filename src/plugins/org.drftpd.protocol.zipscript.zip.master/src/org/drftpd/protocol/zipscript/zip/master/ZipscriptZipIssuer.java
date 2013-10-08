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
package org.drftpd.protocol.zipscript.zip.master;

import org.drftpd.exceptions.SlaveUnavailableException;
import org.drftpd.master.RemoteSlave;
import org.drftpd.protocol.master.AbstractIssuer;
import org.drftpd.slave.async.AsyncCommandArgument;

/**
 * @author djb61
 * @version $Id: ZipscriptZipIssuer.java 1976 2009-08-08 16:48:50Z djb61 $
 */
public class ZipscriptZipIssuer extends AbstractIssuer {
	public String issueZipCRCToSlave(RemoteSlave rslave, String path)throws SlaveUnavailableException {
		String index = rslave.fetchIndex();
		AsyncCommandArgument ac = new AsyncCommandArgument(index, "zipcrc", path);
		rslave.sendCommand(ac);

		return index;
	}

	public String issueZipDizInfoToSlave(RemoteSlave rslave, String path)throws SlaveUnavailableException {
		String index = rslave.fetchIndex();
		AsyncCommandArgument ac = new AsyncCommandArgument(index, "zipdiz", path);
		rslave.sendCommand(ac);

		return index;
	}
}
