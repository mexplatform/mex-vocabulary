package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.InstanceObjects;
import org.aksw.mex.util.MEXEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by esteves on 26.06.15.
 */
public class HardwareConfigurationVO extends InstanceObjects {

    private String _os = "";
    private String _cpu = "";
    private String _memory = "";
    private String _hd = "";
    private String _cache = "";
    private String _video = "";


    public HardwareConfigurationVO(MEXEnum.EnumProcessors cpu, MEXEnum.EnumRAM memory, MEXEnum.EnumCaches cache){
        this._cache =cache.toString();
        this._memory = memory.toString();
        this._cpu=cpu.toString();
        this.setLabel("Hardware configuration");
    }

    public HardwareConfigurationVO(){
        this.setLabel("Hardware configuration");
    }

    public void setOperationalSystem(String value){
        this._os = value;
    }

    public void setMemory(MEXEnum.EnumRAM value){
        this._memory = value.toString();
    }

    public void setCPU(MEXEnum.EnumProcessors value){
        this._cpu = value.toString();
    }

    public void setHD(String value){
        this._hd = value;
    }

    public void setCache(MEXEnum.EnumCaches value){
        this._cache = value.toString();
    }

    public void setVideoGraph(String value){
        this._video = value;
    }

    public String getOs() {return _os;}

    public String getCPU() {return _cpu;}

    public String getMemory() {return _memory;}

    public String getHD() {return _hd;}

    public String getCache() {return _cache;}

    public String getVideo() {return _video;}

    public boolean hasValue(){

        if ((this._os != null && !this._os.isEmpty()) ||
            (this._video != null && !this._video.isEmpty()) ||
                (this._cpu != null && !this._cpu.isEmpty()) ||
                (this._memory != null && !this._memory.isEmpty()) ||
                (this._hd != null && !this._hd.isEmpty()) ||
                (this._cache != null && !this._cache.isEmpty())){

            return true;

        }else{

            return false;
        }

    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof HardwareConfigurationVO)) {
            return false;
        }

        HardwareConfigurationVO that = (HardwareConfigurationVO) other;

        return this._os.equals(that._os)
                && this._cpu.equals(that._cpu)
                && this._cache.equals(that._cache)
                && this._video.equals(that._video)
                && this._memory.equals(that._memory)
                && this._hd.equals(that._hd);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        if (StringUtils.isNotEmpty(this._os) && StringUtils.isNotBlank(this._os))
            hashCode = hashCode * 37 + this._os.hashCode();

        if (StringUtils.isNotEmpty(this._cpu) && StringUtils.isNotBlank(this._cpu))
            hashCode = hashCode * 37 + this._cpu.hashCode();

        if (StringUtils.isNotEmpty(this._cache) && StringUtils.isNotBlank(this._cache))
            hashCode = hashCode * 37 + this._cache.hashCode();

        if (StringUtils.isNotEmpty(this._video) && StringUtils.isNotBlank(this._video))
            hashCode = hashCode * 37 + this._video.hashCode();

        if (StringUtils.isNotEmpty(this._memory) && StringUtils.isNotBlank(this._memory))
            hashCode = hashCode * 37 + this._memory.hashCode();

        if (StringUtils.isNotEmpty(this._hd) && StringUtils.isNotBlank(this._hd))
            hashCode = hashCode * 37 + this._hd.hashCode();

        return hashCode;
    }


}
