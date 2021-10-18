<%--
  Created by IntelliJ IDEA.
  User: LE TUAN THANH
  Date: 10/11/2021
  Time: 7:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="customerEditURL" value="/admin/customer-edit"/>

<html>
<head>
    <title>chỉnh sửa người dùng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Chỉnh sửa khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Thông tin khách hàng
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form:form class="form-horizontal" role="form" commandName="addCustomer" action="${customerEditURL}" id="formEdit"
                               method="GET">
                        <!--<form class="form-horizontal" role="form" id="formEdit">-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="fullName"> Tên khách hàng </label>

                            <div class="col-sm-9">
                                <input type="text" id="fullName" class="form-control" name = "fullName" value="${addCustomer.fullName}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="phone"> Số điện thoại </label>

                            <div class="col-sm-9">
                                <input type="text" id="phone" class="form-control" name = "phone" value="${addCustomer.phone}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="company"> Tên công ty </label>

                            <div class="col-sm-9">
                                <input type="text" id="company" class="form-control" name = "company" value="${addCustomer.company}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="demand"> Nhu cầu </label>

                            <div class="col-sm-9">
                                <input type="text" id="demand" class="form-control" name = "demand" value="${addCustomer.demand}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="note"> Ghi chú </label>

                            <div class="col-sm-9">
                                <input type="text" id="note" class="form-control" name = "note" value="${addCustomer.note}"/>
                            </div>

                        </div>


                        <div class="space-4"></div>
                        <form:hidden path="id" cssClass="col-xs-10 col-sm-12" ></form:hidden>
                        <div class="col-sm-3 control-label no-padding-right" style="margin-left: 200px">
                            <button type="button" class="btn btn-success" data-dismiss="modal" id="btnAddCustomer">Thêm Khách hàng</button>
                            <button type="button" class="btn btn-rotate" data-dismiss="modal">Hủy</button>
                        </div>


                        <!--</form> -->
                    </form:form>
                </div>


            </div><!-- /.col -->
        </div><!-- /.row -->
        </br>

    </div><!-- /.page-content -->
</div>
</div><!-- /.main-content -->

<script>
    $('#btnAddCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formEdit').serializeArray();
        $.each(formData,function (inddex, v) {
            data[""+v.name+""]=v.value;
        });
        $.ajax({
            url: "http://localhost:8080/api/customer",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.assign('http://localhost:8080/admin/customer-list');
                console.log("success");
            },
            error: function (res) {
                console.log("failed");
                console.log(res);
            }
        });
    });
</script>
</body>
</html>
