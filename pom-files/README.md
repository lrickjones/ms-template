# Microservice POM Files
These are the starter poms and dependency poms for microservice projects.

Using these pom files will help ensure we are using common libraries and approved versions.

The root pom is used for building and installing the pom files

mvn clean install will install the pom files to the local cache

mvn clean deploy will install the pom files to artifactory as designated in the root pom:

    <!-- Manage where artifacts are sent on mvn deploy -->
    <distributionManagement>
        <repository>
            <id>releases</id>
            <name>libs-releases</name>
            <url>https://norcas.accessline.com/artifactory/libs-release</url>
        </repository>
        <snapshotRepository>
            <id>snapshots</id>
            <name>libs-snapshots</name>
            <url>https://norcas.accessline.com/artifactory/libs-snapshot</url>
        </snapshotRepository>
    </distributionManagement>

Projects should use im.vbo.service as the parent pom for microservices and im.vbo.library for shared utilities
