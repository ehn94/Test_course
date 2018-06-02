function fromCity() {
    
    var input = $("#cityInput").val();
        $.ajax({
            url: "http://localhost:8080/ProjectGutenberg/api/api/getBookAuthorByCity/" + input,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                
                $('#errorText').empty().append("Could not find any results");
                $('.dataTableCity .tbody').empty();
            }
        }).then(function (data) {
            console.log(data);
            if (data.length === 0) {
                console.log("EMPTY");
                $('#errorText').empty().append("Could not find any results");
                $('.dataTableCity .tbody').empty();
            }
            else {
                $('.dataTableCity .tbody').empty().append(
                    $.map(data, function (item, index) {
                        return '<tr><td>' + data[index].author_name + '</td><td>' + data[index].book_title + '</td></tr>';
                    }).join());
                $('#errorText').empty();

            }
        });
};

function fromGeo(){
    var latitude = $("#latitudeInput").val();
    var longitude = $("#longitudeInput").val();
    $.ajax({
        url: "http://localhost:8080/ProjectGutenberg/api/api/getBookCityByGeolocation/"+ latitude + "/" + longitude,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            
            $('#errorText').empty().append("Could not find any results");
            $('.dataTableCity .tbody').empty();
        }
    }).then(function(data){
        console.log(data);
    })
}