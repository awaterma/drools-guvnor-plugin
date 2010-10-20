package org.drools.guvnor.plugin;

/**
 * Created by IntelliJ IDEA.
 * User: awaterma
 * Date: Oct 20, 2010
 * Time: 1:57:43 PM
 * To change this template use File | Settings | File Templates.
 */

public class DataModel {

    /* @parameter property="artifactId" */
    private String _artifactId;

    /* @parameter property="groupId" */
    private String _groupId;

    /* @parameter property="version" */
    private  Number _version;

    public String getArtifactId() {
        return _artifactId;
    }

    public void setArtifactId(String _artifactId) {
        this._artifactId = _artifactId;
    }

    public String getGroupId() {
        return _groupId;
    }

    public void setGroupId(String _groupId) {
        this._groupId = _groupId;
    }

    public Number getVersion() {
        return _version;
    }

    public void setVersion(Number _version) {
        this._version = _version;
    }

    @Override
    public String toString() {
        return "DataModel{" +
            "artifactId='" + _artifactId + '\'' +
            ", groupId='" + _groupId + '\'' +
            ", version=" + _version +
            '}';
    }
}
