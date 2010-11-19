package org.drools.guvnor.plugin.test;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.drools.guvnor.plugin.CreatePackageMojo;
import org.junit.*;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: awaterma
 * Date: Oct 21, 2010
 * Time: 9:50:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreatePackageMojoTest extends AbstractMojoTestCase {
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
    public void testCreatePackageGoal() throws Exception
    {
        File testPom = new File( getBasedir(), "src/test/resources/testPom.xml" );
        CreatePackageMojo mojo = (CreatePackageMojo) lookupMojo ( "createPackage", testPom );
        assertNotNull( mojo );
    }

    @After
    protected void tearDown() throws Exception {
        super.tearDown();

    }
}
