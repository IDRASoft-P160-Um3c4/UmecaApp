/**
 * Created by Vmware on 09/01/2015.
 */
$(document).ready(function (){

    $(".date-picker, .input-group-addon").mouseover(function () {
        $(this).css('cursor', 'pointer');
    });

    $('.umeca-time-picker').mouseover(function () {
        $(this).css('cursor', 'pointer');
    });

    $('.date-picker').blur(function () {
        try
        {
            jQuery.datepicker.parseDate('yy/mm/dd', $(this).val());
        }
        catch (e)
        {
            $(this).val('');
        }
    });

});
