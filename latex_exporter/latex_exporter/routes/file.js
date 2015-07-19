var express = require('express');
var router = express.Router();
var multer  = require('multer');
var app = express();


/* POST user rdf file */
router.post('/', function(req, res) {
	app.use(multer({
		onFileUploadComplete: function (file, req, res) {
			console.log(file.fieldname + ' uploaded to  ' + file.path);
		}}))
	
	res.send("Whazup!");

});

module.exports = router;