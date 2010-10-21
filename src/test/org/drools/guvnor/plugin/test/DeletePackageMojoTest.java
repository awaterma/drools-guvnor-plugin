package org.drools.guvnor.plugin.test;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.drools.guvnor.plugin.CreatePackageMojo;
import org.drools.guvnor.plugin.DeletePackageMojo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: awaterma
 * Date: Oct 21, 2010
 * Time: 11:40:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeletePackageMojoTest  extends AbstractMojoTestCase {
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
    public void testDeletePackageGoal() throws Exception
    {
        File testPom = new File( getBasedir(), "src/test/resources/testPom.xml" );
        DeletePackageMojo mojo = (DeletePackageMojo) lookupMojo ( "deletePackage", testPom );
        assertNotNull( mojo );
    }

    @After
    protected void tearDown() throws Exception {
        super.tearDown();

    }
}
