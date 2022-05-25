$(document).ready(function () {
    $('.show-file').on('click', function (e) {
        e.preventDefault();
        $.ajax({
            type: 'GET',
            url: $(this).attr('path'),
            success: function(data) {
                $('.files-fragment').html(data);
                $('.files-fragment').show();
            },
            error: function () {
                alert('error')
            }
        });
    });

    $('.btn-close').on('click', function (e) {
        $('.files-fragment').hide();
        location.reload();
    });
});