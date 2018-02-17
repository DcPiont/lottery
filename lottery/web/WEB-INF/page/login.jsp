<%--
  Created by IntelliJ IDEA.
  User: DcPiont
  Date: 2018/2/11
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>
<head>
    <%@include file="head.jsp"%>
    <title>登录</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-12 text-center">
            用户名
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-md-12 col-xs-12 text-center">
            <input id="userName" placeholder="用户名">
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-12 text-center">
            密码
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-md-12 col-xs-12 text-center">
            <input id="password" placeholder="密码" type="password">
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-12 col-xs-12 text-center">
            <br>
            <button id="signIn" class="btn btn-primary" type="button">登录</button>
            <button id="sinUp" class="btn btn-default" type="button">注册</button>
        </div>
    </div>
</div>
<script>
   $(function () {
       $("#signIn").click(function () {
           var json = JSON.stringify({
               "userName":$("#userName").val(),
               "password":$("#password").val()
           })
           $.ajax({
               url:"<%=path%>/user/auth",
               type:"POST",
               contentType:"application/json",
               dataType:"json",
               data:json,
               success:function(data){
                   if(data.code==200){
                       location.href="<%=path%>/event/index"
                   }
               }
           })
       });
       $("#sinUp").click(function () {
           var json = JSON.stringify({
               "userName":$("#userName").val(),
               "password":$("#password").val()
           })
           $.ajax({
               url:"<%=path%>/user/signUp",
               type:"POST",
               contentType:"application/json",
               dataType:"json",
               data:json,
               success:function(data){
                   if(data.code==200){
                       location.href="<%=path%>/event/index"
                   }else{
                       alert(data.msg);
                   }
               }
           })
       })
   })
</script>
</body>
</html>
