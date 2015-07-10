/**
 * Created by esteves on 09.07.15.
 */
var Util = require('../../util/mexconstant.js');

var _id;
var _dataset = require('./dataset.js');
var _sampling = require('./sampling.js');
var _feature = require('./feature.js');
var _algorithm = require('./algorithm.js');
var _softwareImplementation = require('./../algo/implementation.js');
var _featureList = [];
var _algorithmList = [];

module.exports = function(id) {

    this.id = id;

    this.get_id = function() {
        return this.id;
    }

    this.set_id = function(value){
        this.id = value;
    }

    this.get_dataset = function() {
        return _dataset;
    }
    this.get_implementation = function() {
        return _softwareImplementation;
    }
    this.get_sampling = function() {
        return _sampling;
    }

    this.get_feature = function(id) {
        for (var i = 0; i < _featureList.length; i++) {
            if(_featureList[i].get_id() == id){
                return _featureList[i];
            }
        }
        return null;
    }
    this.get_features = function() {
      return _featureList;
    }

    this.add_feature = function (id, name) {
        var f = new _feature(id, name);
        _featureList.push(f);
    }

    this.add_algorithm = function (algorithm) {
        var a = new _algorithm(algorithm);
        _algorithmList.push(a);
    }

    this.add_execution = function (executiontype, phase) {
        var a = new _algorithm(algorithm);
        _algorithmList.push(a);
    }
};

