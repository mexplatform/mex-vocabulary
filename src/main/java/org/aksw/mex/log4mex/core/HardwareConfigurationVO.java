package org.aksw.mex.log4mex.core;

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

    public HardwareConfigurationVO(String cpu, String memory, String hd, String cache, String OS, String video){
        this._os = OS;
        this._cache =cache;
        this._memory = memory;
        this._hd=hd;
        this._video=video;
        this._cpu=cpu;
    }

    public HardwareConfigurationVO(String cpu, String memory, String cache){
        this._cache =cache;
        this._memory = memory;
        this._cpu=cpu;
    }

    public HardwareConfigurationVO(){

    }

    public void setOperationalSystem(String value){
        this._os = value;
    }
    public void setMemory(String value){
        this._memory = value;
    }
    public void setCPU(String value){
        this._cpu = value;
    }
    public void setHD(String value){
        this._hd = value;
    }
    public void setCache(String value){
        this._cache = value;
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


}
