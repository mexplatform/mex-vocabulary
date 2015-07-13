/**
 * Created by esteves on 12.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function Hardware() {
    this._individualName = '';
    this._className = Util.DEF_CLASSES.MEX_CORE.HARDWARE_CONFIGURATION;
    this._os = '';
    this._cpu = '';
    this._memory = '';
    this._hd = '';
    this._cpucache = '';
    this._video = '';
}
// class methods
Hardware.prototype.getIndividualName = function() {
    return this._individualName;
};
Hardware.prototype.getClassName = function() {
    return this._className;
};
Hardware.prototype.getOperationalSystem = function() {
    return this._os;
};
Hardware.prototype.getCPU = function() {
    return this._cpu;
};
Hardware.prototype.getMemory = function() {
    return this._memory;
};
Hardware.prototype.getHD = function() {
    return this._hd;
};
Hardware.prototype.getCPUCache = function() {
    return this._cpucache;
};
Hardware.prototype.getVideo = function() {
    return this._video;
};

Hardware.prototype.setOperationalSystem = function(value) {
    this._os = value;
};
Hardware.prototype.setCPU = function(value) {
    this._cpu = value;
};
Hardware.prototype.setMemory = function(value) {
    this._memory = value;
};
Hardware.prototype.setHD = function(value) {
    this._hd = value;
};
Hardware.prototype.setCPUCache = function(value) {
    this._cpucache = value;
};
Hardware.prototype.setVideo = function(value) {
    this._video = value;
};




Hardware.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
// export the class
module.exports = Hardware;
