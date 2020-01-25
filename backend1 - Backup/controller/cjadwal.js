const jadwalmod = require('../models/jadwal')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId;

exports.simpandata = (data) =>
new Promise((resolve, reject) => {
  jadwalmod.create(data)
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


exports.simpandatajadwalAndroid = (kodepelajaran, namapelajaran, jam, hari,
                        ruangan, nign, namaguru) =>
    new Promise((resolve, reject) => {

      let datajadwal = new jadwalmod({
        kodepelajaran: kodepelajaran,
        namapelajaran: namapelajaran,
        jam: jam,
        hari: hari,
        ruangan: ruangan,
        nign: nign,
            namaguru: namaguru,
        // guru: [
        //   {
        //     nign: nign,
        //     namaguru: namaguru
        //   }
        // ]
      });

      jadwalmod.create(datapelajaran)
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


exports.updatejadwalAndroid = (kodepelajaran, namapelajaran, jam, hari,
    ruangan, nign, namaguru) =>
    new Promise(async (resolve, reject) => {

      // let query = {_id: ObjectId(req.params.id)};
      // console.log(dataMk)
      console.log("disini nih")

      await jadwalmod.update(
          { _id: ObjectId(id) },
          {
            $set: {
              kodepelajaran: kodepelajaran,
              namapelajaran: namapelajaran,
              jam: jam,
              hari: hari,
              ruangan:ruangan,
              nign: nign,
            namaguru: namaguru,
              // guru: [
              //   {
              //     nign: nign,
              //     namaguru: namaguru
              //   }
              // ]
            }
          })
          .then(res => {
            resolve ({error: false,
              pesan: 'Data Berhasil Dirubah'})
          })
          .catch(res => {
            console.log("disini")
            reject({
              error: true,
              pesan: 'Gaagal Dirubah'
            })
          })
    })


// exports.hapusjadwal = (id) =>
//     new Promise(async (resolve, reject) => {
//       await jadwalhmod.remove({_id: ObjectId(id)})
//           .then(res => {
//             resolve ({error:false, pesan: 'Data Berhasil hapus'})
//           })
//           .catch(res => {
//             console.log(error)
//             reject ({error:true, pesan: 'Data gagal dihapus'})
//           })
//     })


exports.getjadwalpelajaran = (kodepelajaran) =>
    new Promise((resolve, reject) => {
      jadwalmod.findOne({kodepelajaran: kodepelajaran})
          .then(res => {
            resolve ({error:false, pesan: 'Data Berhasil diambil', data: res})
          })
          .catch(res => {
            console.log(error)
            reject ({error:true, pesan: 'Data Tidak diambil'})
          })
    })

// 
exports.getAlljadwal = () =>
    new Promise((resolve, reject) => {
        jadwalmod.find()
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

    exports.deletejadwal = (id) =>
    new Promise((resolve,reject) => {
      jadwalmod.deleteOne({
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

    exports.updatejadwal = (data,id) =>
    new Promise((resolve,reject) => {
      jadwalmod.updateOne({
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
      jadwalmod.find({
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
