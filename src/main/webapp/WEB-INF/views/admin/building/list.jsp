<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingListURL" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                <li class="active">Tìm kiếm</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">
            <!-- /.ace-settings-container -->

            <div class="page-header">
                <h1>
                    Tìm kiếm
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Tìm kiếm</h4>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>

                    <div class = "widget-body">
                        <div class="widget-main">
                            <form:form commandName="modelSearch" action="${buildingListURL}" id="listForm" method="GET">
                                <div class = "form-horizontal">
                                    <div class="form-group">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-6">
                                            <div>
                                                <label for = "name">Tên tòa nhà</label>
                                                <form:input path="name" cssClass="form-control"></form:input>
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <div>
                                                <label for = "buildingArea">Diện tích sàn</label>
                                                <input type = "number" id = "buildingArea" class = "form-control" />
                                            </div>
                                        </div>

                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->

                                    <div class="form-group">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-4">
                                            <div>
                                                <label>Quận hiện có</label>
                                                <form:select path="districtCode" cssClass="form-control">
                                                    <form:option value="-1" label="--Quận"/>
                                                    <form:options items="${district}"/>
                                                </form:select>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "ward">Phường</label>
                                                <input type = "text" id = "ward" class = "form-control" name="ward" value="${modelSearch.ward}" />
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "street">Đường</label>
                                                <input type = "text" id = "street" class = "form-control" name="street" value="${modelSearch.street}" />
                                            </div>
                                        </div>

                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->

                                    <div class="form-group">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "numberOfBasement">Số tầng hầm</label>
                                                <input type = "number" id ="numberOfBasement" class = "form-control" name="numberOfBasement" />
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "derection">Hướng</label>
                                                <input type = "text" id = "derection" class = "form-control" />
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "level">Hạng</label>
                                                <input type = "text" id = "level" class = "form-control" />
                                            </div>
                                        </div>

                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->

                                    <div class="form-group">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-3">
                                            <div>
                                                <label for = "areaStart">Diện tích từ</label>
                                                <input type = "number" id ="areaStart" class = "form-control" />
                                            </div>
                                        </div>

                                        <div class="col-sm-3">
                                            <div>
                                                <label for = "areaEnd">Diện tích đến</label>
                                                <input type = "number" id = "areaEnd" class = "form-control" />
                                            </div>
                                        </div>

                                        <div class="col-sm-3">
                                            <div>
                                                <label for = "rentPriceStart">Giá thuê từ</label>
                                                <input type = "decimal" id = "rentPriceStart" class = "form-control" />
                                            </div>
                                        </div>

                                        <div class="col-sm-3">
                                            <div>
                                                <label for = "rentPriceEnd">Giá thuê đến</label>
                                                <input type = "decimal" id = "rentPriceEnd" class = "form-control" />
                                            </div>
                                        </div>

                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->

                                    <div class="form-group">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "nameManager">Tên quản lý</label>
                                                <input type = "text" id ="nameManager" class = "form-control" />
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "phoneManager">Điện Thoại quản lý</label>
                                                <input type = "text" id = "phoneManager" class = "form-control" />
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label>Nhân Viên Phụ trách</label>
                                                <form:select path="staffId" cssClass="form-control">
                                                    <form:option value="-1" label="--Chọn nhân Viên"/>
                                                    <form:options items="${staffMaps}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div><!-- /.col -->
                                    <!-- ComboBox-->
                                    <div class="row">
                                        <div class="checkbox">
                                            <c:forEach var="item" items="${buildingTypes}">
                                                <label class="pos-rel">
                                                    <input type="checkbox" class="ace" />
                                                    <span class="lbl">${item.value}</span>
                                                </label>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top:30px ">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-primary" id="btnSearch">Tìm Kiếm</button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>

                        </div>
                    </div>
                </div><!-- /.row -->
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class = "pull-right">
                        <a href="/admin/building-edit" class="btn btn-white btn-default btn-round" data-toggle = "tooltip" title="Thêm tòa nhà">
                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        </a>

                        <button class="btn btn-white btn-warning btn-round" data-toggle = "tooltip" id="btnDeleteBuilding" title="Xóa tòa nhà">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </button>
                    </div>
                </div>
            </div>
            <br/>
            <div class = "row">
                <div class="col-xs-12">
                    <table class="table table-striped table-bordered table-hover" id="buildingList">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" />
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên sản phẩm</th>
                            <th>Số Tầng Hầm</th>
                            <th>Địa chỉ</th>
                            <th>Tên quản lý</th>
                            <th>DT.Sàn</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${buildings}">
                            <tr>
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" />
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>${item.name}</td>
                                <td>${item.numberOfBasement}</td>
                                <td>${item.address}</td>
                                <td>${item.managerName}</td>
                                <td>${item.floorArea}</td>
                                <td>${item.rentPrice}</td>
                                <td>${item.serviceFee}</td>
                                <td>
                                    <button class="btn btn-xs btn-info" data-toggle = "tooltip" title = "Giao tòa nhà" onclick = "assignmentBuilding(1)">
                                        <i class="fa fa-bars" aria-hidden="true"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table" id="staffList">
                    <thead class="thead-dark">
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên Nhân Viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${staffMaps}">
                        <tr>
                            <td><input type="checkbox" id="checkbox_2" value="${staffMaps.g}" checked></td>
                            <td>${item.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id ="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%--<script !src=""></script>--%>
<script src="assets/js/jquery.2.1.1.min.js"></script>
    <script>
        function assignmentBuilding(buildingId){
            openModalAssignmentBuilding();
            $('#buildingId').val(buildingId);
            console.log($('#buildingId').val());
        }

        function openModalAssignmentBuilding(){
            $('#assignmentBuildingModal').modal();
        }
        $('#btnAssignBuilding').click(function (e) {
            e.preventDefault();
            var data = {};
            data ['buildingId'] = $('#buildingId').val();
            var staffList =  $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            data['staffs'] = staffList;
            assignStaff(data);
        });

        function assignStaff(data) {
            $.ajax({
                url: "http://localhost:8080/api/assigntmentBuilding",
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
        $('#btnDeleteBuilding').click(function (e) {
            e.preventDefault();
            var data={};
            var buildingIds =  $('#buildingList').find('tbody input[type = checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            data['buildingIds'] = buildingIds;
            deleteBuilding(data);
        });

        function deleteBuilding(data) {
            $.ajax({
                url: "http://localhost:8080/api/building",
                type: "DELETE",
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
        $('#btnSearch').click(function (e) {
            e.preventDefault();   //ngăn truy xuất vào link k mong muốn
            $('#listForm').submit();
        });


    </script>
</body>
</html>
