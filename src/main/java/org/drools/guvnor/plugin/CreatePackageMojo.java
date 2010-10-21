package org.drools.guvnor.plugin;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.jackrabbit.webdav.client.methods.PutMethod;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.MojoExecutionException;
import org.drools.guvnor.plugin.data.AbstractDataModel;
import org.drools.guvnor.plugin.data.POJODataModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * "createPackage" goal which deploys configured artifacts into the Drools Guvnor
 * server specified by the URL, "url".
 *
 * What is a package?  Packages consits of resources (a list of build path values,
 * generally pointing to rule-flows, drools-rules-files and dsl mappings) and
 * data models, defined as bindable artifacts in a maven context.
 *
 * WEBDAV repository.
 *
 * @goal createPackage
 * 
 * @phase process-sources
 */
public class CreatePackageMojo extends AbstractGuvnorMojo {
    
    private Set<Artifact> resolvedModels;

    public void execute() throws MojoExecutionException {

        /* First, load all defined models from the repository */
        Set<Artifact> artifacts = project.getArtifacts();

        /* Filter out resolved models from project artifacts */
        try {
            for (AbstractDataModel model : models) {
                if (model instanceof POJODataModel) {
                    for (Artifact artifact : artifacts) {
                        if (artifact.getArtifactId().equals(model.getArtifactId()) &&
                                artifact.getGroupId().equals(model.getGroupId())) {
                            resolvedModels.add(artifact);
                            break;
                        }
                    }
                }
            }

            PutMethod method = null;

            HttpClient client = new HttpClient();
            Credentials credentials = new UsernamePasswordCredentials(username, password);
            client.getState().setCredentials(AuthScope.ANY, credentials);
            method = new PutMethod(guvnorURL.toExternalForm() + "/" + packageName);
            client.executeMethod(method);

            if (!method.succeeded())
                    throw new MojoExecutionException ("PutMethod failed!");

            /* Now that the package has been created, push up the resources and models */
            for (String resource : resources) {
                File f = new File (resource);
                if (!f.exists())
                    throw new MojoExecutionException ("Bad resource: " + resource);
                RequestEntity requestEntity = new InputStreamRequestEntity(new FileInputStream(f));
                method.setRequestEntity(requestEntity);
                client.executeMethod(method);
                if (!method.succeeded())
                    throw new MojoExecutionException ("PutMethod failed!");
            }

            for (Artifact artifact : resolvedModels) {
                File f = artifact.getFile();
                if (!f.exists())
                    throw new MojoExecutionException ("Bad model: " + artifact.toString());                
                RequestEntity requestEntity = new InputStreamRequestEntity(new FileInputStream(f));
                method.setRequestEntity(requestEntity);
                client.executeMethod(method);
                if (!method.succeeded())
                    throw new MojoExecutionException ("PutMethod failed!");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } 
    }
}
