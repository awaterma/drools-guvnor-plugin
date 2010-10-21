package org.drools.guvnor.plugin.data;

/**
 * Created by IntelliJ IDEA.
 * User: awaterma
 * Date: Oct 21, 2010
 * Time: 9:13:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class POJODataModel extends AbstractDataModel {

    @Override
    public String toString() {
        return "PojoDataModel{" +
            "artifactId='" + getArtifactId() + '\'' +
            ", groupId='" + getGroupId() + '\'' +
            '}';
    }

}
