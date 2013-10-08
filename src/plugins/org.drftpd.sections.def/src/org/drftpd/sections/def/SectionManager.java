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
package org.drftpd.sections.def;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.drftpd.GlobalContext;
import org.drftpd.master.ConnectionManager;
import org.drftpd.sections.SectionInterface;
import org.drftpd.sections.SectionManagerInterface;
import org.drftpd.vfs.DirectoryHandle;
import org.drftpd.vfs.ObjectNotValidException;

/**
 * @author mog
 * @version $Id: SectionManager.java 2001 2009-09-05 12:32:41Z djb61 $
 */
public class SectionManager implements SectionManagerInterface {
	
	private static final Logger logger = Logger.getLogger(SectionManager.class);

	public SectionManager() {
	}

	public ConnectionManager getConnectionManager() {
		return ConnectionManager.getConnectionManager();
	}

	public SectionInterface getSection(String name) {
		try {
			try {
				return new Section(getGlobalContext().getRoot()
						.getDirectoryUnchecked(name));
			} catch (ObjectNotValidException e) {
				logger.error("Section defined " + name + " is not a file");
				return new Section(getGlobalContext().getRoot());
			}
		} catch (FileNotFoundException e) {
			return new Section(getGlobalContext().getRoot());
		}
	}

	private GlobalContext getGlobalContext() {
		return GlobalContext.getGlobalContext();
	}

	@SuppressWarnings("unchecked")
	public Collection<SectionInterface> getSections() {
		ArrayList<SectionInterface> sections = new ArrayList<SectionInterface>();
		
		Set<DirectoryHandle> dirs;
		try {
			dirs = GlobalContext.getGlobalContext().getRoot().getDirectoriesUnchecked();
		} catch (FileNotFoundException e) {
			return Collections.EMPTY_SET;
		}
		
		for (DirectoryHandle dir : dirs) {
			sections.add(new Section(dir));
		}

		return sections;
	}

	public void reload() {
	}

	public SectionInterface lookup(DirectoryHandle dir) {
		try {
			DirectoryHandle parent = dir.getParent();
			if (parent.isRoot()) {
				return new Section(dir);
			}
			return lookup(parent);
		} catch (IllegalStateException e) {
			throw new IllegalStateException(
					"The RootDirectory does not have a section");
		}

	}

	static class Section implements SectionInterface {
		private DirectoryHandle _dir;

		public Section(DirectoryHandle lrf) {
			_dir = lrf;
		}

		public DirectoryHandle getCurrentDirectory() {
			return _dir;
		}

		@SuppressWarnings("unchecked")
		public Set<DirectoryHandle> getDirectories() {
			try {
				return _dir.getDirectoriesUnchecked();
			} catch (FileNotFoundException e) {
				return Collections.EMPTY_SET;
			}
		}

		public String getName() {
			return _dir.getName();
		}

		public String getPath() {
			return _dir.getPath();
		}

		public DirectoryHandle getBaseDirectory() {
			return _dir;
		}

		public String getBasePath() {
			return getPath();
		}

		@Override
		public boolean equals(Object arg0) {
			if (!(arg0 instanceof Section)) {
				return false;
			}
			Section compareSection = (Section)arg0;
			return getBaseDirectory().equals(compareSection.getBaseDirectory());
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, SectionInterface> getSectionsMap() {
		HashMap<String, SectionInterface> sections = new HashMap<String, SectionInterface>();
		
		try {
			for (DirectoryHandle dir : getGlobalContext().getRoot().getDirectoriesUnchecked()) {
				sections.put(dir.getName(), new Section(dir));
			}
		} catch (FileNotFoundException e) {
			return Collections.EMPTY_MAP;
		}
		
		return sections;
	}
}
