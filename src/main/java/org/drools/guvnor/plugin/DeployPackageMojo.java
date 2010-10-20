package org.drools.guvnor.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.net.URL;
import java.util.List;

/**
 * "deploy-package" goal which deploys configured artifacts into the Drools Guvnor
 * server specified by the URL, "url".
 *
 * What is a package?  Packages consits of resources (a list of build path values,
 * generally pointing to rule-flows, drools-rules-files and dsl mappings) and
 * data models, defined as bindable artifacts in a maven context.
 *
 * WEBDAV repository.
 *
 * @goal deploy-package
 * 
 * @phase process-sources
 */
public class DeployPackageMojo extends AbstractMojo
{
    
    /* @parameter */
    public URL guvnorURL;

    /* @parameter */
    public List resources;

    /* @parameter */
    public DataModel[] models;

    public void execute() throws MojoExecutionException {

        System.out.println ("guvnorURL: " + guvnorURL);
        for (Object obj : resources) {
            System.out.println ("resource: " + obj);
        }

        for (DataModel model : models) {
            System.out.println ("model: " + model);
        }
    }
}
