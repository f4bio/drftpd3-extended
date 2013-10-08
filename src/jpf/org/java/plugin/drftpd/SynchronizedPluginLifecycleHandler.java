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
package org.java.plugin.drftpd;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.registry.PluginDescriptor;
import org.java.plugin.standard.StandardPluginLifecycleHandler;
import org.java.plugin.util.ExtendedProperties;

/**
 * Standard implementation of plug-in life cycle handler.
 * 
 * Creates class loaders for plugins which are synchronized using a global mutex, this
 * makes all class loading from plugin framework loaders single threaded.
 * @see org.java.plugin.standard.StandardPluginLifecycleHandler
 * 
 * @author djb61
 * @version $Id: SynchronizedPluginLifecycleHandler.java 1941 2009-07-18 17:29:06Z djb61 $
 */
public class SynchronizedPluginLifecycleHandler extends StandardPluginLifecycleHandler {

	private final Log log = LogFactory.getLog(getClass());
	private boolean probeParentLoaderLast;
	private boolean stickySynchronizing;
	private boolean localClassLoadingOptimization;
	private boolean foreignClassLoadingOptimization;

	/**
	 * Creates synchronized implementation of plug-in class loader.
	 * @see org.java.plugin.standard.StandardPluginLifecycleHandler#createPluginClassLoader(
	 *      org.java.plugin.registry.PluginDescriptor)
	 */
	@Override
	protected org.java.plugin.PluginClassLoader createPluginClassLoader(
			final PluginDescriptor descr) {
		SynchronizedPluginClassLoader result;
		if (System.getSecurityManager() != null) {
			result = AccessController.doPrivileged(
					new PrivilegedAction<SynchronizedPluginClassLoader>() {
						public SynchronizedPluginClassLoader run() {
							return new SynchronizedPluginClassLoader(getPluginManager(), descr,
									SynchronizedPluginLifecycleHandler.this.getClass()
									.getClassLoader());
						}
					});
		} else {
			result = new SynchronizedPluginClassLoader(getPluginManager(), descr,
					SynchronizedPluginLifecycleHandler.this.getClass()
					.getClassLoader());
		}
		result.setProbeParentLoaderLast(probeParentLoaderLast);
		result.setStickySynchronizing(stickySynchronizing);
		result.setLocalClassLoadingOptimization(localClassLoadingOptimization);
		result.setForeignClassLoadingOptimization(foreignClassLoadingOptimization);

		return result;
	}

	/**
	 * @see org.java.plugin.standard.StandardPluginLifecycleHandler#configure(
	 *      ExtendedProperties)
	 */
	@Override
	public void configure(ExtendedProperties config) {
		probeParentLoaderLast = "true".equalsIgnoreCase( //$NON-NLS-1$
				config.getProperty("probeParentLoaderLast", "false")); //$NON-NLS-1$ //$NON-NLS-2$
		log.debug("probeParentLoaderLast parameter value is " //$NON-NLS-1$
				+ probeParentLoaderLast);
		stickySynchronizing = "true".equalsIgnoreCase( //$NON-NLS-1$
				config.getProperty("stickySynchronizing", "false")); //$NON-NLS-1$ //$NON-NLS-2$
		log.debug("stickySynchronizing parameter value is " //$NON-NLS-1$
				+ stickySynchronizing);
		localClassLoadingOptimization = !"false".equalsIgnoreCase( //$NON-NLS-1$
				config.getProperty("localClassLoadingOptimization", //$NON-NLS-1$
				"true")); //$NON-NLS-1$
		log.debug("localLoadingClassOptimization parameter value is " //$NON-NLS-1$
				+ localClassLoadingOptimization);
		foreignClassLoadingOptimization = !"false".equalsIgnoreCase( //$NON-NLS-1$
				config.getProperty("foreignClassLoadingOptimization", //$NON-NLS-1$
				"true")); //$NON-NLS-1$
		log.debug("foreignClassLoadingOptimization parameter value is " //$NON-NLS-1$
				+ foreignClassLoadingOptimization);
	}
}
