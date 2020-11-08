
const domainUrl = "http://localhost:8080";

$(document).ready(function() {
    $("#addNewProductButton").click(function(){
        var go_to_url = "/addProduct";
        document.location.href = go_to_url;
    });

    loadProducts();

    var userDetails = null;

    if (farmerInfo.farmerName != ""){
        userDetails = farmerInfo;
        console.log(userDetails);
    }
    else if (customerInfo.customerName != ""){
        userDetails = customerInfo;
    }
    else{
        userDetails = null;
    }

    renderUserDetails(userDetails);
});

function loadProducts(){
    //ajax call to get and render product details
    var availableProducts = getAvailableProducts();
}

function addProductToCart(productId){

    $.ajax({
        url: "/addProductToCart?productId=" + productId,
        type: "GET",
        success: function(result){
            if(result != null & result !== ""){
                addToCartStatus = result;
                if(addToCartStatus === "ADDED"){
                    alert("Product added successfully...!");
                }
                else if(addToCartStatus === "NO_SESSION"){
                    location.href="/customerLogin";
                }
                else{
                    alert("Product adding failed...!");
                }
            }
        }
    });
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
    productLink.className = "btn btn-primary";
    productLink.text = "View Details";
    productLink.setAttribute('target', '_blank');
    productLink.href = domainUrl + "/listProducts?productId=" + product.productId;

    var addToCartLink = document.createElement('button');
    addToCartLink.setAttribute('id', "addProductToCartButton");
    addToCartLink.setAttribute("onClick", "addProductToCart(\"" + product.productId+ "\")");
    addToCartLink.className = "btn btn-success";
    addToCartLink.appendChild(document.createTextNode("Add To Cart"));

    cardBody.appendChild(productTitle);
    cardBody.appendChild(productPrice);
    cardBody.appendChild(productSummary);
    cardBody.appendChild(productLink);
    cardBody.appendChild(addToCartLink);

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

function renderUserDetails(userDetails){

    if(userDetails != null && userDetails.userType != null && userDetails.userType === "customer"){
        $(".userProfileImage").attr("src", "/images/avatar.png");
        $(".userName").text(userDetails.customerName);
        $(".userProfileLink").attr("href", "/farmerProfile");
        $(".farmerLogout").hide();
        $(".farmerAction").hide();
        $(".customerLogout").show();
        $(".customerAction").show();
    }
    else if(userDetails != null && userDetails.userType != null && userDetails.userType === "farmer"){
        $(".userProfileImage").attr("src", "/images/avatar.png");
        $(".userName").text(userDetails.farmerName);
        $(".userProfileLink").attr("href", "/customerProfile");
        $(".farmerLogout").show();
        $(".farmerAction").show();
        $(".customerLogout").hide();
        $(".customerAction").hide();
    }
    else {
        $(".userLoginLink").show();
        $(".userProfileSection").hide();
    }

}