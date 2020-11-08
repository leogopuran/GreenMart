$(document).ready(function() {
});

const domainUrl = "http://localhost:9000";

$("#farmerLoginButton").click(farmerLoginHelper);

function farmerLoginHelper(){

    var farmerPhone = $("#farmerPhone").val();
    var farmerPassword = $("#farmerPassword").val();

    var farmerLoginDetails = {
       "farmerPhone":farmerPhone,
       "farmerPassword":farmerPassword
    };

    $.ajax({
            url: domainUrl + "/farmerLogin",
            type: "POST",
            data : farmerLoginDetails,
            cache : false,
            timeout : 600000,
            success: function(result){
                if(result){
                    console.log(result);
                    document.location.href = result;
                    alert("Farmer Login Successfull.");
                }
                else{
                    console.log(result);
                    alert("Farmer Login failed.");
                    document.location.href = result;
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