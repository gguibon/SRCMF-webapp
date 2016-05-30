SOFA - Syntactic Old French Annotator
===============

![Logo by Kim Gerdes](http://sofa.ilpga.fr:8888/sofa/img/logo-KimGerdes/sofa200.png "Logo by Kim Gerdes")


SOFA is a web interface exploiting the [SRCMF-nlp API](https://github.com/gguibon/SRCMF-nlp-api "SRCMF nlp"). It allows the user to predicted lemma, part of speech tags, and syntax of Old french texts. 
It was done for the Syntactic Reference Corpus of Medieval French (SRCMF) project. [Here the website](http://srcmf.org/ "SRCMF's Homepage")

There is three related papers can be found here

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

# Licence

This program is under the AGPL licence. Please see the LICENCE file in the root directory

# Credits
The logo has been made by [Kim Gerdes](http://gerdes.fr/ "Kim Gerdes Website")

# Contacts

gael dot guibon at gmail.com

@2016 SRCMF LaTTiCe-CNRS
