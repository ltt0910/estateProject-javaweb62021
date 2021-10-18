<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: LE TUAN THANH
  Date: 10/14/2021
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý Giao dịch</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="row">
            <div class="col-xs-12">
                <c:forEach var="item" items="${transactionTypes}">
                    <label class="control-label no-padding-right" >${item.value} </label>
                    <input type="hidden" id="code" name="code" value="">
                    <security:authorize access="hasRole('staff')">
                        <button  class="btn btn-white btn-info btn-bold" data-toggle="tooltip" type="button" onclick="addNotes('${item.key}');">
                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        </button>
                    </security:authorize>
                    <hr>
                    <div class="col-xs-12">
                        <table id="buildingList" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="col-sm-3">Ngày tạo</th>
                                <th class="col-sm-6">Ghi chú</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="index" items="${transactions}">
                                <c:if test="${item.key.equals(index.code)}">
                                    <tr>
                                        <td>${index.createdDate}</td>
                                        <td>${index.note}</td>
                                    </tr>
                                </c:if>
                                <input type="hidden" id="customerid" class="form-control" value="${index.customerId}"/>
                            </c:forEach>
                            <security:authorize access="hasRole('staff')">
                                <input type="text" id="note_${item.key}" class="form-control" placeholder="Thêm note" name="note" value=""/>
                            </security:authorize>
                            </tbody>
                        </table>
                    </div>
                    </br>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/jquery.2.1.1.min.js"></script>
<script>
   function addNotes(code) {
       // e.preventDefault();
       var data = {};
       data['customerId'] = $('#customerid').val();
       data['code'] = code;
       data['note'] =$('#note_'+code).val();
       $.ajax({
           url: "http://localhost:8080/api/transaction",
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
   }
</script>
</body>
</html>
