<%--
  Created by IntelliJ IDEA.
  User: DcPiont
  Date: 2018/2/16
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>详情</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <ul class="nav nav-tabs">
            <li id="show" role="presentation" class="active"><a href="javascript:void(0);">选择</a></li>
            <li id="add" role="presentation"><a href="javascript:void(0);">添加</a></li>
            <li role="presentation"><a href="javascript:future();">一键黑幕</a></li>
        </ul>
    </div>
</div>
<div id="showList" class="container-fluid">
    <div class="row-fluid text-center">
        <table class="table">
            <thead>
                <tr><th>名称</th><th id="userName">所有者</th><th>需求数</th></tr>
            </thead>
            <tbody></tbody>
        </table>
        <br>
        <button id="addNeed" class="btn btn-primary" type="button">添加心愿</button>
    </div>
</div>
<div id="showAdd" class="container-fluid" style="display: none">
    <div class="row-fluid">
        <div class="col-md-12 text-center">
            礼物
        </div>
        <div class="row-fluid">
            <div class="col-md-12 col-xs-12 text-center">
                <input id="presentName" placeholder="名称">
            </div>
            <div class="col-md-12 col-xs-12 text-center">
                <br>
                <button id="addPresent" class="btn btn-primary" type="button">添加</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        var isEnd = false;
        var presentId = 0;
        var prsnId = 0;
        $.ajax({//获取事件是否结束
            url:"<%=path%>/event/getEventById",
            async:false,
            data:{
                "eventId":${eventId}
            },
            dataType:'json',
            success:function (data) {
                if(data.code==200){
                    isEnd=data.content.end;
                }
            }
        });
        $.ajax({//当前用户是否已选择心愿奖品
            url:"<%=path%>/present/getSelectedPresent",
            async:false,
            data:{
                "eventId":${eventId}
            },
            dataType:'json',
            success:function (data) {
                if(data.code==200){
                    if(data.content.presentId != 0){
                        presentId = data.content.presentId;
                        isSelected = true;
                    }
                }
            }
        })
        if(!isEnd){
            $.ajax({
                url:"<%=path%>/present/getAllPresent",
                data:{
                    "eventId":${eventId}
                },
                dataType:"json",
                success:function (data) {
                    if(data.code == 200){
                        var list = data.content
                        for (var i = 0; i < list.length; i++){
                            var string = list[i].id==presentId?"info":"";
                            $("tbody").append('<tr class="'+string+'" prsnId="'+list[i].id+'"><td>'+list[i].presentName+'</td><td>'+list[i].userName+'</td><td>'+list[i].weight+'</td></tr>');
                        }
                    }
                }
            });
        }else{
            $("#userName").text("获奖者");
            $.ajax({
                url:"<%=path%>/present/getWinner",
                data:{
                    "eventId":${eventId}
                },
                dataType:"json",
                success:function (data) {
                    if(data.code == 200){
                        var list = data.content
                        for (var i = 0; i < list.length; i++){
                            $("table").append('<tr><td>'+list[i].presentName+'</td><td>'+list[i].userName+'</td><td>'+list[i].weight+'</td></tr>');
                        }
                    }
                }
            });
        }
        $('#show').click(function () {
            $(this).addClass('active');
            $('#showAdd').hide();
            $('#showList').show();
            $('#add').removeClass('active');
        });
        $('#add').click(function () {
            $(this).addClass('active');
            $('#showList').hide();
            $('#showAdd').show();
            $('#show').removeClass('active');
        });
        $('#addPresent').click(function () {
            $.ajax({
                url:'<%=path%>/present/addPresent',
                type:"POST",
                contentType:"application/json",
                data:JSON.stringify({
                    "presentName":$("#presentName").val(),
                    "eventId":${eventId}
                }),
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        alert('添加成功');
                        location.reload();
                    }else{
                        alert(data.msg);
                    }
                }
            })
        });
        if(presentId == 0){
            $('tbody').on('click','tr',function () {
                $('addNeed').removeClass('disabled');
                $(this).addClass('info').siblings().removeClass('info');
                prsnId = $(this).attr('prsnId');
            });
            $('#addNeed').click(function () {
                $.ajax({
                    url:'<%=path%>/present/addNeed',
                    type:'post',
                    data:{
                        'eventId':${eventId},
                        'presentId':prsnId
                    },
                    dataType:'json',
                    success:function (data) {
                        if(data.code==200){
                            alert('添加成功');
                            location.reload();
                        }
                    }
                })
            })
        }else{
            $('#addNeed').addClass('disabled');
        }
    });
    function future() {
        alert("盲生,你发现了华点");
    }
</script>
</body>
</html>
