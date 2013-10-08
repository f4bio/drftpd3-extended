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
package org.drftpd.permissions;

import java.util.Collection;

import org.apache.oro.text.regex.MalformedPatternException;

/**
 * @author mog
 * @version $Id: RatioPathPermission.java 2125 2010-09-29 11:37:42Z scitz0 $
 */
public class RatioPathPermission extends GlobPathPermission {
	private float _ratio;

	/**
	 * @param pattern
	 * @param ratio
	 * @param users
	 * @throws MalformedPatternException 
	 */
	public RatioPathPermission(String pattern, float ratio, Collection<String> users)
			throws MalformedPatternException {
		super(pattern, users);
		_ratio = ratio;
	}

	public float getRatio() {
		return _ratio;
	}
}
