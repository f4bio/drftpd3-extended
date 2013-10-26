
overview
========
* libs updated
* linux x64 libs/bins added
* paths in xmls updated

updated libs [lib/]
===========================
* ant-*.jar [ 1.8.4 -> 1.9.2 ]
* commons-compress-*.jar [ 1.3 -> 1.5 ]
* commons-net-*.jar [ 3.0.1 -> 3.3 ]
* junit-*.jar [ 4.8.2 -> 4.11 ]
* lucene-*.jar [ 3.2.0 -> 3.6.2 ]
* truezip/truezip-*.jar [ 7.4.3 -> 7.7.4 ]
* findbugs-annotations-*.jar [ 1.3.9 -> 2.0.2 ]

added/complied libs [lib/]
==================================
* wrapper[32|64].jar [ ia32/x64 - 3.5.21 ]
* libwrapper[32|64].so [ ia32/x64 - 3.5.21 ]
* libTerminal[32|64].so [ ia32/x64 - 1.1.4 ]

added/complied bins [bin/]
==================================	
* wrapper[32|64] [ ia32/x64 - 3.5.21 ]

notes
=====
* paths updated (in xml files)
* all extensions selected by default
* please link either ia32 or x64 lib/bin (like it is now)
	* e.g.:
	* $ cd bin/
	* $ ln -s wrappers/wrapper64 wrapper
	* $ cd ../lib
	* $ ln -s terminals/libTerminal64.so libTerminal.so

tested
======
* Ubuntu Server [ x64 - raring ]
* Arch [ x64 - 2013-09 ]
* Oracle Java [ x64 - 1.7.0_40 ]

sources / thanks
================
* DrFTPD
	* http://drftpd.org/
* Apache <3 (as always)
	* http://apache.org/
	* http://commons.apache.org
	* http://ant.apache.org/
	* http://lucene.apache.org/
* JUnit 4
	* http://junit.org/
* TrueZIP
	* https://truezip.java.net/
* FindBugs
	* http://findbugs.sourceforge.net/
* Pitman / Charva
	* http://www.pitman.co.za/
* Tanuk
	* http://wrapper.tanukisoftware.com/

more too come...
