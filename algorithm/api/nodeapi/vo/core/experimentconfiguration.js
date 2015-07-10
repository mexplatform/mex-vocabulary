/**
 * Created by esteves on 09.07.15.
 */
var util = require('../../util/mexconstant.js');

var _individual;
var _dataset = require('./dataset.js');
var _sampling = require('./sampling.js');
var _feature = require('./feature.js');
var _algorithm = require('./../algo/algorithm.js');
var _hyperparameter = require('./../algo/hyperparameter.js');
var _execution = require('./execution.js');
var _softwareImplementation = require('./../algo/implementation.js');
var _featureList = [];
var _algorithmList = [];
var _executionList = [];
var _hyperparamList = [];

module.exports = function(id) {

    this._individual = util.DEF_IDENTIFIERS.EXP_CONFIGURATION;

    this.get_individualName = function() {
        return this._individual;
    }

    this.set_individualName = function(value){
        this._individual = value;
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
        var instance = util.DEF_INDIVIDUALS.ALGORITHM + (_algorithmList.length + 1);
        var a = new _algorithm(instance, algorithm);
        _algorithmList.push(a);
        return instance;
    }

    this.add_execution = function (executiontype, phase, algorithmID, hyperparam) {
        var instance = util.DEF_INDIVIDUALS.EXEC + (_executionList.length + 1);
        var className = executiontype;
        var a = new _execution(instance, className, executiontype, phase);
        _executionList.push(a);
    }
    this.add_hyperparameter = function (id, value) {
        var instance = util.DEF_INDIVIDUALS.HYPERPARAMETER + (_hyperparamList.length + 1);
        var className = util.DEF_CLASSES.MEX_ALGO.HYPERPARAMETER;
        var a = new _hyperparameter(instance, className, id, value);
        _hyperparamList.push(a);
        return instance;
    }
};

