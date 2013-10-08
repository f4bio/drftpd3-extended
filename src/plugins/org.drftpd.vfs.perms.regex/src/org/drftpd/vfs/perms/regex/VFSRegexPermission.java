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
package org.drftpd.vfs.perms.regex;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.drftpd.permissions.Permission;
import org.drftpd.permissions.RegexPathPermission;
import org.drftpd.vfs.perms.VFSPermHandler;

/**
 * Handles Regex Permissions.
 * @author fr0w
 * @version $Id: VFSRegexPermission.java 2157 2010-10-08 18:50:53Z djb61 $
 */
public class VFSRegexPermission extends VFSPermHandler {	
	public void handle(String directive, StringTokenizer st) {
		Pattern p = Pattern.compile(st.nextToken(), Pattern.CASE_INSENSITIVE);
		addPermission(directive, new RegexPathPermission(p, Permission.makeUsers(st)));
	}	
}
