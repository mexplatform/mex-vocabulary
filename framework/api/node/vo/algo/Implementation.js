/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function Implementation(className) {
    this._individualName = '';
    this._className = className;
    this._name = '';
    this._revision = '';
}
// class methods
Implementation.prototype.getIndividualName = function() {
    return this._individualName;
};
Implementation.prototype.getClassName = function() {
    return this._className;
};
Implementation.prototype.getName = function() {
    return this._name;
};
Implementation.prototype.getRevision = function() {
    return this._revision;
};
Implementation.prototype.setName = function(value) {
    this._name = value;
};
Implementation.prototype.setRevision = function(value) {
    this._revision = value;
};
Implementation.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
// export the class
module.exports = Implementation;
