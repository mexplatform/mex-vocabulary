package org.aksw.mex.framework;

import org.aksw.mex.util.MEXEnum;

/**
 * Created by dnes on 02/01/16.
 */
public class ExecutionHelperKey {
    private String str1;
    private MEXEnum.EnumPhases str2;

    public ExecutionHelperKey(String s1, MEXEnum.EnumPhases s2){
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
        return (str1 + str2.name()).hashCode();
    }

    public String algorithmID(){
        return this.str1;
    }

    public MEXEnum.EnumPhases phaseID(){
        return this.str2;
    }

}
