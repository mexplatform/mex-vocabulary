/**
 * Created by esteves on 09.07.15.
 */
var train_size;
var test_size;
var name;
var Util = require('../../util/mexconstant.js');

module.exports = function() {

    this.get_train_size = function() {
        return this.train_size;
    }

    this.get_test_size = function(){
        return this.test_size;
    }
    this.get_name = function() {
        return this.name;
    }

    this.set_name = function(value){
        this.name = value;
    }
    this.set_train_size = function(value){
        this.train_size = value;
    }
    this.set_test_size = function(value){
        this.test_size = value;
    }




};
