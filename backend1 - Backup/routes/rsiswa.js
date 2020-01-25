const siswa = require('express')()
const siswaController = require('../controller/csiswa')

siswa.post('/postsiswa', (req, res) => {
  siswaController.postsiswa(req.body)
    .then(result => {
      res.json(result)
    }).catch(err => {
      res.json(err)
    })
})

siswa.post('/login', (req, res) => {
    siswaController.login(req.body)
        .then(result => {
            res.json(result)
        }).catch(err => {
        res.json(err)
    })
})

siswa.get ('/getallsiswa',(req,res) => {
  siswaController.getAllsiswa()
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

siswa.delete('/delete/:id', (req,res) => {
  siswaController.deletesiswa(req.params.id)
  .then(result => res.json(result))
  .catch(Error => res.json(Error))
})

siswa.put('/upsiswa/:id',(req,res)=>{
  siswaController.updatesiswa(req.body,req.params.id)
  .then(result => res.json(result))
  .catch(err => res.json(err))
})
module.exports = siswa
