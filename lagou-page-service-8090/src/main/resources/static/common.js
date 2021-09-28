$(document).ajaxSend(function (event,request,settings) {

    var token = $.cookie("token");

    request.setRequestHeader("token",token);

});
