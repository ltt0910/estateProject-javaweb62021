<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <form:form commandName="modelSearch" action="" id="listForm" method="GET">
                                <div class = "form-horizontal">
                                    <div class="form-group">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-6">
                                            <div>
                                                <label for = "name">Tên tòa nhà</label>
                                                <input type = "text" id = "name" class = "form-control" name="name"/>
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
                                                <label for = "districtId">Quận hiện có</label>
                                                <select class="form-control" id="districtId">
                                                    <option value="">--Chọn quận--</option>
                                                    <option value="Q1">Quận 1</option>
                                                    <option value="Q2">Quận 2</option>
                                                    <option value="Q4">Quận 4</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "ward">Phường</label>
                                                <input type = "text" id = "ward" class = "form-control" />
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "street">Đường</label>
                                                <input type = "text" id = "street" class = "form-control" />
                                            </div>
                                        </div>

                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->

                                    <div class="form-group">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-4">
                                            <div>
                                                <label for = "numberOfBasement">Số tầng hầm</label>
                                                <input type = "number" id ="numberOfBasement" class = "form-control" />
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
                                                <label for = "staffId">Nhân viên phụ trách</label>
                                                <select class="form-control" id="staffId">
                                                    <option value="">--Chọn nhân viên--</option>
                                                    <option value="nguyenvana">Nguyễn Văn A</option>
                                                    <option value="nguyenvanb">Nguyễn Văn B</option>
                                                    <option value="nguyenvanc">Nguyễn Văn C</option>
                                                </select>
                                            </div>
                                        </div>

                                    </div><!-- /.col -->
                                    <!-- ComboBox-->
                                    <div class="row">
                                        <div class="checkbox">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace" />
                                                <span class="lbl">Tầng trệt</span>
                                            </label>

                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace" />
                                                <span class="lbl">Nguyên căn</span>
                                            </label>

                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace" />
                                                <span class="lbl">Nội thất</span>
                                            </label>
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
                            <th>Địa chỉ</th>
                            <th>Tên quản lý</th>
                            <th>DT.Sàn</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" id="checkbox_building1" />
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>
                                <button class="btn btn-xs btn-info" data-toggle = "tooltip" title = "Giao tòa nhà" onclick = "assignmentBuilding(1)">
                                    <i class="fa fa-bars" aria-hidden="true"></i>
                                </button>
                            </td>
                        </tr>

                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace"  id="checkbox_building2"/>
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>
                                <button class="btn btn-xs btn-info" data-toggle = "tooltip" title = "Giao tòa nhà" onclick = "assignmentBuilding(2)">
                                    <i class="fa fa-bars" aria-hidden="true"></i>
                                </button>
                            </td>
                        </tr>

                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace"  id="checkbox_building3"/>
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>Abc</td>
                            <td>
                                <button class="btn btn-xs btn-info" data-toggle = "tooltip" title = "Giao tòa nhà" onclick = "assignmentBuilding(3)">
                                    <i class="fa fa-bars" aria-hidden="true"></i>
                                </button>
                            </td>
                        </tr>
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
                        <tr>
                            <td><input type="checkbox" id="checkbox_2" value="2" checked></td>
                            <td>Nguyễn Văn B</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" id="checkbox_3" value="3" ></td>
                            <td>Nguyễn Văn C</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" id="checkbox_4" value="4" ></td>
                            <td>Nguyễn Văn D</td>
                        </tr>
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

    </script>
</body>
</html>
