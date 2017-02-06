package org.jenkins.tools.test.model.hook;

/**
 * An abstract class that marks a hook that runs before the compilation stage of the
 * Plugin Compat Tester.
 *
 * This exists simply for the ability to check when a subclass should be implemented.
 */
public abstract class PluginCompatTesterHookBeforeExecution implements PluginCompatTesterHook {
}