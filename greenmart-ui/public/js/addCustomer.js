$(document).ready(function() {
});

const domainUrl = "http://localhost:8080";

$("#submitNewCustomerButton").click(addNewCustomer);

function addNewCustomer(){

    var customerName = $("#customerName").val();
    var customerAge = $("#customerAge").val();
    var customerAddress = $("#customerAddress").val();
    var customerTelephone1 = $("#customerTelephone1").val();
    var customerTelephone2 = $("#customerTelephone2").val();
    var customerPassword = $("#customerPassword").val();
    var customerLocation = getCustomerLocationInfo();

    var newCustomerDetails = {
       "customerId":customerTelephone1,
       "customerName":customerName,
       "customerAge":customerAge,
       "customerAddress":customerAddress,
       "customerTelephone1":customerTelephone1,
       "customerTelephone2":customerTelephone2,
       "customerPassword":customerPassword,
       "customerLocation":customerLocation
    };

    $.ajax({
            url: "/customerSignUp",
            type: "POST",
            contentType : "application/json",
            data : JSON.stringify(newCustomerDetails),
            dataType : 'json',
            cache : false,
            timeout : 600000,
            success: function(result){
                if(result){
                    alert("Customer added sucessfully.");
                }
                else{
                    alert("Customer adding failed.");
                }
            },
            error: function(e) {
                console.log(e);
                alert("Customer adding failed.");
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