const adminModel = require('../models/admin')
const bcrypt = require('bcryptjs')


exports.postadmin = (data) =>
  new Promise((resolve, reject) => {
    bcrypt.hash(data.password, 10, (err, hash) => {
      data.password = hash
      adminModel.find({
        user: data.user
      }).then(hasil => {
        if (hasil.length > 0) {
          reject({
            error: true,
            pesan: 'user Sudah Digunakan'
          })
        } else {
          adminModel.create(data)
            .then(res => {
              resolve({
                error: false,
                pesan: 'Berhasil Input Data'
              })
            }).catch(() => {
              reject({
                error: true,
                pesan: 'NIS Sudah Digunakan.'
              })
            })
        }
      })
    })
  })


  exports.loginadmin = (data) =>
  new Promise((resolve, reject) => {
    adminModel.findOne({
      user: data.user
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
