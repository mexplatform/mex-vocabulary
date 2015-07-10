/**
 * Created by esteves on 09.07.15.
 */
var instance;
var id;
var value;
var Util = require('../../util/mexconstant.js');

module.exports = function(id, value) {

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
