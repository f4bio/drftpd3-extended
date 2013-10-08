#!/bin/sh
# $Id: build.sh 1925 2009-06-15 21:46:05Z tdsoul $
$ANT_HOME/bin/ant -buildfile installer.xml build
LIBS=`echo ./lib/*.jar | tr ' ' ':'`
$JAVA_HOME/bin/java -cp "$LIBS:$JAVA_HOME/lib/tools.jar" -Djava.library.path=lib -Dlog4j.configuration=file:log4j-build.properties -Dincludes="src/*/plugin.xml,src/plugins/*/plugin.xml" org.drftpd.tools.installer.Wrapper
