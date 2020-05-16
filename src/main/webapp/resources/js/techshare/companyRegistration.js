companyRegistration = (function () {
    "use strict";
    var form = $('#companyRegistrationFormId');
    var isSubmitted = false;

    function _baseURL() {
        return 'companyRegistration/';
    }

    function saveCompanyRegistration() {
        $('#btnSave').on('click', function () {
            $.validator.setDefaults({
                submitHandler: function () {
                    var url = _baseURL() + 'saveCompanyRegistration';
                    var data = $(form).serializeArray();

                    isSubmitted = true;
                    $('#btnSave').attr('disabled', true);

                    $.ajax({
                        url: url,
                        type: 'post',
                        data: data,
                        processData: true,
                        success: function (res) {
                            if (res.responseStatus == 1) {
                                successMsg(res.responseText);
                                getCompanyList();
                            } else {
                                warningMsg(res.responseText);
                            }
                        }, complete: function () {
                            isSubmitted = false;
                            $('#btnSave').attr('disabled', false);
                        }
                    });
                }
            });

            form.validate({
                rules: {
                    companyName: {
                        required: true
                    }
                },
                messages: {
                    companyName: {
                        required: "This field is required"
                    }
                },
                errorElement: 'span',
                errorPlacement: function (error, element) {
                    error.addClass('invalid-feedback');
                    element.closest('.col-sm-10').append(error);
                },
                highlight: function (element, errorClass, validClass) {
                    $(element).addClass('is-invalid');
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).removeClass('is-invalid');
                }
            });

        });
    }

    function getCompanyList() {
        var url = _baseURL() + 'getCompanyList';
        $.ajax({
            url: url,
            type: 'GET',
            success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    var dataTableDefinition = [
                        {"data": "companyId", class: "companyId hidden"}
                        , {"data": "status", class: "status hidden"}
                        , {
                            "mRender": function (data, type, row, meta) {
                                return meta.row + 1;
                            }
                        }
                        , {"data": "companyName", class: "companyName align-middle"}
                        , {"data": "description", class: "description align-middle"}
                        , {
                            "data": "status", class: "statusText align-middle",
                            "mRender": function (data) {
                                var statusTag;
                                if (data === 'I') {
                                    statusTag = 'Inactive';
                                } else {
                                    statusTag = 'Active';
                                }
                                return statusTag;
                            }
                        }, {
                            "data": "null", class: "btnView align-middle",
                            "mRender": function () {
                                return "<a id='btnView' class='btn btn-sm btn-info align-middle form-control-sm'>View</a>";
                            }
                        }
                    ];
                    $('#listTableId').DataTable({
                        data: data
                        , columns: dataTableDefinition
                        , destroy: true
                        , bSort: false
                        //, pageLength: 50
                    });
                }
            }
        });
    }

    function btnView() {
        $('#listTableId tbody').on('click', 'tr #btnView', function () {
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var companyId = selectedRow.find('.companyId').text();
            var companyName = selectedRow.find('.companyName').text();
            var description = selectedRow.find('.description').text();
            var status = selectedRow.find('.status').text();
            selectedRow.removeClass('selected');
            $('#companyId').val(companyId);
            $('#companyName').val(companyName);
            $('#description').val(description);
            if (status == 'A') {
                $('#statusTagActive').prop('checked', true);
                $('#statusTagInactive').prop('checked', false);
            } else {
                $('#statusTagActive').prop('checked', false);
                $('#statusTagInactive').prop('checked', true);
            }
            $('#btnDelete').attr('disabled', false);
        });
    }

    function btnDelete() {
        $('#btnDelete').on('click', function () {
            var url = _baseURL() + 'deleteCompany';
            var companyId = $('#companyId').val();

            isSubmitted = true;
            $('#btnDelete').attr('disabled', true);

            $.ajax({
                url: url
                , type: 'post'
                , data: {companyId: companyId}
                , success: function (res) {
                    if (res.responseStatus == 1) {
                        successMsg(res.responseText);
                        getCompanyList();
                    } else {
                        warningMsg(res.responseText);
                    }
                }, complete: function () {
                    isSubmitted = false;
                    $('#btnDelete').attr('disabled', true);
                }
            });
        });
    }

    return {
        saveCompanyRegistration: saveCompanyRegistration
        , getCompanyList: getCompanyList
        , btnView: btnView
        , btnDelete: btnDelete
    }
})
();
$(document).ready(
    function () {
        companyRegistration.saveCompanyRegistration();
        companyRegistration.getCompanyList();
        companyRegistration.btnView();
        companyRegistration.btnDelete();
    });
