
const domainUrl = "http://localhost:8080";
var customerDetails;

$(document).ready(function() {
    $("#addNewProductButton").click(function(){
        var go_to_url = "/addProduct";
        document.location.href = go_to_url;
    });

    customerDetails = customerInfo;
    console.log("customerDetails" + JSON.stringify(customerDetails));

    renderCustomerProfile(customerDetails);
    loadProducts();
});

function loadProducts(){
    var availableProducts = getProductsAddedByCustomer();
}

function getProductsAddedByCustomer(){

    var getProductsInCustomerCart = "nodata";

    $.ajax({
        url: "/productsInCustomerCart",
        type: "POST",
        contentType : "application/json",
        dataType : 'json',
        cache : false,
        timeout : 600000,
        data: null,
        success: function(result){
            if(result != null){
                console.log("customerData : "+result);
                getProductsInCustomerCart = result;
                result.forEach(function(product) {
                    renderProducts(product);
                });
            }
            else{
                alert("No records found ...!");
            }
        },
        error: function(e) {
            console.log(e);
            alert("Failed getting data...!");
        }
    });

    return getProductsInCustomerCart;
}

function renderProducts(product){

    var div = document.createElement('div');
    div.className = "card w-25";
    var img = document.createElement('img');
    img.className = "card-img-top";
    img.src = "https://5.imimg.com/data5/VF/CT/MY-49857352/organic-vegetables-500x500.png";
    div.appendChild(img);

    var cardBody = document.createElement('div');
    cardBody.className = "card-body";

    var productTitle = document.createElement('h4');
    productTitle.className = "card-title";
    productTitle.appendChild(document.createTextNode(product.productName));

    var productSummary = document.createElement('p');
    productSummary.className = "card-text";
    productSummary.appendChild(document.createTextNode(product.productDescription));

    var productPrice = document.createElement('h5');
    productPrice.className = "card-text";
    productPrice.appendChild(document.createTextNode(product.productPrice + "Rs/Kg"));

    var productLink = document.createElement('a');
    productLink.className = "btn btn-success";
    productLink.text = "Confirm";
    productLink.setAttribute('target', '_blank');
    productLink.href = domainUrl + "/confirmPurchase?productId=" + product.productId;

    var productRemoveLink = document.createElement('a');
    productRemoveLink.className = "btn btn-danger";
    productRemoveLink.text = "Remove";
    productRemoveLink.setAttribute('target', '_blank');
    productRemoveLink.href = domainUrl + "/removeProductFromCart?productId=" + product.productId;

    cardBody.appendChild(productTitle);
    cardBody.appendChild(productPrice);
    cardBody.appendChild(productSummary);
    cardBody.appendChild(productLink);
    cardBody.appendChild(productRemoveLink);

    div.appendChild(cardBody);

    var cardFooter = document.createElement('div');
    cardFooter.className = "card-footer";
    var cardFooterText = document.createElement('small');
    cardFooterText.className="text-muted";
    cardFooterText.appendChild(document.createTextNode("Posted on " + product.productAddDate));

    cardFooter.appendChild(cardFooterText);
    div.appendChild(cardFooter);

    document.getElementById('productListRow').appendChild(div);

}

function renderCustomerProfile(farmerDetails){
    $(".customerProfileImage").attr("src", "/images/avatar.png");
    $(".customerName").text(farmerDetails.customerName);
    $(".customerAge").text(farmerDetails.customerAge);
    $(".customerAddress").text(farmerDetails.customerAddress);
    $(".customerLocation").text(farmerDetails.customerLocation);
    $(".customerTelephone1").text(farmerDetails.customerTelephone1);
    $(".customerTelephone2").text(farmerDetails.customerTelephone2);
    $(".customerStatus").text(farmerDetails.customerStatus);
}