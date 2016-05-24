package org.aksw.mex.log4mex.algo;

import org.aksw.mex.log4mex.InstanceObjects;
import org.aksw.mex.util.MEXEnum;

/**
 * Created by esteves on 08.06.15.
 */
public class ToolVO extends InstanceObjects {

    private String rdfs_label = "";
    private String doap_name = "";
    private String doap_homepage = "";
    private String doap_revision = "";
    private String doap_description = "";
    private String doap_programming_language = "";

    public ToolVO(MEXEnum.EnumTools name){
        this.setIndividualName(name.toString());
    }

    public ToolVO(){}

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

    public void set(MEXEnum.EnumTools value) {
        this.setIndividualName(value.toString());
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

    public boolean hasValue(){

        if ((this.doap_name != null && !this.doap_name.isEmpty()) &&
                ((this.doap_description != null && !this.doap_description.isEmpty()) ||
                (this.doap_revision != null && !this.doap_revision.isEmpty()) ||
                (this.doap_programming_language != null && !this.doap_programming_language.isEmpty()) ||
                (this.doap_homepage != null && !this.doap_homepage.isEmpty()))) {
            return true;
        }else{
            return false;
        }

    }



    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ToolVO)) {
            return false;
        }

        ToolVO that = (ToolVO) other;

        return this.rdfs_label.equals(that.rdfs_label)
                && this.doap_name.equals(that.doap_name)
                && this.doap_homepage.equals(that.doap_homepage)
                && this.doap_revision.equals(that.doap_revision)
                && this.doap_description.equals(that.doap_description)
                && this.doap_programming_language.equals(that.doap_programming_language);

    }


    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.getIndividualName().hashCode();
        hashCode = hashCode * 37 + this.rdfs_label.hashCode();
        hashCode = hashCode * 37 + this.doap_name.hashCode();
        hashCode = hashCode * 37 + this.doap_homepage.hashCode();
        hashCode = hashCode * 37 + this.doap_revision.hashCode();
        hashCode = hashCode * 37 + this.doap_description.hashCode();
        hashCode = hashCode * 37 + this.doap_programming_language.hashCode();


        return hashCode;
    }


}
