package org.drools.guvnor.plugin.test;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.drools.guvnor.plugin.DeployPackageMojo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: awaterma
 * Date: Oct 21, 2010
 * Time: 3:19:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeployPackageMojoTest extends AbstractMojoTestCase {
    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Before
    protected void setUp() throws Exception {

        // required for mojo lookups to work
        super.setUp();
    }

    /**
     * @throws Exception
     */
    @Test
    public void testDeployPackageGoal() throws Exception
    {
        File testPom = new File( getBasedir(), "src/test/resources/testPom.xml" );
        DeployPackageMojo mojo = (DeployPackageMojo) lookupMojo ( "deployPackage", testPom );
        assertNotNull( mojo );
    }


    /** Not working yet, issues with MavenProject not being pushed into top-level mojo.
    @Test
    public void testDeployPackage () throws Exception {
        File testPom = new File( getBasedir(), "src/test/resources/testPom.xml" );
        DeployPackageMojo mojo = (DeployPackageMojo) lookupMojo ( "deployPackage", testPom );
        assertNotNull( mojo );
        mojo.execute();
    }
    */


    @After
    protected void tearDown() throws Exception {
        super.tearDown();

    }
}
