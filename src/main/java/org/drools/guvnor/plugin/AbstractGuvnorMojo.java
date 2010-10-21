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
 */
public abstract class AbstractGuvnorMojo extends AbstractMojo  {

    public void configure(AbstractGuvnorMojo mojo) {
        this.project = mojo.project;
        this.guvnorURL = mojo.guvnorURL;
        this.username = mojo.username;
        this.password = mojo.password;
        this.packageName= mojo.packageName;
        this.resources = mojo.resources;
        this.models = mojo.models;
    }

    /**
    * The maven project.
    *
    *  @required 
    *  @parameter
    */
    public MavenProject project;

    /*  @required
        @parameter */
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

}
