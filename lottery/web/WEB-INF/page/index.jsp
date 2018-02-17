<%--
  Created by IntelliJ IDEA.
  User: DcPiont
  Date: 2018/2/16
  Time: 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <style>
        input{
            -webkit-user-select:auto;
        }
    </style>
    <title>主页</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <ul class="nav nav-tabs">
            <li id="join" role="presentation" class="active"><a>抽奖</a></li>
            <li id="result" role="presentation"><a >结果</a></li>
        </ul>
    </div>
</div>
<div id="div-join" class="container-fluid">
    <div class="row-fluid">
        <table class="table text-center">
            <thead><tr><th class="text-center">名称</th><th class="text-center">参与人数</th></tr></thead>
            <tbody id="joinList"></tbody>
        </table>
    </div>
</div>
<div id="div-result" class="container-fluid" style="display: none">
    <div class="row-fluid">
        <table class="table text-center">
            <thead><tr><th class="text-center">名称</th><th class="text-center">参与人数</th></tr></thead>
            <tbody id="resultList"></tbody>
        </table>
    </div>
</div>
<script>
    $(function () {
        $.ajax({//参与抽奖列表
            url:"<%=path%>/event/getLatestStartedEvent",
            dataType:"json",
            success:function (data) {
                if(data.code == 200){
                    var event = data.content;
                    if(event.id==0){
                        $('#joinList').empty();
                    }else{
                        $("#joinList").append('<tr id="'+event.id+'" class="redirect"><td>'+event.eventName+'</td><td>'+event.count+'</td></tr>');
                    }
                }
            }
        });
        $.ajax({//结果列表
            url:"<%=path%>/event/getAllEvent",
            dataType:"json",
            success:function (data) {
                if(data.code == 200){
                    var event = data.content;
                    if(event.id==0){
                        $('#resultList').empty();
                    }else{
                        for(var i = 0; i < event.length; i++){
                            $("#resultList").append('<tr id="'+event[i].id+'" class="redirect"><td>'+event[i].eventName+'</td><td>'+event[i].end+'</td></tr>');
                        }
                    }
                }
            }
        });
        $("tbody").on("click",".redirect",function () {
            location.href="<%=path%>/event/detail?eventId="+$(this).attr('id');
        });
        $('li').click(function () {
            $(this).addClass('active').siblings().removeClass('active');
            switch ($(this).attr('id')){
                case 'join':
                    $('#div-join').show();
                    $('#div-result').hide();
                    break;
                case 'result':
                    $('#div-result').show();
                    $('#div-join').hide();
                    break;
            }
        });
    })

</script>
</body>
</html>
