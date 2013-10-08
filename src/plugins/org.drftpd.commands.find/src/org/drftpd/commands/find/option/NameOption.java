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
package org.drftpd.commands.find.option;

import org.drftpd.commandmanager.ImproperUsageException;
import org.drftpd.commands.find.FindUtils;
import org.drftpd.vfs.index.AdvancedSearchParams;

/**
 * @author scitz0
 * @version $Id: NameOption.java 2482 2011-06-28 10:20:44Z scitz0 $
 */
public class NameOption implements OptionInterface {

	@Override
	public void exec(String option, String[] args, AdvancedSearchParams params) throws ImproperUsageException {
		if (args == null) {
			throw new ImproperUsageException("Missing argument for "+option+" option");
		}
		if (option.equalsIgnoreCase("-name")) {
			params.setName(FindUtils.getStringFromArray(args, " "));
		} else if (option.equalsIgnoreCase("-regex")) {
			params.setRegex(FindUtils.getStringFromArray(args, " "));
		} else if (option.equalsIgnoreCase("-exact")) {
			params.setExact(FindUtils.getStringFromArray(args, " "));
		} else if (option.equalsIgnoreCase("-endswith")) {
			params.setEndsWith(FindUtils.getStringFromArray(args, " "));
		}
	}
}
