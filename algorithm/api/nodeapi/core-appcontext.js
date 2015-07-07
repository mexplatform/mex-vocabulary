module.exports = function(name, email) {

    this.name = name;
    this.email = email;
    
    this.get_name = function() {
        return this.name;
    }
    
    this.get_email = function() {
        return this.email;
    }

    this.set_name = function(name){
    	this.name = name;
    }

    this.set_email = function(email){
    	this.email = email;
    }
};