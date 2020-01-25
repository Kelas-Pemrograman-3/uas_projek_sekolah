const jadwal = require('express')()

const jadwalcont = require('../controller/cjadwal')

jadwal.post('/simpan', (req, res) => {
    // console.log(req.body)
    jadwalcont.simpandata(req.body)
    .then(result => res.json(result))
    .catch(error => res.json(error))
  })

jadwal.post('/simpanpelandroid', (req, res) => {
    jadwalcont.simpandatajadwalAndroid(req.body.kodemk,req.body.namamk,req.body.jam,
        req.body.hari,req.body.ruangan,req.body.nidn,req.body.namadosen)
        .then(result => res.json(result))
        .catch(error => res.json(error))
})

jadwal.put('/updatejadwalandroid/:id', (req, res) => {
    jadwalcont.updatejadwalAndroid(req.params.id,req.body.kodemk,req.body.namamk,req.body.jam,
        req.body.hari,req.body.ruangan,req.body.nidn,req.body.namadosen)
        .then(result => res.json(result))
        .catch(error => res.json(error))
})

jadwal.delete('/deletejadwal/:id', (req, res) => {
    console.log(req.params.id)
    jadwalcont.deletejadwal(req.params.id)
        .then(result => res.json(result))
        .catch(error => res.json(error))
})

// jadwal.get('/getpel/:id', (req, res) => {
//     console.log(req.params.id)
//     jadwalcont.getAlljadwal(req.params.id)
//         .then(result => res.json(result))
//         .catch(error => res.json(error))
// })

  // jadwal.get('/getpel', (req, res) => {
  //   jadwalcont.getjadwal()
  //     .then(result => res.json(result))
  //     .catch(error => res.json(error))
  // })
  
  jadwal.get ('/getalljadwal',(req,res) => {
    jadwalcont.getAlljadwal()
    .then(result => {
      res.json(result)
    }).catch(err => {
      res.json(err)
    })
  })

  jadwal.put('/upjadwal/:id',(req,res)=>{
    jadwalcont.updatejadwal(req.body,req.params.id)
    .then(result => res.json(result))
    .catch(err => res.json(err))
  })

module.exports = jadwal
