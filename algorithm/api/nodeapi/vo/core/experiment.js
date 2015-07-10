/**
 * Created by esteves on 09.07.15.
 */
var id;
var Util = require('../../util/mexconstant.js');

module.exports = function() {

    this.id = Util.DEF_ID_EXP;

    this.get_id = function() {
        return this.id;
    }

    this.get_description = function() {
        return this.description;
    }

    this.get_date = function() {
        return this.date;
    }

    this.set_id = function(value){
        this.id = value;
    }

    this.set_description = function(value){
        this.description = value;
    }

    this.set_date = function(value){
        this.date = value;
    }
};