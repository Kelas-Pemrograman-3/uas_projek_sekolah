const mongoose = require('mongoose')
const Schema = mongoose.Schema

const spp = new Schema({
  nis: {
    type: String,
    required: true
  },
  nama: {
    type: String
  },
  spp_bulan: {
    type: String
  },
  status: {
    type: String
  }
})

module.exports = mongoose.model('spp', spp)