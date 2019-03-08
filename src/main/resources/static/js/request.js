/*
 *
 */

$(document).ready(function() {
    $('.table .eBtn').on('click',function (event) {
        $('.requestForm #exampleModal').modal();
    });
    $('.table .eBtn2').on('click',function (event) {
        $('.requestForm2 #exampleModal2').modal();
    });
});