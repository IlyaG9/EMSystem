
$(function() {

    $("#employee_form").validate({
        rules: {
            firstname: {
                required: true,
                minlength: 2,
                maxlength: 15
            },
            lastname: {
                required: true,
                minlength: 2
            },
            salary: {
                required: true,
                minlength: 1,
                maxlength: 18,
                number:true,
                min:1,
                max:999999999999999.99
            },
            birthdate: {
                required: true,
                date: true
            }
        }
    });


    $("#employee_birthdate").datepicker("option", "dateFormat", "dd/mm/yy");
    $("#employee_birthdate").datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange: '1900:2014'
    });
    $("#employee_birthdate").attr("readonly", "");
    
});
