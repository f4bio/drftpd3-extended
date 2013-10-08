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
package org.drftpd.commands.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.drftpd.GlobalContext;
import org.drftpd.commandmanager.CommandInstanceContainer;
import org.drftpd.commandmanager.CommandInterface;
import org.drftpd.commandmanager.CommandRequest;
import org.drftpd.commandmanager.CommandResponse;
import org.drftpd.commandmanager.StandardCommandManager;
import org.drftpd.master.BaseFtpConnection;
import org.tanesha.replacer.FormatterException;
import org.tanesha.replacer.ReplacerEnvironment;
import org.tanesha.replacer.SimplePrintf;


/**
 * @version $Id: Misc.java 1983 2009-08-23 16:51:16Z djb61 $
 */
public class Misc extends CommandInterface {

	private static final Logger logger = Logger
		.getLogger(Misc.class);

	private StandardCommandManager _cManager;

	private ResourceBundle _bundle;

	private String _keyPrefix;
	
	public void initialize(String method, String pluginName, StandardCommandManager cManager) {
    	super.initialize(method, pluginName, cManager);
    	_cManager = cManager;
    	_bundle = cManager.getResourceBundle();
    	_keyPrefix = this.getClass().getName()+".";
    }

    /**
     * <code>ABOR &lt;CRLF&gt;</code><br>
     *
     * This command tells the server to abort the previous FTP
     * service command and any associated transfer of data.
     * No action is to be taken if the previous command
     * has been completed (including data transfer).  The control
     * connection is not to be closed by the server, but the data
     * connection must be closed.
     * Current implementation does not do anything. As here data
     * transfers are not multi-threaded.
     */
	public CommandResponse doABOR(CommandRequest request) {
    	request.getSession().abortCommand();
    	return new CommandResponse(226, request.getCommand().toUpperCase() + " command successful");
    }

    // LIST;NLST;RETR;STOR
    public CommandResponse doFEAT(CommandRequest request) {
    	BaseFtpConnection conn = (BaseFtpConnection) request.getSession();
        ArrayList<String> featFound = new ArrayList<String>();
        conn.printOutput("211-Extensions supported:\r\n");

        for (CommandInstanceContainer container : _cManager.getCommandHandlersMap().values()) {
        	CommandInterface hnd = container.getCommandInterfaceInstance();
        	String[] feat = hnd.getFeatReplies();  
        	if (feat == null) {
        		continue;  
        	}
        	
        	for (int i = 0; i < feat.length; i++) {
                if (!featFound.contains(feat[i])) {
                	conn.printOutput(" " + feat[i] + "\r\n");
                	featFound.add(feat[i]);
                }
            }
        }

        conn.printOutput("211 End\r\n");

        return null;
    }

    /**
     * <code>HELP [&lt;SP&gt; <string>] &lt;CRLF&gt;</code><br>
     *
     * This command shall cause the server to send helpful
     * information regarding its implementation status over the
     * control connection to the user.  The command may take an
     * argument (e.g., any command name) and return more specific
     * information as a response.
     */

    //TODO implement HELP, SITE HELP would be good too.
    //	public FtpReply doHELP(BaseFtpConnection conn) {
    //
    // print global help
    //		if (!request.hasArgument()) {
    //		FtpReply response = new FtpReply(214);
    //		response.addComment("The following commands are recognized.");
    //out.write(ftpStatus.getResponse(214, null, user, null));
    //		Method methods[] = this.getClass().getDeclaredMethods();
    //		for (int i = 0; i < methods.length; i++) {
    //			Method method = methods[i];
    //			Class parameterTypes[] = method.getParameterTypes();
    //			if (parameterTypes.length == 2
    //				&& parameterTypes[0] == FtpRequest.class
    //				&& parameterTypes[1] == PrintWriter.class) {
    //				String commandName =
    //					method.getName().substring(2).replace('_', ' ');
    //				response.addComment(commandName);
    //			}
    //		}
    //		out.print(response);
    //		return;
    //		}
    //
    //		// print command specific help
    //		String ftpCmd = request.getArgument().toUpperCase();
    //		String args[] = null;
    //		FtpRequest tempRequest = new FtpRequest(ftpCmd);
    //		out.write(ftpStatus.getResponse(214, tempRequest, user, args));
    //		return;
    //	}
    public CommandResponse doSITE_STAT(CommandRequest request) {
        if (request.hasArgument()) {
        	return StandardCommandManager.genericResponse("RESPONSE_504_COMMAND_NOT_IMPLEMENTED_FOR_PARM");
        }

        CommandResponse response = StandardCommandManager.genericResponse("RESPONSE_200_COMMAND_OK");

        /* TODO maybe think of another way of doing this so as to not pull in
         * BaseFtpConnection
         */
        BaseFtpConnection conn = (BaseFtpConnection) request.getSession();
        response.addComment(conn.status());

        return response;
    }

