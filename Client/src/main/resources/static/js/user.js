$( document ).ready(function() {
    $.ajax({
        url: "./user-rest/role/all",
        success: function(roles){
        roles.forEach(function(item, i) {
            $('#roles').append(`
            <div class="form-check form-check-inline">
                <input type="checkbox" class="form-check-input" value=${item.name} />
                <label class="form-check-label">
                    ${item.name}
                </label
            </div>
            `);
        });
    }});
    $.ajax({url: "./user-rest/user/all", success: function(users){
        users.forEach(function(item, i) {
            var roles = "";
            item.roles.forEach(function(itemRole, j) {
                roles += itemRole.name + ";";
            });
            $('#users tbody:last-child').append(`
            <tr>
                <td> ${item.id}</td>
                <td> ${roles}</td>
                <td> ${item.login}</td>
                <td> ${item.password}</td>
                <td> ${item.email}</td>
                <td> 
                    <button class="btn btn-primary" onclick="clickFunction(${item.id})">Edit</button>
                </td>
            </tr>
            `);
        });
    }});
});

function clickFunction(id){
    $.ajax({url: './user-rest/user/id/' + id, success: function(user){
        $('#modalTitle').text("Edit user " + user.login);
        $('#modalDiv input').each( function (index) {
            if(index == 0) {
                $( this ).val(user.id);
            } else if(index == 1) {
                $( this ).val(user.email);
            } else if(index == 2) {
                $( this ).val(user.login);
            } else if(index == 3) {
                $( this ).val(user.password);
            }
        });
        $('#roles input').prop( "checked", false );
        user.roles.forEach(function(item, i) {
            $('#roles input[value='+ item.name + ']').prop( "checked", true );
        });
        $('#modal').modal();
    }});
}

function edit(){
    var sendData = {};
    $('#modalDiv input').each( function (index) {
        if(index == 0) {
            sendData.id = $( this ).val();
        } else if(index == 1) {
            sendData.email = $( this ).val();
        } else if(index == 2) {
            sendData.login = $( this ).val();
        }  else if(index == 3) {
            sendData.password = $(this).val();
        }
    });
    sendData.roles = [];
    $('#roles input:checked').each( function (index) {
        sendData.roles.push({
            name: $( this ).val()
        });
    });
    $.ajax({
        url: './user-rest/user/edit',
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        success: function(user){
            window.location.href = "./admin";
        },
        data: JSON.stringify(sendData)
    })
}
