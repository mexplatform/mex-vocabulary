var app = require('http').createServer(index), 
    io = require('socket.io').listen(app), 
    fs = require('fs');

app.listen(8444, function() {
  console.log("MEX - Server started");
});

function index(req, res){
  fs.readFile(__dirname + '/contador.html', function(err, data){
	  res.writeHead(200);
    res.end(data);	
  });
};

// Iniciando Socket.IO
var visitas = 0;
// Evento connection ocorre quando entra um novo usuário.
io.on('connection', function(socket){
  // Incrementa o total de visitas no site.
  visitas++;
  // Envia o total de visitas para o novo usuário.
  socket.emit('visits', visitas);
  // Envia o total de visitas para os demais usuários.
  socket.broadcast.emit('visits', visitas);

  // Evento disconnect ocorre quando sai um usuário.
  socket.on('disconnect', function(){
    visitas--;
    // Atualiza o total de visitas para os demais usuários.
    socket.broadcast.emit('message', visitas);
  });

  io.on('callMethod', function(data) {
    if(data["methodName"] == "method3") {
        socket.emit('visits', visitas);
    }
    socket.emit('visits', visitas);

});
});