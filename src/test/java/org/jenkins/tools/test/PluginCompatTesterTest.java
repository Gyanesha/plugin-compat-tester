package org.jenkins.tools.test;

import hudson.model.UpdateSite;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PluginCompatTesterTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		SCMManagerFactory.getInstance().start();
	}
	
	@After
	public void tearDown() throws Exception {
		SCMManagerFactory.getInstance().stop();
	}
	
	@Test
	public void testWithUrl() throws Throwable {
		PluginCompatTester tester = new PluginCompatTester("http://updates.jenkins-ci.org/update-center.json?version=build", 
				"org.jenkins-ci.plugins:plugin", testFolder.getRoot(), new File(testFolder.getRoot().getAbsolutePath()+"/report.xml"));

        List<String> includedPlugins = new ArrayList<String>(){{ /*add("scm-sync-configuration");*/ add("Schmant"); }};
		tester.testPlugins(includedPlugins);
	}
}
