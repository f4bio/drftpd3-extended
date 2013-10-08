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
package org.drftpd.commands.config.hooks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MalformedPatternException;
import org.drftpd.GlobalContext;
import org.drftpd.config.ConfigHandler;
import org.drftpd.dynamicdata.Key;
import org.drftpd.dynamicdata.KeyedMap;
import org.drftpd.permissions.GlobPathPermission;
import org.drftpd.permissions.MessagePathPermission;
import org.drftpd.permissions.Permission;

/**
 * Handles most of the perms.conf directives that aren't releated to the VFS.
 * @author fr0w
 * @version $Id: DefaultConfigHandler.java 2157 2010-10-08 18:50:53Z djb61 $
 */
public class DefaultConfigHandler extends ConfigHandler {
	private static final Logger logger = Logger.getLogger(DefaultConfigHandler.class);
	
	protected static final Key<ArrayList<MessagePathPermission>> MSGPATH = new Key<ArrayList<MessagePathPermission>>(DefaultConfigHandler.class, "msgPath");
	
	public void handlePathPerm(String directive, StringTokenizer st) throws MalformedPatternException {
		addPathPermission(directive, new GlobPathPermission(st.nextToken(), Permission.makeUsers(st)));
	}
	
	public void handlePerm(String directive, StringTokenizer st) {
		addPermission(directive, new Permission(Permission.makeUsers(st)));
	}
	
	public void handleMsgPath(String directive, StringTokenizer st) {
		String pattern = st.nextToken();
		String messageFile = st.nextToken();
		
		MessagePathPermission perm = null;
		try {
			perm = new MessagePathPermission(pattern, messageFile, Permission.makeUsers(st));
		} catch (IOException e) {
			logger.error("Unable to read "+messageFile+" directive ignored");
		}
		
		KeyedMap<Key<?>, Object> map = GlobalContext.getConfig().getKeyedMap();		
		ArrayList<MessagePathPermission> list = map.getObject(MSGPATH, null);		
		if (list == null) { // in case that's the first directive that's being loaded
			list = new ArrayList<MessagePathPermission>();
			map.setObject(MSGPATH, list);
		}
		
		list.add(perm);
	}
}
