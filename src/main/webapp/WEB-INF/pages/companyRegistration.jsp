<%--
  Created by IntelliJ IDEA.
  User: nzepa
  Date: 4/6/2020
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Register Company</title>
</head>
<body class="hold-transition sidebar-mini">
<section class="content">
    <div class="container-fluid">
        <div class="card card-info">
            <form id="companyRegistrationFormId" action="<c:url value='/companyRegistration'/>"
                  method="post">
                <div class="card-body">
                    <input type="hidden" class="form-control" name="companyId" id="companyId">
                    <fieldset>
                        <legend>Enter company details</legend>
                        <div class="form-group row">
                            <label for="companyId" class="col-sm-2 col-form-label text-sm required">Company Name</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control form-control-sm" name="companyName"
                                       id="companyName" required="true">
                            </div>
                        </div>

                        <div class="form-group row">

                            <label for="companyName" class="col-sm-2 col-form-label text-sm">Description</label>

                            <div class="col-sm-10">
                                <textarea class="form-control form-control-sm" name="description"
                                          id="description"></textarea>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label text-sm">Status</label>

                            <div class="col-md-4">
                                <input type="radio" name="status" class="statusTag" id="statusTagActive"
                                       value="A"/>
                                <label for="statusTagActive"/>Active</label>

                                <input type="radio" name="status" class="statusTag" id="statusTagInactive"
                                       value="I" checked/>
                                <label for="statusTagInactive"/>Inactive</label>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-2"></div>
                            <%--<security:authorize access="hasRole('1012-ADD')">--%>
                            <div class="col-md-2">
                                <input type="submit" id="btnSave" value="Save"
                                       class="btn btn-primary btn-block"/>
                            </div>

                            <%--</security:authorize>--%>
                            <div class="col-md-2">
                                <input type="button" id="btnDelete" value="Delete"
                                       class="btn btn-danger btn-block" disabled/>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Company List</legend>
                        <div class="card">
                            <!-- /.card-header -->
                            <div class="card-body">
                                <table id="listTableId" class="table table-bordered table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th class="hidden">Id</th>
                                        <th class="hidden">Status</th>
                                        <th>Sl. No</th>
                                        <th>Company Name</th>
                                        <th>Description</th>
                                        <th>Status</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