    public CommandResponse doSITE_TIME(CommandRequest request) {
    	if (request.hasArgument()) {
        	return StandardCommandManager.genericResponse("RESPONSE_501_SYNTAX_ERROR");
        }

    	return new CommandResponse(200, "Server time is: " + new Date());
    }

    public CommandResponse doSITE_HELP(CommandRequest request) {
    	/* TODO: the old implementation would check whether the issuing
    	 * user had permissions for the command before giving specific
    	 * help or listing that command in general help, this implementation
    	 * currently does not.
    	 */
    	if (request.hasArgument()) {
    		CommandResponse response;
    		Properties cmdProperties = request.getSession().getCommands().get(request.getArgument());
    		if (cmdProperties == null) {
    			response = StandardCommandManager.genericResponse("RESPONSE_501_SYNTAX_ERROR");
    			response.addComment("The " + request.getArgument() + " command does not exist");
    			return response;
    		}
    		response = StandardCommandManager.genericResponse("RESPONSE_200_COMMAND_OK");
    		String helpString = cmdProperties.getProperty("help.specific");
    		if (helpString == null) {
    			response.addComment("Bug your siteop to add help for the \""
						+ request.getArgument() + "\" command");
    		}
    		else {
    			ReplacerEnvironment env = new ReplacerEnvironment();
    			env.add("command", request.getArgument().toUpperCase());
    			try {
    				response.addComment(SimplePrintf.jprintf(helpString,env));
    			}
    			catch (FormatterException e) {
    				response.addComment(request.getArgument() 
    						+ " command has an invalid help.specific definition");
    			}
    		}
    		return response;
    	}
    	// global list of commands with help
    	HashMap<String, String> helpInfo = new HashMap<String, String>();
    	HashMap<String, Properties> cmdProperties = request.getSession().getCommands();
    	// find which commands we should ignore
    	String noHelp = request.getProperties().getProperty("nohelp");
    	ArrayList<String> noHelpCommands = new ArrayList<String>();
    	if (noHelp != null) {
    		StringTokenizer st = new StringTokenizer(noHelp, ",");
    		while (st.hasMoreTokens()) {
    			noHelpCommands.add(st.nextToken().toLowerCase());
    		}
    	}
    	// find the longest command to enable padding
    	int cmdLength = 0;
    	for (String cmd : _cManager.getCommandHandlersMap().keySet()) {
    		if (cmd.length() > cmdLength && !noHelpCommands.contains(cmd)) {
    			cmdLength = cmd.length();
    		}
    	}
    	StringBuilder padBuilder = new StringBuilder();
    	for (int i = 0; i < cmdLength; i++) {
    		padBuilder.append(" ");
    	}
    	String pad = padBuilder.toString();
    	for (Entry<String,Properties> cmd :  cmdProperties.entrySet()) {
    		String helpString = cmd.getValue().getProperty("help");
    		if (helpString == null) {
    			if (!noHelpCommands.contains(cmd.getKey())) {
    				helpString = cmd.getKey() 
    					+ " does not have any help, bug your siteop";
    			}
    		}
    		try {
    			if (helpString != null) {
    				helpInfo.put(cmd.getKey(), pad.substring(cmd.getKey().length()) 
    					+ cmd.getKey() + " : " + helpString);
    			}
    		}
    		catch (java.lang.StringIndexOutOfBoundsException e) {
    			/* This really should not happen anymore but will leave
    			 * this check for now
    			 */
    			logger.error("Help command pad string too short");
    		}
    	}
    	ArrayList<String> sortedList = new ArrayList<String>(helpInfo.keySet());
    	Collections.sort(sortedList);
    	CommandResponse response = StandardCommandManager.genericResponse("RESPONSE_200_COMMAND_OK");
    	try {
    		response.addComment(_bundle.getString(_keyPrefix+"help.header"));
    	} catch (MissingResourceException e) {
    		response.addComment("Help has no header");
    	}
    	for (String cmd : sortedList) {
    		response.addComment(helpInfo.get(cmd));
    	}
    	try {
    		response.addComment(_bundle.getString(_keyPrefix+"help.footer"));
    	} catch (MissingResourceException e) {
    		response.addComment("Help has no footer");
    	}
    	return response;
    }

    public CommandResponse doSITE_VERS(CommandRequest request) {
    	return new CommandResponse(200, GlobalContext.VERSION);
    }
}
