/**
 * Created by esteves on 23.07.15.
 */
var http = require('http'),
    fs = require('fs'),
    port = 8000;

fs.readFile('./index.html', function (err, html) {
    if (err) {
        throw err;
    }
    http.createServer(function(request, response) {
        response.writeHeader(200, {"Content-Type": "text/html"});
        response.write(html);
        response.end();
    }).listen(port);
});

console.log('*********************************************');
console.log('MEX Platform: server started - port:' + port);
console.log('*********************************************');