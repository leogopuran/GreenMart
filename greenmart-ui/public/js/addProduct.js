$(document).ready(function() {
console.log("farmerId : " + farmerId);
    console.log("farmerIdJson : " + JSON.parse(JSON.stringify(farmerId)));
});

const domainUrl = "http://localhost:8080";

$("#submitNewProductButton").click(addNewProduct);

function addNewProduct(){

    if(farmerId !=null && farmerId != ""){

    var productName = $("#productName").val();
    var productType = $("#productType").val();
    var productPrice = $("#productPrice").val();
    var productMaxQuantity = $("#productMaxQuantity").val();;
    var productMinQuantity = $("#productMinQuantity").val();;
    var saleStartDate = $("#saleStartDate").val();
    var saleEndDate = $("#saleEndDate").val();
    var productMinQuantity = $("#productMinQuantity").val();
    var productMaxQuantity = $("#productMaxQuantity").val();
    var productDescription = $("#productDescription").val();
    var productLocationInfo = getProductLocationInfo();
    var productId = createUniqueProductid(20);
    var productOwnerId = JSON.parse(JSON.stringify(farmerId));
    var productAddDate = new Date().toLocaleString();

    var newProductDetails = {
       "productName":productName,
       "productType":productType,
       "productPrice":productPrice,
       "productMaxQuantity":productMaxQuantity,
       "productMinQuantity":productMinQuantity,
       "saleStartDate":saleStartDate,
       "saleEndDate":saleEndDate,
       "productDescription":productDescription,
       "productLocationInfo":productLocationInfo,
       "productId": productId,
       "productOwnerId":productOwnerId,
       "productAddDate": productAddDate
    };

    $.ajax({
            url: domainUrl + "/addProduct",
            type: "POST",
            contentType : "application/json",
            data : JSON.stringify(newProductDetails),
            dataType : 'json',
            cache : false,
            timeout : 600000,
            success: function(result){
                if(result){
                    alert("Product added sucessfully.");
                    document.location.href = "/farmerLogin";
                }
                else{
                    alert("Product adding failed.");
                }
            },
            error: function(e) {
                console.log(e);
            }
    });
    }
}

function createUniqueProductid(length) {
   var result           = '';
   var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
   var charactersLength = characters.length;
   for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
   }
   console.log("UUD : " + result);
   return result;
}