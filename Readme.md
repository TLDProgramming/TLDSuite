#TLDSuite
##Overview
Complete collection of TLD plugins for the Spout voxel game platform
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
            <artifactId>spoutapi</artifactId>
            <version>dev-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- TLDCommonlib -->
        <dependency>
            <groupId>com.github.thelonedevil</groupId>
            <artifactId>TLDCommonlib</artifactId>
            <version>1.3-SNAPSHOT</version>
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
