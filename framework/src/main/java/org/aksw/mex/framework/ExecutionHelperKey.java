package org.aksw.mex.framework;

/**
 * Created by dnes on 02/01/16.
 */
public class ExecutionHelperKey {
    private String str1;
    private String str2;

    public ExecutionHelperKey(String s1, String s2){
        this.str1 = s1;
        this.str2 = s2;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof ExecutionHelperKey) {
            ExecutionHelperKey s = (ExecutionHelperKey)obj;
            return str1.equals(s.str1) && str2.equals(s.str2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (str1 + str2).hashCode();
    }

    public String algorithmID(){
        return this.str1;
    }

    public String phaseID(){
        return this.str2;
    }

}
