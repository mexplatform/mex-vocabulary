console.log("POR");
$('#uploadForm').submit(function() {
    $(this).ajaxSubmit({
        error: function(xhr) {
           status('Error: ' + xhr.status);
       },
       success: function(response) {
         console.log(response);
     }
 });
    //Very important line, it disable the page refresh.
    return false;
}); 