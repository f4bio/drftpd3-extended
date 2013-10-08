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
package org.drftpd.slaveselection.filter;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.oro.text.GlobCompiler;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.drftpd.GlobalContext;
import org.drftpd.PropertyHelper;
import org.drftpd.exceptions.FatalException;
import org.drftpd.master.RemoteSlave;
import org.drftpd.usermanager.User;
import org.drftpd.vfs.InodeHandleInterface;

/**
 * Example slaveselection entry:
 * 
 * <pre>
 *  &lt;n&gt;.filter=matchdir
 *  &lt;n&gt;.assign=&lt;slavename&gt;+100000
 *  &lt;n&gt;.match=&lt;path glob match&gt;
 *  &lt;n&gt;.assume.remove=&lt;true&gt;
 *  &lt;n&gt;.negate.expression=&lt;true&gt;
 * </pre>
 * 
 * @author mog
 * @version $Id: MatchdirFilter.java 2357 2011-02-18 18:51:03Z cyber1331 $
 */
public class MatchdirFilter extends Filter {
	private ArrayList<AssignParser> _assigns;

	private Pattern _p;

	private boolean _negateExpr;
	
	public MatchdirFilter(int i, Properties p) {
		super(i, p);
		try {
			_assigns = AssignSlave.parseAssign(PropertyHelper.getProperty(p, i + ".assign"));

			boolean assumeRemove = PropertyHelper.getProperty(p, i + ".assume.remove", "false").
					equalsIgnoreCase("true");
			// If assume.remove=true, add all slaves not assigned a score to be removed
			if (assumeRemove) {
				for (RemoteSlave slave : GlobalContext.getGlobalContext().getSlaveManager().getSlaves()) {
					boolean assigned = false;
					for (AssignParser ap : _assigns) {
						if (slave.equals(ap.getRSlave())) {
							// Score added to slave already, skip
							assigned = true;
							break;
						}
					}
					if (!assigned) {
						// Add slave to be remove
						_assigns.add(new AssignParser(slave.getName()+"+remove"));
					}
				}
			}

			_p = new GlobCompiler().compile(PropertyHelper.getProperty(p, i	+ ".match"),Perl5Compiler.READ_ONLY_MASK);

			_negateExpr = PropertyHelper.getProperty(p, i + ".negate.expression", "false").
					equalsIgnoreCase("true");
		} catch (Exception e) {
			throw new FatalException(e);
		}
	}

	public void process(ScoreChart scorechart, User user, InetAddress source,
			char direction, InodeHandleInterface file, RemoteSlave sourceSlave) {
		Perl5Matcher m = new Perl5Matcher();
		boolean validPath = _negateExpr ? !m.matches(file.getPath(), _p) : m.matches(file.getPath(), _p);
		if (validPath) {
			AssignSlave.addScoresToChart(_assigns, scorechart);
		}
	}
}