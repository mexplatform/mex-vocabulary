var express = require('express');
var router = express.Router();
var multer  = require('multer');
var app = express();


/* GET users listing. */
router.post('/', function(req, res) {
	 app.use(multer({
	 onFileUploadComplete: function (file, req, res) {
     console.log(file.fieldname + ' uploaded to  ' + file.path);
}}))
	 
});

module.exports = router;