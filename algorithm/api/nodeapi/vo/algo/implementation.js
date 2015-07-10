/**
 * Created by esteves on 09.07.15.
 */
var id;
var name;
var revision;
var Util = require('../../util/mexconstant.js');

module.exports = function(id, value) {

    this.get_id = function() {
        return this.id;
    }

    this.set_id = function(value){
        this.id = value;
    }

    this.get_name = function() {
        return this.name;
    }

    this.set_name = function(value){
        this.name = value;
    }

    this.get_revision = function() {
        return this.revision;
    }

    this.set_revision = function(value){
        this.revision = value;
    }

};
