/**
 * Created by esteves on 23.07.15.
 */
var io = require('socket.io').listen(8123);

io.sockets.on('connection', function (socket) {
    socket.emit('for_client', { someData: 'if necessary' });
    socket.on('for_server', function(data) {
        doSomethingServerSide(data);
    });
});

function doSomethingServerSide(data){ console.log(data); }