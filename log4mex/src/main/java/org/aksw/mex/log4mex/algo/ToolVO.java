package org.aksw.mex.log4mex.algo;

import org.aksw.mex.util.MEXEnum;

import java.util.List;

/**
 * Created by esteves on 08.06.15.
 */
public class ToolVO {

    private String _individualName;
    private String rdfs_label;
    private String doap_name;
    private String doap_homepage;
    private String doap_revision;
    private String doap_description;
    private String doap_programming_language;
    private List<ToolParameterVO> _parameters;

    public ToolVO(MEXEnum.EnumTools name){
        this._individualName = name.toString();
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

    public String get() {
        return _individualName;
    }

    public void set(MEXEnum.EnumTools value) {
        this._individualName = value.toString();
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

    public List<ToolParameterVO> getParameters(){
        return this._parameters;
    }

}
