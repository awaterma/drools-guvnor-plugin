package org.drools.guvnor.plugin;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.jackrabbit.webdav.client.methods.DeleteMethod;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

/**
 * "deletePackage" mojo which deletes all artifacts in the package, "package"
 * at the Guvnor instance defined by URL "url".
 *
 * @goal deletePackage
 *
 * @phase process-sources
 */
public class DeletePackageMojo extends AbstractGuvnorMojo {
    
    public void execute() throws MojoExecutionException {
        HttpClient client = new HttpClient();
        Credentials creds = new UsernamePasswordCredentials(username, password);
        client.getState().setCredentials(AuthScope.ANY, creds);
        DeleteMethod method = new DeleteMethod(guvnorURL.toExternalForm() + "/" + packageName);

        try {
            client.executeMethod(method);
        } catch (IOException e) {
            throw new MojoExecutionException (e.getMessage());
        }

        if (!method.succeeded())
            throw new MojoExecutionException ("DeleteMethod failed!");
    }
}
