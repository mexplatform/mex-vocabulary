/**
 * Created by esteves on 09.07.15.
 */

var className;
var instanceName;
var userID;
var Util = require('../../util/mexconstant.js');

module.exports = function(instanceName, className, userID) {
    this.instanceName = instanceName;
    this.className = className;
    this.userID = userID;

    this.get_id = function() {
        return this.id;
    }

    this.set_id = function(value){
        this.id = value;
    }

    this.get_value = function() {
        return this.value;
    }

    this.set_value = function(value){
        this.value = value;
    }
};
