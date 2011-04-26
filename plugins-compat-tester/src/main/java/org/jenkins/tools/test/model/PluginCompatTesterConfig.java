package org.jenkins.tools.test.model;

import java.io.File;
import java.util.List;

public class PluginCompatTesterConfig {

    // Update center used to retrieve plugins informations
    public final String updateCenterUrl;

    // GroupId which will be used to replace tested plugin's parent groupId
    public final String parentGroupId;

    // ArtifactId which will be used to replace tested plugin's parent artifactId
    public final String parentArtifactId;

    // A working directory where will be checkouted tested plugin's sources
    public final File workDirectory;

    // A report file where will be generated testing report
    // If the file already exist, testing report will be merged into it
    public final File reportFile;

    // Path for maven settings file where repository will be provided allowing to
    // download jenkins-core artefact (and dependencies)
    private final File m2SettingsFile;

    // Version which will be used to replace tested plugin's parent verison
    // If null, latest core version (retrieved via the update center) will be used
    private String parentVersion = null;

    // List of plugin artefact id on which tests will be performed
    // If null, tests will be performed on every plugins retrieved from update center
    private List<String> pluginsList = null;

    public PluginCompatTesterConfig(File workDirectory, File reportFile, File m2SettingsFile){
        this("http://updates.jenkins-ci.org/update-center.json?version=build", "org.jenkins-ci.plugins:plugin",
                workDirectory, reportFile, m2SettingsFile);
    }

    public PluginCompatTesterConfig(String updateCenterUrl, String parentGAV,
                                    File workDirectory, File reportFile, File m2SettingsFile){
        this.updateCenterUrl = updateCenterUrl;
        String[] gavChunks = parentGAV.split(":");
        assert gavChunks.length == 3 || gavChunks.length == 2;
        this.parentGroupId = gavChunks[0];
        this.parentArtifactId = gavChunks[1];
        if(gavChunks.length == 3){
            this.setParentVersion(gavChunks[2]);
        }
        this.workDirectory = workDirectory;
        this.reportFile = reportFile;
        this.m2SettingsFile = m2SettingsFile;
    }

    public String getParentVersion() {
        return parentVersion;
    }

    public void setParentVersion(String parentVersion) {
        this.parentVersion = parentVersion;
    }

    public List<String> getPluginsList() {
        return pluginsList;
    }

    public void setPluginsList(List<String> pluginsList) {
        this.pluginsList = pluginsList;
    }

    public File getM2SettingsFile() {
        return m2SettingsFile;
    }
}
