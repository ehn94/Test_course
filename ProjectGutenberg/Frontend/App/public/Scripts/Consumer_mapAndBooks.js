
function loadBookListAndMapFromAuthor() {
    
    var input = $("#authorInput").val();
        $.ajax({
            url: "http://localhost:8080/ProjectGutenberg/api/api/getBookAuthorCityByAuthor/" + input,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('.dataTable .tbody').empty();
                $('#errorText').empty().append("Could not find any results");
            }
        }).then(function (data) {
            console.log(data);
            if (data.length === 0) {
                console.log("EMPTY");
                $('#errorText').empty().append("Could not find any results");
                $('.dataTable .tbody').empty();
            }
            else {
                $('.dataTable .tbody').empty().append(
                    $.map(data, function (item, index) {
                        for(var i = 0; i < data[index].cities.length; i++){
                            var marker =   L.marker([data[index].cities[i].latitude, data[index].cities[i].longitude]).addTo(mymap);
                            marker.bindPopup("<b>" + data[index].cities[i].city_name + "</b><br>" + data[index].book_title).openPopup();
                        }
                        return '<tr><td>' + data[index].book_title + '</td></tr>';
                    }).join());
                $('#errorText').empty();

            }
    });
};
