var postData = {};

function postSearch() {
    var name = $("#name").val();
    var location = $("#location").val();
    var min_price = $("#min_price").text().split("$")[0];
    var max_price = $("#max_price").text().split("$")[0];
    var min_rooms = $("#min_rooms").text();
    var max_rooms = $("#max_rooms").text();

    postData["name"] = name;
    postData["location"] = location;
    postData["min_price"] = min_price;
    postData["max_price"] = max_price;
    postData["min_rooms"] = min_rooms;
    postData["max_rooms"] = max_rooms;

    $.ajax({
        type: "POST",
        url: "/",
        contentType: "application/json",
        success: function (response) {
            document.getElementById("housesRow").innerHTML = "";
            response.forEach(element => {
                document.getElementById("housesRow").innerHTML +=
                    '   <div class="col-lg-4 col-md-4 col-md-6">' +
                    '        <div class="room-items">' +
                    '            <div class="room-img" style="height:100% !important">' +
                    '                <img src="' + element["photos"][0]["photo"] + '"/>' +
                    '                <a href="#" class="room-content">' +
                    '                    <i class="flaticon-heart"></i>' +
                    '                </a>' +
                    '            </div>' +
                    '            <div class="room-text">' +
                    '                <div class="room-details">' +
                    '                    <div class="room-title">' +
                    '                        <h5>' + element["name"] + '</h5>' +
                    '                        <i class="flaticon-placeholder"></i> <span>' + element["address"] + '</span>' +
                    '                        ' +
                    '                    </div>' +
                    '                </div>' +
                    '                <div class="room-features">' +
                    '                    <div class="room-info">' +
                    '                        <div class="size">' +
                    '                            <p>Lot Size</p>' +
                    '                            <img src="img/rooms/size.png" alt="">' +
                    '                            <i class="flaticon-bath"></i>' +
                    '                            <span>' + element["habSpace"] + '</span> <span>sqft</span>' +
                    '                        </div>' +
                    '                        <div class="beds">' +
                    '                            <p>Rooms</p>' +
                    '                            <img src="img/rooms/bed.png" alt="">' +
                    '                            <span>' + element["nrooms"] + '</span>' +
                    '                        </div>' +
                    '                        <div class="baths">' +
                    '                            <p>Baths</p> ' +
                    '                            <img src="img/rooms/bath.png" alt="">' +
                    '                            <span>' + element["nbathrooms"] + '</span>' +
                    '                        </div>' +
                    '                        <div class="garage">' +
                    '                            <p>Garage</p>' +
                    '                            <img src="img/rooms/garage.png" alt="">' +
                    '                            <span>' + element["garage"] + '</span>' +
                    '                        </div>' +
                    '                    </div> ' +
                    '                </div> ' +
                    '                <div class="room-price"> ' +
                    '                    <p>Price Per Month</p> ' +
                    '                    <span>$</span><span>' + element["price"] + '</span> ' +
                    '                </div> ' +
                    '                <a href="/getProperty?id=' + element["houseId"] + '" class="site-btn btn-line">View Property</a> ' +
                    '            </div> ' +
                    '        </div> ' +
                    '    </div>';
            });


            console.log(response);
            console.log(response[0]["photos"][0]["photo"]);





        },
        data: JSON.stringify(postData)
    });
}