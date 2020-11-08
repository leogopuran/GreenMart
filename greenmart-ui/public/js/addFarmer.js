$(document).ready(function() {
});

const domainUrl = "http://localhost:8080";

$("#submitNewFarmerButton").click(addNewFarmer);

function addNewFarmer(){

    var farmerName = $("#farmerName").val();
    var farmerAddress = $("#farmerAddress").val();
    var farmerPhone1 = $("#farmerPhone1").val();
    var farmerPhone2 = $("#farmerPhone2").val();
    var farmerEmail = $("#farmerEmail").val();
    var farmerId = createUniqueFarmerId(10);
    var farmerPassword = $("#farmerPassword").val();

    var newFarmerDetails = {
       "farmerName":farmerName,
       "farmerAddress":farmerAddress,
       "farmerPhone1":farmerPhone1,
       "farmerPhone2":farmerPhone2,
       "farmerEmail":farmerEmail,
       "farmerId":farmerId,
       "farmerPassword":farmerPassword
    };

    $.ajax({
            url: domainUrl + "/addNewFarmer",
            type: "POST",
            contentType : "application/json",
            data : JSON.stringify(newFarmerDetails),
            dataType : 'json',
            cache : false,
            timeout : 600000,
            success: function(result){
                if(result){
                    alert("Farmer added sucessfully.");
                }
                else{
                    alert("Farmer adding failed.");
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