const siswaModel = require('../models/siswa')
const bcrypt = require('bcryptjs')

exports.postsiswa = (data) =>
  new Promise((resolve, reject) => {
    bcrypt.hash(data.password, 10, (err, hash) => {
      data.password = hash
      siswaModel.find({
        nis: data.nis
      }).then(hasil => {
        if (hasil.length > 0) {
          reject({
            error: true,
            pesan: 'NIS Sudah Digunakan'
          })
        } else {
          siswaModel.create(data)
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

// exports.login = (data) =>
//   new Promise((resolve, reject) => {
//     siswaModel.findOne({
//       nis: data.nis
//     }).then(res => {
//       if (res === null) {
//         reject({
//           error: true,
//           pesan: 'Username Tidak Terdaftar'
//         })
//       } else {
//         let passwordHash = res.password
//         if (data.password, passwordHash) {
//           resolve({
//             error: false,
//             pesan: 'Berhasil Login',
//             data: res
//           })
//         } else {
//           reject({
//             error: true,
//             pesan: 'Password Anda Salah'
//           })
//         }
//       }
//     })
//   })

exports.login = (data) =>
  new Promise((resolve, reject) => {
    siswaModel.findOne({
      nis: data.nis
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

exports.getAllsiswa = () =>
    new Promise((resolve, reject) => {
        siswaModel.find()
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

    exports.deletesiswa = (id) =>
    new Promise((resolve,reject) => {
      siswaModel.deleteOne({
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

    exports.updatesiswa = (data,id) =>
    new Promise((resolve,reject) => {
      siswaModel.updateOne({
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
      siswaModel.find({
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