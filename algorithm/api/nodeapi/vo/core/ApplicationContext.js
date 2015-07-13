/**
 * Created by esteves on 09.07.15.
 */
var util = require('../../util/mexconstant.js');
var clsContext = require('./Context.js');

// constructor
function ApplicationContext(context) {
    this._individualName = util.DEF_INDIVIDUALS.APPLICATION_CONTEXT;
    this._className      = util.DEF_CLASSES.MEX_CORE.APPLICATION_CONTEXT;
    this._userName = '';
    this._userEmail = '';
    this._context = new clsContext(context);
}
// class methods
ApplicationContext.prototype.getIndividualName = function() {
    return this._individualName;
};
ApplicationContext.prototype.getClassName = function() {
    return this._className;
};
ApplicationContext.prototype.setClassName = function(value) {
    this._className = value;
};

ApplicationContext.prototype.getUserName = function() {
    return this._userName;
};
ApplicationContext.prototype.setUserName = function(value) {
    this._userName = value;
};
ApplicationContext.prototype.getUserEmail = function() {
    return this._userEmail;
};
ApplicationContext.prototype.setUserEmail = function(value) {
    this._userEmail = value;
};
ApplicationContext.prototype.getContext = function() {
    return this._context;
};
// export the class
module.exports = ApplicationContext;
