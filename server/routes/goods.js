const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const Goods = require('../models/goods');

mongoose.connect('mongodb://127.0.0.1:27017/FXMall');

mongoose.connection.on('connected', () => {
  console.log('数据库连接成功');
});

mongoose.connection.on('error', () => {
  console.log('数据库连接失败');
});

mongoose.connection.on('disconnected', () => {
  console.log('数据库断开');
});

router.get('/', (req, res, next) => {
  let page = parseInt(req.param('page'));
  let pageSize = parseInt(req.param('pageSize'));
  let priceLeval = req.param('priceLeval')
  let sort = req.param('sort');
  let skip = (page - 1) * pageSize;
  let priceGt = '',
    priceLte = '';
  let params = {};
  if (priceLeval !== 'all') {
    switch (priceLeval) {
      case '0':
        priceGt = 0;
        priceLte = 100;
        break;
      case '1':
        priceGt = 100;
        priceLte = 500;
        break;
      case '2':
        priceGt = 500;
        priceLte = 1000;
        break;
      case '3':
        priceGt = 1000;
        priceLte = 5000;
        break;
    }
    params = {
      salePrice: {
        $gt: priceGt,
        $lte: priceLte
      }
    }
  }
  let goodsModel = Goods.find(params).skip(skip).limit(pageSize);
  goodsModel.sort({
    'salePrice': sort
  });
  goodsModel.exec((err, doc) => {
    if (err) {
      res.json({
        status: '1',
        msg: err.message
      });
    } else {
      res.json({
        status: '0',
        msg: '',
        result: {
          count: doc.length,
          list: doc
        }
      });
    }
  });
});

module.exports = router;
