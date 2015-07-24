var express = require('express'),
    list = require('./request.js').Request; // see  template

var app = express.createServer();

app.use(express.static(__dirname + '/public')); // exposes index.html, per below

app.get('/request', function(req, res){
    // run your request.js script
    // when index.html makes the ajax call to www.yoursite.com/request, this runs
    // you can also require your request.js as a module (above) and call on that:
    res.send(list.getList()); // try res.json() if getList() returns an object or array
});

app.listen(8088);