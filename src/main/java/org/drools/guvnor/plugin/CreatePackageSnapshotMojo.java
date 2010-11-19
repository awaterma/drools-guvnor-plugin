package org.drools.guvnor.plugin;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: awaterma
 * Date: Nov 19, 2010
 * Time: 12:59:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreatePackageSnapshotMojo extends AbstractGuvnorMojo {        

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        HttpClient client = new HttpClient();
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        client.getState().setCredentials(AuthScope.ANY, credentials);
        PostMethod method = new PostMethod(guvnorURL.toExternalForm() + RestQuery + "/" + packageName);
        try {
            client.executeMethod(method);
        } catch (IOException e) {
            throw new MojoFailureException (e.getMessage());
        }
    }
}
