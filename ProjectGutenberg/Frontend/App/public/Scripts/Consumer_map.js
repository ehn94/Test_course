
function loadMapFromTitle() {
    
    var input = $("#titleInput").val();

        $.ajax({
            url: "http://localhost:8080/ProjectGutenberg/api/api/getCitiesByBookTitle/" + input,
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
                mymap.clearLayers();
                    $.map(data, function (item, index) {
                        
                        var marker =   L.marker([data[index].latitude, data[index].longitude]).addTo(mymap);
                        marker.bindPopup("<b>" + data[index].city_name + "</b>").openPopup();
                    })
                $('#errorText').empty();
            }
        });

};
