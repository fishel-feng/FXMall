const express = require('express');
const router = express.Router();

const User = require('./../models/user')

router.post('/login', function (req, res, next) {
  let param = {
    userName: req.body.userName,
    userPwd: req.body.userPwd
  }
  User.findOne(param, (err,doc)=>{
    if(err){
      res.json({
        status: '1',
        msg: err.message
      });
    }else{
      if(doc){
        res.cookie('userId', doc.userId, {
          path: '/',
          maxAge: 1000*60*60
        });
        res.cookie('userName', doc.userName, {
          path: '/',
          maxAge: 1000*60*60
        });
        // req.session.user = doc;
        res.json({
          status: '0',
          msg: '',
          result: {
            userName: doc.userName
          }
        });
      }
    }
  });
});

router.post('/logout',(req,res,next)=>{
  res.cookie('userId', '', {
    path: '/',
    maxAge: -1
  });
  res.json({
    status: '0',
    msg: '',
    result: ''
  });
});

router.get('/checkLogin',(req,res,next)=>{
  if(req.cookies.userId){
    res.json({
      status: '0',
      msg: '',
      result: req.cookies.userName || ''
    });
  }else{
    res.json({
      status: '1',
      msg: 'no login',
      result: ''
    });
  }
});

module.exports = router;
