const spp = require('express')()

const sppcont = require('../controller/cspp')

spp.post('/simpan', (req, res) => {
    // console.log(req.body)
   sppcont.simpandata(req.body)
    .then(result => res.json(result))
    .catch(error => res.json(error))
  })

spp.post('/simpanspp', (req, res) => {
    sppcont.simpandatasppAndroid(req.body.kodemk,req.body.namamk,req.body.jam,
        req.body.hari,req.body.ruangan,req.body.nidn,req.body.namadosen)
        .then(result => res.json(result))
        .catch(error => res.json(error))
})

spp.put('/updatesppandroid/:id', (req, res) => {
    sppcont.updatejadwalAndroid(req.params.id,req.body.kodemk,req.body.namamk,req.body.jam,
        req.body.hari,req.body.ruangan,req.body.nidn,req.body.namadosen)
        .then(result => res.json(result))
        .catch(error => res.json(error))
})

spp.delete('/deletespp/:id', (req, res) => {
    console.log(req.params.id)
    sppcont.deletespp(req.params.id)
        .then(result => res.json(result))
        .catch(error => res.json(error))
})

// spp.get('/getspp/:id', (req, res) => {
//     // console.log(req.params.id)
//     sppcont.getspp(req.params.id)
//         .then(result => res.json(result))
//         .catch(error => res.json(error))
// })

  // spp.get('/getspp', (req, res) => {
  //   sppcont.getsppp()
  //     .then(result => res.json(result))
  //     .catch(error => res.json(error))
  // })

  spp.get ('/getallspp',(req,res) => {
    sppcont.getAllspp()
    .then(result => {
      res.json(result)
    }).catch(err => {
      res.json(err)
    })
  })

  spp.put('/upspp/:id',(req,res)=>{
    sppcont.updatespp(req.body,req.params.id)
    .then(result => res.json(result))
    .catch(err => res.json(err))
  })


module.exports = spp
