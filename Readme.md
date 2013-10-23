#TLDSuite

##Overview
Complete collection of TLD plugins for the Spout voxel game platform
***

###License
This software is licensed under the LGPL version 3, a copy of which is included in License.txt
***

###Binary Downloads
https://drone.io/github.com/TLDProgramming/TLDSuite/files
[![Build Status](https://drone.io/github.com/TLDProgramming/TLDSuite/status.png)](https://drone.io/github.com/TLDProgramming/TLDSuite/latest)
***

###Modules
* TLDCommonlib
* TLDAgeChecker
* TLDAfk
* TLDFactions
* TLDSpells
* TLDRules
* TLDNotes
* TLDQuotes
* TLDWelcomer
* TLDReserve


***
####TLDCommonlib
#####Features
* TODO
 

#####Using in development
To use in your own plugin devlopment add this to your pom.xml

```XML
    <!-- Repositories -->
    <repositories>

        <!-- Spout's Maven Repository -->
        <repository>
            <id>spout-repo</id>
            <url>http://nexus.spout.org/content/groups/public</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
        
        <!-- TLD's Maven Repository  -->
        <repository>
            <id>TLDcommonlib</id>
            <url>http://nexus.justin-wiblin.tk:8081</url>
        </repository>

    </repositories>

    <!-- Dependencies -->
    <dependencies>

        <!-- The Spout API -->
        <dependency>
            <groupId>org.spout</groupId>
            <artifactId>spout-api</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- TLDCommonlib -->
        <dependency>
            <groupId>com.github.thelonedevil</groupId>
            <artifactId>TLDCommonlib</artifactId>
            <version>1.5-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>
```
***
####TLDAgeChecker
#####Features
* TODO


***
####TLDAfk
#####Features
* TODO


***
####TLDFactions
#####Features
* TODO


***
####TLDSpells
#####Features
* TODO


***
####TLDRules
#####Features
* TODO


***
####TLDNotes
#####Features
* TODO


***
####TLDQuotes
#####Features
* TODO


***
####TLDWelcomer
#####Features
* TODO


***
####TLDReserve
#####Features
* TODO


***
