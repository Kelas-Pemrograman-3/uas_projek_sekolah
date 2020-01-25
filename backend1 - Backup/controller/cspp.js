const sppmod = require('../models/spp')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId;

exports.simpandata = (data) =>
new Promise((resolve, reject) => {
  sppmod.create(data)
  .then(res => {
    resolve ({error: false,
    pesan: 'Data Terimput'})
  })
  .catch(res => {
    reject({
      error: true,
      pesan: 'Gaagal Terimput'
    })
  })
})


exports.simpandatasppAndroid = (nis, nama, spp_bulan, status) =>
    new Promise((resolve, reject) => {

      let dataspp = new sppmod({
        nis: nis,
        nama: nama,
        spp_bulan: spp_bulan,
        status: status,
      });

      spphmod.create(dataMk)
          .then(res => {
            resolve ({error: false,
              pesan: 'Data Terimput'})
          })
          .catch(res => {
            reject({
              error: true,
              pesan: 'Gaagal Terimput'
            })
          })
    })


exports.updatesppAndroid = (nis,nama, spp_bulan, status) =>
    new Promise(async (resolve, reject) => {

      // let query = {_id: ObjectId(req.params.id)};
      // console.log(dataMk)
    //   console.log("disini nih")

      await sppmod.update(
          { _id: ObjectId(id) },
          {
            $set: {
                nis: nis,
                nama: nama,
                spp_bulan: spp_bulan,
                status: status,
            }
          })
          .then(res => {
            resolve ({error: false,
              pesan: 'Data Berhasil Dirubah'})
          })
          .catch(res => {
            // console.log("disini")
            reject({
              error: true,
              pesan: 'Gaagal Dirubah'
            })
          })
    })


// exports.hapusspp = (id) =>
//     new Promise(async (resolve, reject) => {
//       await sppmod.remove({_id: ObjectId(id)})
//           .then(res => {
//             resolve ({error:false, pesan: 'Data Berhasil hapus'})
//           })
//           .catch(res => {
//             console.log(error)
//             reject ({error:true, pesan: 'Data gagal dihapus'})
//           })
//     })


// exports.getspp = (mk) =>
//     new Promise((resolve, reject) => {
//       sppmod.findOne({nis: nis})
//           .then(res => {
//             resolve ({error:false, pesan: 'Data Berhasil diambil', data: res})
//           })
//           .catch(res => {
//             console.log(error)
//             reject ({error:true, pesan: 'Data Tidak diambil'})
//           })
//     })

// exports.getsppp = () =>
// new Promise((resolve, reject) => {
//   sppmod.find()
//   .then(res => {
//     resolve ({error:false, pesan: 'Data Berhasil diambil', data: res})
//   })
//   .catch(res => {
//     console.log(error)
//     reject ({error:true, pesan: 'Data Tidak diambil'})
//   })
// })

exports.getAllspp = () =>
    new Promise((resolve, reject) => {
        sppmod.find()
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

    exports.deletespp = (id) =>
    new Promise((resolve,reject) => {
      sppmod.deleteOne({
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

    exports.updatespp = (data,id) =>
    new Promise((resolve,reject) => {
      sppmod.updateOne({
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
      sppmod.find({
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
