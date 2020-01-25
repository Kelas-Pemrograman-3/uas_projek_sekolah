const mongoose = require('mongoose')
const Schema = mongoose.Schema

const jadwal = new Schema({
  kodepelajaran: {
    type: String,
    required: true
  },
  namapelajaran: {
    type: String
  },
  jam: {
    type: String
  },
  hari: {
    type: String
  },
  ruangan: {
    type: String
  },
  nign: {
    type: String
  }, 
  namaguru: {
    type: String
  },  

  // guru: [
  //   {
  //     nign: String,
  //     namaguru: String
  //   }
  // ]
})

module.exports = mongoose.model('jadwal', jadwal)