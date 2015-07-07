/**
 * Created by esteves on 06.07.15.
 */

var N3 = require('n3');
var writer = N3.Writer({ prefixes: { 'c': 'http://example.org/cartoons#',
                                    'prov':'http://www.w3.org/ns/prov#',
                                    'rdfs':'http://www.w3.org/1999/02/22-rdf-syntax-ns#'} });

writer.addTriple('http://example.org/cartoons#Tom', 'http://www.w3.org/1999/02/22-rdf-syntax-ns#type', 'http://example.org/cartoons#Cat');
writer.addTriple({
    subject:   'http://example.org/cartoons#Tom',
    predicate: 'http://example.org/cartoons#name',
    object:    '"Tom"'
});
writer.addTriple({
    subject:   'http://example.org/cartoons#Tom',
    predicate: 'http://www.w3.org/ns/prov#wasGeneratedBy',
    object:    '"Tom"'
});

writer.addTriple('http://example.org/cartoons#Tom1',
    'http://www.w3.org/1999/02/22-rdf-syntax-ns#type',
    'http://example.org/cartoons#Cat');

writer.addTriple({
    subject:   'http://example.org/cartoons#Tom1',
    predicate: 'http://example.org/cartoons#name',
    object:    '"Tom1"'
});
writer.addTriple({
    subject:   'http://example.org/cartoons#Tom1',
    predicate: 'http://www.w3.org/ns/prov#wasGeneratedBy',
    object:    '"Tom1"'
});


writer.end(function (error, result) { console.log(result); });