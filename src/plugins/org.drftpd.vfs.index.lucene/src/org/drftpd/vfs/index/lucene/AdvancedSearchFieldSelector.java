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

package org.drftpd.vfs.index.lucene;

import org.apache.lucene.document.FieldSelector;
import org.apache.lucene.document.FieldSelectorResult;

/**
 * This field selector helps advanced searches to be faster,
 * loading only the path and inode type from the Index,
 * making the other options to be loaded lazily.
 * @author scitz0
 * @version $Id: AdvancedSearchFieldSelector.java 2260 2010-11-06 01:54:56Z scitz0 $
 */
@SuppressWarnings("serial")
public class AdvancedSearchFieldSelector implements FieldSelector {
	public FieldSelectorResult accept(String fieldName) {
		if (fieldName.equals("type") || fieldName.equals("fullPath")) {
			return FieldSelectorResult.LOAD;
		}
		
		return FieldSelectorResult.LAZY_LOAD;
	}
}
