package org.drools.guvnor.plugin;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * "compilePackage" goal which compiles all artifacts in the package, "package"
 * at the Guvnor instance defined by URL "url".
 *
 * @goal compilePackage
 *
 * @phase process-sources
 */
public class CompilePackageMojo extends AbstractGuvnorMojo {

    public void execute() throws MojoExecutionException {
        
    }
}
