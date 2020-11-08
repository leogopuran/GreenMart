const express = require('express')
const router = express.Router()
const axios = require('axios')

var apiDomainUrl = "http://localhost:8080";

router.get('/', (req, res, next) => {

    var userDetails = null;

    if(req.session != null && req.session.farmerInfo != null){
        userDetails = req.session.farmerInfo
    }

    if(req.session != null && req.session.customerInfo != null){
        userDetails = req.session.customerInfo
    }

    res.render("home", userDetails)
})

router.get('/userCommonLogin', (req, res, next) => {

    var userDetails = null;

    if(req.session != null && req.session.farmerInfo != null){
        userDetails = req.session.farmerInfo
        res.redirect("/farmerLogin");
    }
    else if(req.session != null && req.session.customerInfo != null){
        userDetails = req.session.customerInfo
        res.redirect("/customerLogin");
    }
    else{
        res.render("userCommonLogin",null);
    }
})

router.get('/addProduct', (req, res, next) => {
    res.render("addProduct", req.session.farmerInfo)
})
router.get('/farmerSignUp', (req, res, next) => {
    res.render("farmersignup", null)
})
router.get('/farmerLogout', (req, res, next) => {
    req.session.destroy((err) => {
        if(err) {
            return console.log(err);
        }
        res.redirect('/farmerlogin');
    });
})
router.get('/farmerLogin', (req, res, next) => {
    if(req.session != null && req.session.farmerInfo != null){
        res.render("farmerProfile", req.session.farmerInfo)
    }
    else{
        res.render("farmerlogin", null)
    }
})
router.get('/farmerProfile', (req, res, next) => {
    if(req.session != null && req.session.farmerInfo != null){
        res.render("farmerProfile", req.session.farmerInfo)
    }
    else{
        res.render("farmerlogin", null)
    }
})
router.post('/farmerLogin', (req, res, next) => {

    var go_to_url = "/farmerLogin";

    if((req.body.phone != "") && (req.body.farmerPassword != "")){

        var farmerLoginDetails = {
            "farmerPhone1":req.body.farmerPhone,
            "farmerPassword":req.body.farmerPassword
        };

        //Sending API request
        axios.post(apiDomainUrl + "/validateFarmerLogin", farmerLoginDetails)
        .then(response => {
            req.session.farmerInfo = response.data;
            console.log("data in session => " + JSON.stringify(response.data));
            go_to_url = "/farmerProfile";
            res.end(go_to_url);
        })
        .catch(error => {
            console.log(error)
        })
    }
    else{
        go_to_url = "/farmerLogin";
        res.end(go_to_url);
    }

})

module.exports = router