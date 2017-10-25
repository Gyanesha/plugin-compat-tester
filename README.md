Generate a compatibility matrix for plugins against Jenkins core.

See https://wiki.jenkins-ci.org/display/JENKINS/Plugin+Compatibility+Tester for background.

To do (refile in `plugin-compat-tester` in JIRA!):

1. `InternalMavenRunner` currently still seems to run `install` goal which is very undesirable on release tags
1. should run `surefire-report:report` goal instead (or `surefire-report:report-only` after) and display link to HTML results from index page
1. Export *everything* to GAE, dropping the data storing in XML files (which pollutes the filesystem and can be easily delete if we are careless) and processing with XSL. (migration already started to GAE datastorage, but not completely finished, especially on build logs). (jglick: this is undesirable, need to be able to review local results without uploading them)
1. Improve GAE app to allow plugin maintainers to subscribe to notifications on plugin compatibility tests for their plugins against new jenkins versions released.
1. Remove possibility, on GAE app, to select both "every plugins" and "every cores" results... because it generates too much results and crash GAE datastore
1. most plugin tests fail to build using internal Maven; `PlexusWagonProvider.lookup` with a `roleHint=https` fails for no clear reason, and some missing `SNAPSHOT`s cause a build failure related to https://github.com/stapler/stapler-adjunct-codemirror/commit/da995b03a1f165fef7c9d34eadb15797f58399cd
1. testing a module not at the root of its Git repo fails (`findbugs` succeeds but tests against old Jenkins core)
1. testing `analysis-core` fails because it uses `org.jvnet.hudson.plugins:analysis-pom` as a parent
1. when testing a plugin depending on other plugins, bump up the dependency to the latest released version…or even build the dependency from `master` sources
