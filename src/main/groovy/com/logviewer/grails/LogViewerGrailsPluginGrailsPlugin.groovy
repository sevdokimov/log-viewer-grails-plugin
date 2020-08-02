package com.logviewer.grails

import com.logviewer.config.LogViewerAutoConfig
import com.logviewer.config.LvConfigBase
import com.logviewer.web.LogViewerServlet
import grails.plugins.Plugin
import org.springframework.boot.web.servlet.ServletRegistrationBean

class LogViewerGrailsPluginGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.2.0 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Log Viewer" // Headline display name of the plugin
    def author = "Sergey Evdokimov"
    def authorEmail = "sergey.evdokimov85@gmail.com"
    def description = '''\
Provides web UI to monitor application logs. 
Filtering / highlight / search is available. No problem with big files.
 
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "https://github.com/sevdokimov/log-viewer-grails-plugin"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    def issueManagement = [ system: "github", url: "https://github.com/sevdokimov/log-viewer-grails-plugin/issues" ]

    def scm = [ url: "https://github.com/sevdokimov/log-viewer-grails-plugin" ]

    Closure doWithSpring() {
        { ->
            logViewerBaseConfig(LvConfigBase.class) {

            }

            logViewerAutoConfig(LogViewerAutoConfig.class) {

            }

            logViewerServlet(ServletRegistrationBean.class) {
                servlet = new LogViewerServlet()
                asyncSupported = true
                name = "logViewerServlet"

                def logServletPath = application.config.getProperty("log-viewer.url-mapping", "/logs/*")
                if (!logServletPath.endsWith("*")) {
                    if (!logServletPath.endsWith("/"))
                        logServletPath += "/";

                    logServletPath += "*";
                }

                urlMappings = [logServletPath]
            }
        }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
