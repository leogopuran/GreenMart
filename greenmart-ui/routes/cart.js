const express = require('express')
const router = express.Router()
const axios = require('axios')

var apiDomainUrl = "http://localhost:8080";

router.get('/addProductToCart', (req, res, next) => {

    var productIdToAdd;
    var customerId;
    var addToCartStatus = null;

    if(req.session != null && req.session.customerInfo != null){
        customerInfo = req.session.customerInfo;

        if((req.query != "") && (req.query.productId != "")){
            productIdToAdd = req.query.productId;

            var cartDetailsToUpdate = {
                "customerId":customerInfo.customerId,
                "productId":productIdToAdd
            };

            //Sending API request
            axios.post(apiDomainUrl + "/addProductToCart", cartDetailsToUpdate)
            .then(response => {
                console.log("addToCartStatus => " + JSON.stringify(response.data));
                if(response.data){
                    addToCartStatus = "ADDED";
                }
                else{
                    addToCartStatus = "FAILED";
                }
                res.send(addToCartStatus);
            })
            .catch(error => {
                console.log(error)
                addToCartStatus = "FAILED";
                res.send(addToCartStatus);
            })
        }
        else{
            addToCartStatus = "FAILED";
            res.send(addToCartStatus);
        }
    }
    else{
        addToCartStatus = "NO_SESSION";
        res.send(addToCartStatus);
    }
})

module.exports = router