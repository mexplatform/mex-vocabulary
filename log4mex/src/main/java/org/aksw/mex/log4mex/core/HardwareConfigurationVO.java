package org.aksw.mex.log4mex.core;

import org.aksw.mex.util.MEXEnum;

/**
 * Created by esteves on 26.06.15.
 */
public class HardwareConfigurationVO {

    private String _os;
    private String _cpu;
    private String _memory;
    private String _hd;
    private String _cache;
    private String _video;


    public HardwareConfigurationVO(MEXEnum.EnumProcessors cpu, MEXEnum.EnumRAM memory, MEXEnum.EnumCaches cache){
        this._cache =cache.toString();
        this._memory = memory.toString();
        this._cpu=cpu.toString();
    }

    public HardwareConfigurationVO(){

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

}
