$(document).ready(function () {
    $('body').on('click', '.show-files', function () {
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

    $('body').on('click', '.btn-close', function () {
        $('.files-fragment').hide();
    });
});
