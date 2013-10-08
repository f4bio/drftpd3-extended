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
package org.drftpd.commands.dummy;

import java.util.ArrayList;
import java.util.Map;

import org.drftpd.commandmanager.CommandInstanceContainer;
import org.drftpd.commandmanager.CommandInterface;
import org.drftpd.commandmanager.CommandRequest;
import org.drftpd.commandmanager.CommandResponse;
import org.drftpd.commandmanager.StandardCommandManager;

/**
 * returns 200 Command OK on all commands
 *
 * @author mog
 * @version $Id: Dummy.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class Dummy extends CommandInterface {

	private StandardCommandManager _cManager;

	public void initialize(String method, String pluginName, StandardCommandManager cManager) {
    	super.initialize(method, pluginName, cManager);
    	_cManager = cManager;
    }

	public CommandResponse doDUMMY(CommandRequest request) {
		return StandardCommandManager.genericResponse("RESPONSE_200_COMMAND_OK");
	}

	private ArrayList<String> getHandledCommands(Class<?> class1) {
		ArrayList<String> list = new ArrayList<String>();

		for (Map.Entry<String,CommandInstanceContainer> element : _cManager.getCommandHandlersMap().entrySet()) {
			if (element.getValue().getCommandInterfaceInstance().getClass().equals(class1)) {
				list.add(element.getKey().toUpperCase());
			}
		}
		return list;
	}

	public String[] getFeatReplies() {
		return getHandledCommands(getClass()).toArray(new String[0]);
	}
}
