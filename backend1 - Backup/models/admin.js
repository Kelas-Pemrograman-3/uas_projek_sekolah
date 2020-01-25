const mongoose = require('mongoose')
const Schema = mongoose.Schema

const adminSchema = new Schema({
  user: {
    type: String,
    indexes: {
      unique: true
    }
  },
  password: {
    type: String
  }
})

module.exports = mongoose.model('admin', adminSchema)