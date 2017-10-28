const mongoose = require('mongoose');

const productSchema = new mongoose.Schema({
  "productId": String,
  "productName": String,
  "salePrice": Number,
  "checked": String,
  "productNum": Number,
  "productImage": String
});

module.exports = mongoose.model('Good', productSchema);
