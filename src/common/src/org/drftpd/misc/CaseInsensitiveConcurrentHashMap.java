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
package org.drftpd.misc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author djb61
 * @version $Id: CaseInsensitiveConcurrentHashMap.java 2157 2010-10-08 18:50:53Z djb61 $ Must be CaseInsensitiveConcurrentHashMap <String,V>
 */
@SuppressWarnings("serial")
public class CaseInsensitiveConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

	public CaseInsensitiveConcurrentHashMap() {
		super();
	}

	public boolean containsKey(String arg0) {
		return super.containsKey(arg0.toLowerCase());
	}

	public V get(String arg0) {
		return super.get(arg0.toLowerCase());
	}

	@SuppressWarnings("unchecked")
	public V put(K arg0, V arg1) {
		return super.put(((K) ((String) arg0).toLowerCase()), arg1);
	}
}
