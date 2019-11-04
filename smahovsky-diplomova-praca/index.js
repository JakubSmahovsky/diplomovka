const express = require('express')
const bodyParser = require('body-parser')
const cookieParser = require('cookie-parser')
const expressSession = require('express-session')

const PORT = process.env.PORT || 3010
const app = express()

app.use(express.static(__dirname + '/build'))
app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())
app.use(cookieParser())

// init sessions
app.use(expressSession({
  key: 'sid',
  secret: 'absolutely not correct hose battery staple',
  resave: false,
  saveUninitialized: false
}))

// clear cookie if it has no matching session
app.use((req, res, next) => {
  if (req.cookies.sid && !req.session.login) {
      res.clearCookie('sid')
  }
  next()
})

app.post('/api/login', function(req, res) {
  if (req.body.login != 'dipl' || req.body.password != 'dipl')
    res.send()
  else {
    req.session.login = req.body
    res.send({
      status: 'success'
    })
  }
})

app.get('/api/loginStatus', function(req, res) {
  res.send({loggedIn: (req.session.login && req.cookies.sid)})
})

app.get('/', function(req, res) {
  res.sendFile('/build/index.html')
})

app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`)
})