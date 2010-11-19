package org.drools.guvnor.plugin;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.jackrabbit.webdav.Status;
import org.apache.jackrabbit.webdav.client.methods.PutMethod;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectBuilder;
import org.apache.maven.project.MavenProjectHelper;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.drools.guvnor.plugin.data.AbstractDataModel;
import org.drools.guvnor.plugin.data.POJODataModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
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
 * @requiresDependencyResolution
 *
 * @goal createPackage
 * 
 * @phase package
 */
public class CreatePackageMojo extends AbstractGuvnorMojo {

    public void execute() throws MojoExecutionException {
        Set<Artifact> resolvedModels = new HashSet<Artifact>();
        
        HttpClient client = new HttpClient();
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        client.getState().setCredentials(AuthScope.ANY, credentials);

        try {
            /* Artifact filter for defined models */
            if (models != null && models.length > 0) {
                Set<Artifact> artifacts = project.getArtifacts();
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
            }

            /* Create Package */
            PutMethod method = new PutMethod(guvnorURL.toExternalForm() + RestQuery + "/" + packageName);
            client.executeMethod(method);

            if (!method.succeeded())
                throw new MojoExecutionException (method.getResponseException().toString());

            for (String resource : resources) {
                File f = new File (resource);
                if (!f.exists())
                    throw new MojoExecutionException ("Bad resource: " + resource);
                RequestEntity requestEntity = new InputStreamRequestEntity(new FileInputStream(f));
                method = new PutMethod(guvnorURL.toExternalForm() + RestQuery + "/" + packageName + "/" + f.getName());
                method.setRequestEntity(requestEntity);
                client.executeMethod(method);
                if (!method.succeeded())
                    throw new MojoExecutionException (method.getResponseException().toString());
            }

            for (Artifact artifact : resolvedModels) {
                File f = artifact.getFile();
                if (!f.exists())
                    throw new MojoExecutionException ("Bad model: " + artifact.toString());
                RequestEntity requestEntity = new InputStreamRequestEntity(new FileInputStream(f));
                method = new PutMethod(guvnorURL.toExternalForm() + RestQuery + "/" + packageName);
                method.setRequestEntity(requestEntity);
                client.executeMethod(method);
                if (!method.succeeded())
                    throw new MojoExecutionException (method.getResponseException().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
