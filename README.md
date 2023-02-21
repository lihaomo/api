# api

1.查询用户余额

  请求的地址：http://localhost:8080/api/UserWallerController/querybalance
  请求的方式：GET
  
  参数              类型            说明
  username         String           用户名
  
  示例：
  http://localhost:8080/api/UserWallerController/querybalance?username=zhangsan
  
  返回参数说明：
  名称          类型          说明
  flag        boolean        业务处理结果
  message     String          返回说明
