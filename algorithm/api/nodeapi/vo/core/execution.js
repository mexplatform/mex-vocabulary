/**
 * Created by esteves on 10.07.15.
 */
var Util = require('../../util/mexconstant.js');
var id;
var type;
var phase;
var algorithmID;
var _hyperparamIDList = [];

module.exports = function(id, type, phase, algorithm) {

    this.id = id;
    this.type = type;
    this.phase =
    this.algorithmID = algorithm;

    this.get_id = function() {
        return this.id;
    }

    this.set_id = function(value){
        this.id = value;
    }

    this.get_type = function() {
        return this.type;
    }

    this.set_type = function(value){
        this.type = value;
    }

    this.add_hyperparameter = function(value){
        _hyperparamIDList.push(value);
    }

};