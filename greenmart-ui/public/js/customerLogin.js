$(document).ready(function() {
});

$("#customerLoginButton").click(customerLoginHelper);

function customerLoginHelper(){

    var customerId = $("#customerPhone").val();
    var customerPassword = $("#customerPassword").val();

    var customerLoginDetails = {
       "customerId":customerId,
       "customerPassword":customerPassword
    };

    console.log(customerLoginDetails);

    $.ajax({
            url: "/customerLogin",
            type: "POST",
            data : customerLoginDetails,
            cache : false,
            timeout : 600000,
            success: function(result){
                if(result && result != null){
                    console.log(result);
                    alert("Customer Login Successfull.");
                    location.href="/customerProfile"
                }
                else{
                    console.log(result);
                    alert("Customer Login failed.");
                }
            },
            error: function(e) {
                console.log(e);
            }
        });
}

function createUniqueFarmerId(length) {
   var result           = '';
   var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
   var charactersLength = characters.length;
   for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
   }
   console.log("UUID_Farmer : " + result);
   return result;
}