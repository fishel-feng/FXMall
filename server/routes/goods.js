const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const Goods = require('../models/goods');
const User = require('../models/user');

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

router.get('/list', (req, res, next) => {
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

router.post('/addCart', (req, res, next) => {
  let userId = '100000077',
    productId = req.body.productId;
  User.findOne({
    userId: userId
  }, (err, userDoc) => {
    if (err) {
      res.json({
        status: '1',
        msg: err.message
      });
    } else {
      console.log(userDoc);
      if (userDoc) {
        let goodsItem = '';
        userDoc.cartList.forEach(item => {
          if (item.productId === productId) {
            goodsItem = item;
            item.productNum++;
          }
        });
        if (goodsItem) {
          userDoc.save((err2, doc2) => {
            if (err2) {
              res.json({
                status: '1',
                msg: err2.message
              });
            } else {
              res.json({
                status: '0',
                msg: '',
                result: 'suc'
              });
            }
          });
        } else {
          Goods.findOne({
            productId: productId
          }, (err1, doc) => {
            if (err1) {
              res.json({
                status: '1',
                msg: err1.message
              });
            } else {
              if (doc) {
                doc.productNum = 1;
                doc.checked = 1;
                userDoc.cartList.push(doc);
                userDoc.save((err2, doc2) => {
                  if (err2) {
                    res.json({
                      status: '1',
                      msg: err2.message
                    });
                  } else {
                    res.json({
                      status: '0',
                      msg: '',
                      result: 'suc'
                    });
                  }
                });
              }
            }
          });
        }
      }
    }
  });
});

module.exports = router;
