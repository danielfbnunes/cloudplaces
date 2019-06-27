var picBase64 = "";

window.onload(function reload() {
    $('#name').val("");
    $('#email').val("");
    $('#cellphone').val("");
    $('#password').val("");
    $('#photo').val("");
});


function sendPost() {

    var postData = {};

    var name = $('#name').val();
    var email = $('#email').val();
    var cellphone = $('#cellphone').val();
    var pw = $('#password').val();
    var photo = picBase64;

    if (name.length == 0 || email.length == 0 || cellphone.length == 0 || pw.length == 0 || photo.length == 0) {
        alert("[ERROR] Fill all the necessary fields!");
    } else {
        postData["name"] = name;
        postData["email"] = email;
        postData["cellphone"] = cellphone;
        postData["pw"] = pw;
        postData["photo"] = photo;

        $.ajax({
            type: "POST",
            url: "/signUp",
            contentType: "application/json",
            success: function (response) {
                if (response != "login")
                    alert(response);
                else
                    window.location.replace('login');
            },
            data: JSON.stringify(postData)
        });
    }
}


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#falseinput').attr('src', e.target.result);
            picBase64 = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }
}