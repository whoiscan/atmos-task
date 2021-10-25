$(document).ready(function () {
    var newsId = '';
    var cancelId = '';
    var editId = '';
    var userTable = $('#userTable').DataTable({
        ajax: {
            url: "/admin/all/news/list",
            type: "GET",
            dataSrc: ''
        },
        columns: [
            {title: "ID", data: "id"},
            {title: "Дата публикации", data: "createdDate"},
            {title: "Дата одобрения", data: "approvedDate"},
            {title: "Текст", data: "description"},
            {title: "Логин", data: "userName"},
            {title: "Почта", data: "email"},
            {title: "Одобрено", data: "approved"},
            {title: "Статус", data: "status"},
            {
                title: "Операция", data: "id", render: function () {
                    return '<div class="row">\n' +
                        '    <div class="col mt-1 mb-1">\n' +
                        '        <button  type="button" class="btn btn-primary text-white btn-block btn-edit"><i class="fas fa-edit"></i> Одобрить</button>\n' +
                        '    </div>\n' +
                        '    <div class="col mt-1 mb-1" >\n' +
                        '        <button  data-toggle="modal" data-target="#modalDelete" class=" text-white btn btn-danger btn-block btn-delete" style="color: darkred"><i class="fab fa-bitbucket"> </i> Отменить</button>\n' +
                        '    </div>\n' +
                        '    <div class="col mt-1 mb-1" >\n' +
                        '        <button  data-toggle="modal" data-target="#modalChange" class=" text-white btn btn-info btn-block btn-change" style="color: green"><i class="fas fa-edit"></i>Изменить</button>\n' +
                        '    </div>\n' +
                        '</div>\n';
                }
            }
        ]
    });

    $(userTable.table().body()).on('click', '.btn-edit', function () {

        $('#userForm')[0].reset();
        $("#userForm input").removeAttr('checked');

        var data = userTable.row($(this).parents('tr')).data();
        newsId = data.id;
        $('#userModal').modal('toggle');
    });

    $('#save-news-button').click((e) => {
        e.preventDefault()

        const descriptionValue = $('#description').val()

        $.ajax({
            url: "/admin/board/approve/" + newsId,
            contentType: "application/json; charset=utf-8",
            method: 'POST',
            dataType: 'json',
            data: descriptionValue,
        });
        $('#userModal').modal('toggle');
        // $('#userTable').DataTable().ajax.reload();
        location.reload();

    })

    $(userTable.table().body()).on('click', '.btn-delete', function () {
        var data = userTable.row($(this).parents('tr')).data();
        cancelId = data.id;
        $('#userDeleteModal').modal('toggle');
    });

    $('#userDelBtn').click(function () {
        $.ajax({
            url: "/admin/board/cancel/" + cancelId,
            contentType: "application/json; charset=utf-8",
            method: 'POST',
            dataType: 'json',
        }).always(function () {
            userTable.ajax.reload();
            $('#userDeleteModal').modal('toggle');
        });
    });

    $(userTable.table().body()).on('click', '.btn-change', function () {
        var data = userTable.row($(this).parents('tr')).data();
        editId = data.id;
        $('#modalChange').modal('toggle');
    });

    $('#saveChange').click(function () {
        let text = $('.modalInput').val();
        if (text.length >= 500)
            alert("Ограничение текста 500 символов!!");
        else {
            $.ajax({
                url: "/api/board/edit/" + editId,
                contentType: "application/json; charset=utf-8",
                method: 'POST',
                dataType: 'json',
                data: text
            }).always(function () {
                userTable.ajax.reload();
                $('#modalChange').modal('toggle');
            });
        }
    });


})
;




