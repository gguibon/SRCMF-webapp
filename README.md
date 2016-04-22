SRCMF-webapp
===============

This webapp is a graphical interface to use the SRCMF-api grpahically. It allows the user to predicted lemma, part of speech tags, and syntax of Old french texts. 

# Build

This repo is built using maven, and developped using eclipse. To facilitate the build I left the eclipse project AND the maven files. In order to build it, please use:

```
mvn clean compile
mvn package
```

Or just import the project in Eclipse using the maven Eclipse plugin.

This will result in a .war which could be put in any apache tomcat server.

# Usage

Put the resulting .war into you work folder of Tomcat server. Launch it and open in your web browser.

# Contacts

gael dot guibon at gmail.com

@2016 SRCMF LaTTiCe-CNRS
