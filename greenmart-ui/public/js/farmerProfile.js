
const domainUrl = "http://localhost:8080";
var farmerDetails;

$(document).ready(function() {
    $("#addNewProductButton").click(function(){
        var go_to_url = "/addProduct";
        document.location.href = go_to_url;
    });

    farmerDetails = farmerInfo;
    console.log("farmer" + JSON.stringify(farmerDetails));

    renderFarmerProfile(farmerDetails);
    loadProducts();
});

function loadProducts(){
    var availableProducts = getProductsAddedByFarmer();
}

function getProductsAddedByFarmer(){

    var getProductsAddedByFarmer;

    $.ajax({
        url: domainUrl + "/productsAddedByFarmer",
        type: "POST",
        contentType : "application/json",
        dataType : 'json',
        cache : false,
        timeout : 600000,
        data: JSON.stringify(farmerDetails),
        success: function(result){
//            result = $.parseJSON(result);
            if(result != null){
                console.log(result);
                getProductsAddedByFarmer = result;
                result.forEach(function(product) {
                    renderProducts(product);
                });
            }
        }
    });

    return getProductsAddedByFarmer;
}

function getAvailableProducts(){

    var availableProducts;

    $.ajax({
        url: domainUrl + "/listProducts",
        type: "GET",
        success: function(result){
            result = $.parseJSON(result);
            if(result != null){
                result.forEach(function(product) {
                    renderProducts(product);
                });
            }
        }
    });

    return availableProducts;
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
    productLink.className = "btn btn-info";
    productLink.text = "Info";
    productLink.setAttribute('target', '_blank');
    productLink.href = domainUrl + "/listProducts?productId=" + product.productId;

    var productRemoveLink = document.createElement('a');
    productRemoveLink.className = "btn btn-danger";
    productRemoveLink.text = "Remove";
//    productRemoveLink.setAttribute('target', '_blank');
    productRemoveLink.href = domainUrl + "/removeProduct?productId=" + product.productId;

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

function renderFarmerProfile(farmerDetails){
    $(".farmerProfileImage").attr("src", "/images/avatar.png");
    $(".farmerName").text(farmerDetails.farmerName);
    $(".farmerAddress").text(farmerDetails.farmerAddress);
    $(".farmerPhone1").text(farmerDetails.farmerPhone1);
    $(".farmerPhone2").text(farmerDetails.farmerPhone2);
    $(".farmerEmail").text(farmerDetails.farmerEmail);
}