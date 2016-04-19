package org.aksw.mex.log4mex.algo;

/**
 * Created by esteves on 08.06.15.
 */
public class ImplementationVO {

    private String _individualName;
    private String rdfs_label;
    private String doap_name;
    private String doap_homepage;
    private String doap_revision;
    private String doap_description;
    private String doap_programming_language;
    //private ArrayList<String> _properties;

    ImplementationVO(String ind, String name, String label, String homepage, String revision, String description, String programming){
       this._individualName = ind;
        this.doap_name = name;
        this.rdfs_label = label;
        this.doap_homepage = homepage;
        this.doap_revision = revision;
        this.doap_description = description;
        this.doap_programming_language = programming;

    }
    public ImplementationVO(String name){
        this._individualName = name;
    }

    public ImplementationVO(){
       /* _properties = new ArrayList<String>();
        _properties.add("id");
        _properties.add("doap:name");
        _properties.add("doap:homepage");
        _properties.add("doap:revision");
        _properties.add("doap:description");
        _properties.add("doap:programming-language");
        _properties.add("rdfs:label"); */
    }

    public String getLabel() {
        return rdfs_label;
    }

    public void setLabel(String value) {
        this.rdfs_label = value;
    }

    public String getName() {
        return doap_name;
    }

    public void setName(String value) {
        this.doap_name = value;
    }


    //public ArrayList<String> getListOfProperty() {
    //   return _properties;
    //}

    /*public void setProperty(String prop, String value) {

        switch (prop) {
            case "id":
                this._individualName = value;
                break;
            case "doap:name":
                this.doap_name = value;
                break;
            case "rdfs:label":
                this.rdfs_label = value;
                break;
            case "doap:homepage":
                this.doap_homepage = value;
                break;
            case "doap:revision":
                this.doap_revision = value;
                break;
            case "doap:description":
                this.doap_description = value;
                break;
            case "doap:programming-language":
                this.doap_programming_language = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid property: " + prop);
        }



    }*/


    public String get() {
        return _individualName;
    }

    public void set(String value) {
        this._individualName = value;
    }

    public String getHomepage() {
        return doap_homepage;
    }

    public void setHomepage(String value) {
        this.doap_homepage = value;
    }


    public String getRevision() {
        return doap_revision;
    }

    public void setRevision(String value) {
        this.doap_revision = value;
    }

    public String getDescription() {
        return doap_description;
    }

    public void setDescription(String value) {
        this.doap_description = value;
    }

    public String getProgrammingLanguage() {
        return doap_programming_language;
    }

    public void setProgrammingLanguage(String value) {
        this.doap_programming_language = value;
    }
}
