function addJSON() {
    var sendData = {};
    $('#addDiv input').each( function (index) {
        if(index == 0) {
            sendData.email = $( this ).val();
        } else if(index == 1) {
            sendData.login = $( this ).val();
        } else if(index == 2) {
            sendData.password = $( this ).val();
        }
    });
    sendData.roles = [];
    $('#roles input:checked').each( function (index) {
        sendData.roles.push({
            name: $( this ).val()
        });
    });
    $.ajax({
        url: './user-rest/user/add',
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        success: function(user){
            window.location.href = "./admin";
        },
        data: JSON.stringify(sendData)
    })
}

$( document ).ready(function() {
    $.ajax({url: "./user-rest/role/all", success: function(roles){
            roles.forEach(function(item, i) {
                $('#roles').append(`
                <div class="form-check form-check-inline">
                 <input type="checkbox" class="form-check-input" value="${item.name}" />
                 <label class="form-check-label">${item.name}</label>
                </div>   
                `);
            });
    }});
});
