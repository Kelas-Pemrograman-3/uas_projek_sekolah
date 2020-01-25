const guruModel = require('../models/guru')
const bcrypt = require('bcryptjs')

exports.postguru = (data) =>
  new Promise((resolve, reject) => {
    // bcrypt.hash(data.password, 10, (err, hash) => {
    //   data.password = hash
      guruModel.find({
        nign: data.nign
      }).then(hasil => {
        if (hasil.length > 0) {
          reject({
            error: true,
            pesan: 'NIGN Sudah Digunakan'
          })
        } else {
          guruModel.create(data)
            .then(res => {
              resolve({
                error: false,
                pesan: 'Berhasil Input Data'
              })
            }).catch(() => {
              reject({
                error: true,
                pesan: 'NIGN Sudah Digunakan.'
              })
            })
        }
      })
    })
  // })

exports.login = (data) =>
  new Promise((resolve, reject) => {
    guruModel.findOne({
      nign: data.nign
    }).then(res => {
      if (res === null) {
        reject({
          error: true,
          pesan: 'Username Tidak Terdaftar'
        })
      } else {
        let passwordHash = res.password
        if (bcrypt.compareSync(data.password, passwordHash)) {
          resolve({
            error: false,
            pesan: 'Berhasil Login',
            data: res
          })
        } else {
          reject({
            error: true,
            pesan: 'Password Anda Salah'
          })
        }
      }
    })
  })

exports.getAllguru = () =>
    new Promise((resolve, reject) => {
        guruModel.find()
            .then(results => {
                resolve({
                    error: false,
                    pesan: 'Berhasil Mengambil Data',
                    data: results
                })
            }).catch(() => {
            reject({
                error: true,
                pesan: 'Gagal Mengambil Data'
            })
        })
    })

    exports.deleteguru = (id) =>
    new Promise((resolve,reject) => {
      guruModel.deleteOne({
        _id: id
      }).then(res =>{
        resolve ({
          error:false,
          pesan: 'data berhasil dihapus'
        })
      })
      .catch(error => {
        console.log(error)
        reject({
          error:true,
          pesan: 'data tidak terhapus'
        })
      })
    })

    exports.updateguru = (data,id) =>
    new Promise((resolve,reject) => {
      guruModel.updateOne({
        _id: id
      },data)
      .then(res =>{
        resolve({
          error: false,
          pesan: 'data update successfully'
        })
      }).catch(() => {
        reject({
          error:false,
          pesan: 'data update'
        })
      })
    })
    
    exports.getbyid = (id) =>
    new Promise((resolve,reject) => {
      guruModel.find({
        _id: Object(id)
      })
      .then(res =>{
        resolve ({
          error:false,
          pesan: 'data update successfully'
        })
      })
      .catch(() => {
        reject({
          error:false,
          pesan: 'data update'
        })
      })
    })