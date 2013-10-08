package org.drftpd.tests;

import org.drftpd.master.RemoteSlave;


/**
 * @author zubov
 * @version $Id: DummyRemoteSlave.java 1925 2009-06-15 21:46:05Z tdsoul $
 */
public class DummyRemoteSlave extends RemoteSlave {
    public DummyRemoteSlave(String name) {
        super(name);
    }

	public int getPort() {
		return 10;
	}
	
    public void fakeConnect() {
        _errors = 0;
        _lastNetworkError = System.currentTimeMillis();
    }
}
