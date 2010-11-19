package org.drools.guvnor.plugin;

import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.drools.guvnor.plugin.data.AbstractDataModel;

import java.net.URL;
import java.util.List;

/**
 * AbstractMojo class for drools-guvnor-plugin.
 *
 * @RequiresProject true
 */
public abstract class AbstractGuvnorMojo extends AbstractMojo  {

    /*
        REST API query string.
     */
    public final static String RestQuery = "/api/packages";

    /*
        REST Action query string.
     */
    public final static String ActionQuery = "/actions";

    /*
     *  @required
     *  @parameter expression="${project}"
     *  @readonly
    */
    public MavenProject project;    

    /*  @required
        @parameter

        The URL to Drools Guvnor. */
    public URL guvnorURL;

    /*  @required
        @parameter */
    public String username;

    /*  @required
        @parameter */
    public String password;

    /*  @required
        @parameter */
    public String packageName;

    /* @parameter */
    public List<String> resources;

    /* @parameter */
    public AbstractDataModel[] models;

    /*  Configure this project to reflect the common
        values set into the Abstraction instance.
     */
    public void configure(AbstractGuvnorMojo mojo) {
        this.guvnorURL = mojo.guvnorURL;
        this.username = mojo.username;
        this.password = mojo.password;
        this.packageName= mojo.packageName;
        this.resources = mojo.resources;
        this.models = mojo.models;
    }
}
