const express = require('express')
const session = require('express-session')
const bodyParser = require('body-parser');
const path = require('path')
const hoganMiddleware = require('hogan-middleware')


const app = express()
app.set('views', path.join(__dirname, 'views'))
app.set('view engine', 'mustache')
app.engine('mustache', hoganMiddleware.__express)
app.use(express.static(path.join(__dirname, 'public')))
app.use('/fontawesome', express.static(path.join(__dirname, './node_modules/@fortawesome/fontawesome-free')));
app.use('/bootstrap', express.static(path.join(__dirname, './node_modules/bootstrap/dist')));
app.use('/jquery', express.static(path.join(__dirname, './node_modules/jquery/dist')));
app.use('/popper', express.static(path.join(__dirname, './node_modules/popper.js/dist')));
app.use(session({secret: 'ssshhhhh',saveUninitialized: true,resave: true}));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

//Routings
const indexRouter = require('./routes/index')
app.use('/', indexRouter)

const customerRouter = require('./routes/customer')
app.use('/', customerRouter)

const cartRouter = require('./routes/cart')
app.use('/', cartRouter)

app.listen(9000)
console.log('Server running at http://localhost:9000')