const admin = require('express')()
const adminController = require('../controller/admin')

admin.post('/postadmin', (req, res) => {
    adminController.postadmin(req.body)
      .then(result => {
        res.json(result)
      }).catch(err => {
        res.json(err)
      })
  })

admin.post('/loginadmin', (req, res) => {
    adminController.loginadmin(req.body)
        .then(result => {
            res.json(result)
        }).catch(err => {
        res.json(err)
    })
})

module.exports = admin
