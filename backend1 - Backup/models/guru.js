const mongoose = require('mongoose')
const Schema = mongoose.Schema

const guruSchema = new Schema({
  nign: {
    type: String,
    indexes: {
      unique: true
    }
  },
  password: {
    type: String
  },
  namaguru: {
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

module.exports = mongoose.model('guru', guruSchema)