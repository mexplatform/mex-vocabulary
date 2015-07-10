/**
 * Created by esteves on 09.07.15.
 */
var name;
var email;
var context;

module.exports = function() {

    
    this.get_name = function() {
        return this.name;
    }
    
    this.get_email = function() {
        return this.email;
    }

    this.get_context = function() {
        return this.context;
    }

    this.set_name = function(name){
    	this.name = name;
    }

    this.set_email = function(email){
    	this.email = email;
    }

    this.set_context = function(value){
        this.context = value;
    }
};