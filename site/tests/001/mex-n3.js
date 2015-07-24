/**
 * Created by esteves on 23.07.15.
 */
var N3 = require('n3');

var parser = N3.Parser();
parser.parse('@prefix c: <http://example.org/cartoons#>.\n' +
    'c:Tom a c:Cat.\n' +
    'c:Jerry a c:Mouse;\n' +
    '        c:smarterThan c:Tom.',
    function (error, triple, prefixes) {
        if (triple)
            console.log(triple.subject, triple.predicate, triple.object, '.');
        else
            console.log("# That's all, folks!", prefixes)
    });