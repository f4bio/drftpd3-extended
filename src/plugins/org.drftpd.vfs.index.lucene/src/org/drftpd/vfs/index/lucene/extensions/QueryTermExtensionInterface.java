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
package org.drftpd.vfs.index.lucene.extensions;

import org.apache.lucene.search.BooleanQuery;
import org.drftpd.vfs.index.AdvancedSearchParams;

/**
 * @author djb61
 * @version $Id: QueryTermExtensionInterface.java 2479 2011-06-26 17:35:45Z djb61 $
 */
public interface QueryTermExtensionInterface {

	/**
	 * This method is called whenever a query is made to the index.
	 * If any relevant data is contained within the extension map of
	 * the search parameters this should be added to the index query
	 * appropriately.
	 * 
	 * @param doc
	 *            The document to populate fields in.
	 * 
	 * @param inode
	 *            The inode currently being indexed.
	 */
	public void addQueryTerms(BooleanQuery query, AdvancedSearchParams params);
}
