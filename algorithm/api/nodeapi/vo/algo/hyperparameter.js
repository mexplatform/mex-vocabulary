/**
 * Created by esteves on 10.07.15.
 */
var id;
var value;
var individualName;
var className;
var Util = require('../../util/mexconstant.js');

module.exports = function(instance, className, id, value) {

    this.individualName = instance;
    this.className = className;
    this.id = id;
    this.value = value;

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
