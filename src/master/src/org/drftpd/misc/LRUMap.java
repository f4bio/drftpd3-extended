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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU means least-recently-used.<br>
 * This map is backed by a LinkedHashMap and overrides the removeEldestEntry() method to make this map an actual LRU map.<br>
 * @author fr0w
 * @version $Id: LRUMap.java 1945 2009-07-25 18:32:01Z djb61 $
 */
@SuppressWarnings("serial")
public class LRUMap<K, V> extends LinkedHashMap<K, V> {
	
	private int _maxSize = Integer.MAX_VALUE; 
	
	public LRUMap(int maxSize) {
		super(maxSize, 0.75f);
		_maxSize = maxSize;
	}
	
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		return size() > _maxSize;
	}
	
	public int getMaxSize() {
		return _maxSize;
	}
}
