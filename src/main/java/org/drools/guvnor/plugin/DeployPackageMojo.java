package org.drools.guvnor.plugin;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * "deployPackage" mojo which deploys all artifacts to the package, "package"
 * at the Guvnor instance defined by URL "url".
 *
 * @goal deployPackage
 *
 * @phase package
 */

public class DeployPackageMojo extends AbstractGuvnorMojo {

    DeletePackageMojo delete = new DeletePackageMojo();
    CreatePackageMojo create = new CreatePackageMojo();
    CompilePackageMojo compile = new CompilePackageMojo();

    public void execute() throws MojoExecutionException {
        /* configure */
        delete.configure(this);
        create.configure(this);
        compile.configure(this);

        /* Deploy */
        delete.execute();
        create.execute();
        compile.execute();
    }
}
