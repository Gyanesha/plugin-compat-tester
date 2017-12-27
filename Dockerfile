# The MIT License
#
#  Copyright (c) 2017 CloudBees, Inc.
#
#  Permission is hereby granted, free of charge, to any person obtaining a copy
#  of this software and associated documentation files (the "Software"), to deal
#  in the Software without restriction, including without limitation the rights
#  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
#  copies of the Software, and to permit persons to whom the Software is
#  furnished to do so, subject to the following conditions:
#
#  The above copyright notice and this permission notice shall be included in
#  all copies or substantial portions of the Software.
#
#  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
#  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
#  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
#  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
#  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
#  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
#  THE SOFTWARE.

FROM maven:3.5.2-jdk-8
LABEL Description="Base image for running Jenkins Plugin Compat Tester (PCT) against custom plugins and Jenkins cores" Vendor="Jenkins project"

# Local build
COPY plugins-compat-tester-cli/target/  /pct/src/plugins-compat-tester-cli/target/

#TODO: Uncomment once Jenkins Artifactory is recovered
# https://issues.jenkins-ci.org/browse/INFRA-1447
# Copy sources
#COPY plugins-compat-tester/ /pct/src/plugins-compat-tester/
#COPY plugins-compat-tester-cli/ /pct/src/plugins-compat-tester-cli/
#COPY plugins-compat-tester-gae/ /pct/src/plugins-compat-tester-gae/
#COPY plugins-compat-tester-gae-client/ /pct/src/plugins-compat-tester-gae-client/
#COPY plugins-compat-tester-model/ /pct/src/plugins-compat-tester-model/
#COPY *.xml /pct/src/
#COPY LICENSE.txt /pct/src/LICENSE.txt

#WORKDIR /opt/pct/src/
#RUN ls -l && mvn clean package

ENV JENKINS_WAR_PATH=/pct/jenkins.war
ENV PCT_OUTPUT_DIR=/pct/out
ENV PCT_TMP=/pct/tmp

COPY src/main/docker/run-pct.sh /usr/local/bin/run-pct

EXPOSE 5005

VOLUME /pct/plugin-src
VOLUME /pct/jenkins.war
VOLUME /pct/out
VOLUME /root/.m2
ENTRYPOINT ["run-pct"]
