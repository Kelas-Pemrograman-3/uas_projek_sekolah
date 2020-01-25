const guru = require('express')()
const guruController = require('../controller/cguru')

guru.post('/postguru', (req, res) => {
  guruController.postguru(req.body)
    .then(result => {
      res.json(result)
    }).catch(err => {
      res.json(err)
    })
})

guru.post('/login', (req, res) => {
    guruController.login(req.body)
        .then(result => {
            res.json(result)
        }).catch(err => {
        res.json(err)
    })
})

guru.get ('/getallguru',(req,res) => {
  guruController.getAllguru()
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

guru.delete('/deletegr/:id', (req,res) => {
  guruController.deleteguru(req.params.id)
  .then(result => res.json(result))
  .catch(Error => res.json(Error))
})

guru.put('/updategr/:id',(req,res)=>{
  guruController.updateguru(req.body,req.params.id)
  .then(result => res.json(result))
  .catch(err => res.json(err))
})

module.exports = guru
