<%--
  Created by IntelliJ IDEA.
  User: LE TUAN THANH
  Date: 10/11/2021
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="loadCustomerAPI" value="/api/customer"/>
<c:url var="customerListURL" value="/admin/customer-list"/>

<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Danh sách khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm Kiếm</h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form commandName="modelSearch" action="${customerListURL}" id="listForm"
                                           method="GET">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="fullName">Tên khách hàng</label>
                                                <form:input path="fullName" cssClass="form-control"/>
                                            </div>

                                        </div>
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="phone">Di động</label>
                                                <form:input path="phone" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="email">email</label>
                                                <form:input path="email" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <security:authorize access="hasRole('manager')">
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Nhân Viên Phụ trách</label>
                                                    <form:select path="staffId" cssClass="form-control">
                                                        <form:option value="" label="--Chọn nhân Viên"/>
                                                        <c:forEach var="item" items="${staffMaps}">
                                                            <form:option value="${item.id}" label="${item.fullName}"/>
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </security:authorize>

                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12" style="margin-top: 30px">
                                            <button type="button" class="btn btn-primary" id="btnSearch">Tìm kiếm
                                            </button>

                                        </div>

                                    </div>
                                </form:form>

                            </div>
                        </div>
                    </div>
                </div><!-- /.col -->


                <div class="pull-right">
                    <a href="/admin/customer-edit" class="btn btn-white btn-default btn-round" data-toggle = "tooltip" title="Thêm khách hàng">
                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                    </a>
                <security:authorize access="hasRole('manager')">
                    <button class="btn btn-white btn-warning btn-round" data-toggle = "tooltip" id="btnDeleteCustomer" title="Xóa khách hàng">
                        <i class="fa fa-trash" aria-hidden="true"></i>
                    </button>
                </security:authorize>

                </div>
            </div>
            </br>
            <div class="row">
                <div class="col-xs-12">
                    <table id="customerList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Tên </th>
                            <th>Nhu cầu</th>
                            <th>Người nhập</th>
                            <th>Ngày nhập</th>
                            <th>Tình trạng</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${customers}">
                            <tr>
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" value="${item.id}" id="checkbox_2"/>
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>${item.fullName}</td>
                                <td>${item.demand}</td>
                                <td>${item.createdBy}</td>
                                <td>${item.createdDate}</td>
                                <td></td>
                                <td class="center">
                                    <security:authorize access="hasRole('manager')">
                                        <button class="btn btn-xs btn-info" data-toggle = "tooltip" title = "Giao khách hàng" onclick = "assignmentCustomer(${item.id})">
                                            <i class="fa fa-bars" aria-hidden="true"></i>
                                        </button>
                                    </security:authorize>
                                        <%--<a href=/admin/transaction-${item.id} class="btn btn-xs btn-info" data-toggle = "tooltip" title = "Quản lý giao dịch">--%>
                                            <%--<i class="ace-icon fa fa-cogs "></i>--%>
                                        <%--</a>--%>
                                        <a href=/admin/customer-edit-${item.id} class="btn btn-xs btn-info" data-toggle = "tooltip" title = "Sửa thông tin">
                                            <i class="ace-icon fa fa-pencil-square-o "></i>
                                        </a>

                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="assignmentModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal Header</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <input type="hidden" id="customerId" name="customerId" value="">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignment">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentCustomer(customerId) {
        openModalAssignmentCustomer();
        $('#customerId').val(customerId);
        loadStaff(customerId);
    }

    function loadStaff(customerId) {
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/api/customer/'+customerId+'/staffs',
            dataType: "json",
            success: function (response) {
                console.log('success');
                var row = '';
                $.each(response,function (index,item) {
                    row+='<tr>';
                    row+='<td class ="text-center"><input type="checkbox" name="checkList" value='+item.id+' id="checkbox_'+item.id+'" '+item.checked+'/></td>';
                    row+='<td class ="text-center">'+item.fullName+'</td>';
                    row+='</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    }

    function openModalAssignmentCustomer() {
        $('#assignmentModal').modal();
    }

    $('#btnDeleteCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        var customerIds = $('#customerList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data = customerIds;
        deleteCustomer(data);
    });

    $('#btnAssignment').click(function (e) {
        e.preventDefault();
        var data = {};
        data ['customerId'] = $('#customerId').val();
        var staffList =  $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffList;
        assignStaff(data);
        window.location.assign('http://localhost:8080/admin/customer-list');
    });
    function assignStaff(data) {
        $.ajax({
            url: "http://localhost:8080/api/customer/assignment",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                console.log("success");
            },
            error: function (res) {
                console.log("failed");
                console.log(res);
            }
        });
    }
    function deleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api/customer",
            data: JSON.stringify(data),
            // dataType: "json",
            contentType: "application/json",
            success: function (response) {
                window.location.assign('http://localhost:8080/admin/customer-list');
                console.log('success');
            },
            error: function (response) {
                console.log('failed');
            }
        });
    }
    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });
</script>
</body>
</html>

