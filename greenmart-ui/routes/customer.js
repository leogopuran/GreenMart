const express = require('express')
const router = express.Router()
const axios = require('axios')

var apiDomainUrl = "http://localhost:8080";

router.get('/customerSignUp', (req, res, next) => {

    res.render("customersignup", null)
})
router.get('/customerLogout', (req, res, next) => {
    req.session.destroy((err) => {
        if(err) {
            return console.log(err);
        }
        res.redirect('/customerlogin');
    });
})
router.get('/customerLogin', (req, res, next) => {
    if(req.session != null && req.session.customerInfo != null){
        res.render("customerProfile", req.session.customerInfo)
    }
    else{
        res.render("customerlogin", null)
    }
})
router.post('/customerLogin', (req, res, next) => {

    if((req.body != "") && (req.body != "")){

        var customerLoginDetails = {
            "customerId":req.body.customerId,
            "customerPassword":req.body.customerPassword
        };

         //Sending API request
         var loggedInCustomerInfo = null;
         axios.post(apiDomainUrl + "/validateCustomerLogin", customerLoginDetails)
         .then(response => {
            loggedInCustomerInfo = response.data;
            req.session.customerInfo = loggedInCustomerInfo;
            console.log("loggedInCustomerInfo => " + JSON.stringify(loggedInCustomerInfo));
            res.send(loggedInCustomerInfo);
         })
         .catch(error => {
            console.log(error)
            res.send(loggedInCustomerInfo);
         })
    }
    else{
        res.send(loggedInCustomerInfo);
    }
})
router.get('/customerCart', (req, res, next) => {
    if(req.session != null && req.session.customerInfo != null){
        res.render("customerCart", req.session.customerInfo)
    }
    else{
        res.render("customerlogin", null)
    }
})
router.get('/customerProfile', (req, res, next) => {
    if(req.session != null && req.session.customerInfo != null){
        res.render("customerProfile", req.session.customerInfo)
    }
    else{
        res.render("customerlogin", null)
    }
})
router.post('/customerSignUp', (req, res, next) => {

    if((req.body != "") && (req.body != "")){

        var newCustomerDetails = {
            "customerId":req.body.customerTelephone1,
            "customerName":req.body.customerName,
            "customerAge":req.body.customerAge,
            "customerAddress":req.body.customerAddress,
            "customerTelephone1":req.body.customerTelephone1,
            "customerTelephone2":req.body.customerTelephone2,
            "customerPassword":req.body.customerPassword,
            "customerLocation":req.body.customerLocation
        };

        //Sending API request
        var customerSignupStatus = false;
        axios.post(apiDomainUrl + "/customerSignUp", newCustomerDetails)
        .then(response => {
            customerSignupStatus = response.data;
            console.log("customerSignupStatus => " + JSON.stringify(customerSignupStatus));
            res.send(customerSignupStatus);
        })
        .catch(error => {
            console.log(error)
            res.send(customerSignupStatus);
        })
    }
    else{
        res.send(customerSignupStatus);
    }

})

router.post('/productsInCustomerCart', (req, res, next) => {

    var productsAddedByCustomer = null;
    if(req.session != null && req.session.customerInfo != null){
        var customerDetails = {
            "customerId":req.session.customerInfo.customerId
        };
        axios.post(apiDomainUrl + "/viewCartItems", customerDetails)
        .then(response => {
            console.log("customerCartDetails => " + JSON.stringify(response.data));
            productsAddedByCustomer = response.data;
            res.send(productsAddedByCustomer);
        })
        .catch(error => {
            console.log(error)
            res.send(productsAddedByCustomer);
        })
    }
    else{
        res.send(productsAddedByCustomer);
    }
})



module.exports = router