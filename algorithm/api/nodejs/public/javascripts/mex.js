/**
 * Created by esteves on 06.07.15.
 */

function MEX () {
    this.applicationContext = '';
    this.color = "red";
    this.app.name = '';
    this.app.email = '';
}

MEX.prototype.setApplicationContext = function(name, email) {
    this.app.name = name;
    this.app.email = email;
};

MEX.prototype.getApplicationContext = function() {
    return 'name: ' + this.app.name + ' email: ' + this.app.email;
};


function addApplicationContext(){


    return true;
}