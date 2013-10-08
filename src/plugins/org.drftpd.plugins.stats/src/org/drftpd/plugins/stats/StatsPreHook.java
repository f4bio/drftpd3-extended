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
package org.drftpd.plugins.stats;

import java.io.FileNotFoundException;

import org.drftpd.commandmanager.CommandRequest;
import org.drftpd.commandmanager.CommandRequestInterface;
import org.drftpd.commandmanager.CommandResponse;
import org.drftpd.commandmanager.PreHookInterface;
import org.drftpd.commandmanager.StandardCommandManager;
import org.drftpd.usermanager.User;
import org.drftpd.vfs.DirectoryHandle;
import org.drftpd.vfs.ObjectNotValidException;

/**
 * @author fr0w
 * @version $Id: StatsPreHook.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class StatsPreHook implements PreHookInterface {
	public void initialize(StandardCommandManager manager) {
	}
	
	public CommandRequestInterface doRETRPreHook(CommandRequest request) {
		DirectoryHandle dir = request.getCurrentDirectory();
		User user = request.getSession().getUserNull(request.getUser());
		
		float ratio = StatsManager.getStatsManager().getCreditLossRatio(dir, user);
		
		if (ratio == 0L) {
			return request;
		}
				
		try {
			long fileSize = dir.getFileUnchecked(request.getArgument()).getSize();			
			long creditsLoss = (long) ratio * fileSize;
			long userCredits = user.getCredits();
			
			if (userCredits < creditsLoss) {
				// this comparison doesn't allow a user with negative credits,
				// but a 0 ratio to download files :)
				request.setAllowed(false);
				request.setDeniedResponse(new CommandResponse(550, "Not enough credits"));
			}
			
		} catch (FileNotFoundException e) {
			request.setAllowed(false);
			request.setDeniedResponse(StandardCommandManager.genericResponse("RESPONSE_550_REQUESTED_ACTION_NOT_TAKEN"));
		} catch (ObjectNotValidException e) {
			request.setAllowed(false);
			request.setDeniedResponse(new CommandResponse(550, "Argument is not a file"));
		}
		
		return request;
	}

}
