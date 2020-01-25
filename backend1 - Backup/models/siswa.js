const mongoose = require('mongoose')
const Schema = mongoose.Schema

const siswaSchema = new Schema({
  nis: {
    type: String,
    indexes: {
      unique: true
    }
  },
  password: {
    type: String
  },
  nama: {
    type: String
  },
  email: {
    type: String
  },
  tgllahir: {
    type: String
  },
  notelepon: {
    type: String
  },
  jeniskelamin: {
    type: String
  }
})

module.exports = mongoose.model('siswa', siswaSchema)