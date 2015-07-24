/**
 * Created by esteves on 23.07.15.
 */
var http = require('http'),
    faye = require('faye');

var server = http.createServer(),
    bayeux = new faye.NodeAdapter({mount: '/'});

bayeux.attach(server);
server.listen(8787);

setInterval(function()
{
    var currentTime_secsSinceEpoch = new Date().getTime() / 1000;

    server.getClient().publish('/heartbeat',
        {
            pageName: 'app.js',
            timeMessageSent_secs_since_epoch: currentTime_secsSinceEpoch,
            iFrame1CycleCount: iFrame1CycleCount

        });
}, 1000);
