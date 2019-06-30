var postData = {};

String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};

function postSearch() {

    // Reset compare list when searching for houses
    compare_list = [];

    var name = $("#name").val();
    var location = $("#location").val();
    var min_price = $("#min_price").text().split("$")[0].replaceAll(" ","");
    var max_price = $("#max_price").text().split("$")[0].replaceAll(" ","");
    var min_rooms = $("#min_rooms").text().replaceAll(" ","");
    var max_rooms = $("#max_rooms").text().replaceAll(" ","");

    var queryUrl = 
    "/propertiesSearch?"
    + "name=" + name
    + "&location=" + location
    + "&min_price=" + min_price
    + "&max_price="  + max_price
    + "&min_rooms="  + min_rooms
    + "&max_rooms="+ max_rooms;

    $.ajax({
        type: "GET",
        url: queryUrl,
        success: function (response) {
            document.getElementById("housesRow").innerHTML = "";
            var innerHouseRow = "";

            if (response.length > 0) {
                innerHouseRow += 
                '<div class="col-lg-12" style="text-align: right; padding: 10px">' +
                    '   <button disabled onclick="compareHouses()" data-toggle="modal" data-target="#myModal" id="compareHouses" type="button" class="btn btn-md btn-success">Compare Selected Houses</button>' +
                '</div>';
            }
            else{
                innerHouseRow += 
                '<div th:if="!${has_elements}">' +
                '<h3>No available properties...</h3>' + 
                '</div>';
            }

            response.forEach(element => {
                innerHouseRow +=
                    '   <div class="col-lg-4 col-md-4 col-md-6">' +
                    '        <div class="room-items">' +
                    '            <div class="room-img" style="height:100% !important">' +
                    '                <img src="' + element["photos"][0]["photo"] + '"/>' +
                    '                   <a class="room-content">' +
                    '                    <button onclick="button_comparator_clicked(this)" clicked="false" type="button" class="btn btn-sm btn-warning" style="background-color: rgba(252, 185, 65, 0.5)">Compare</button>' +
                    '                </a>' +
                    '            </div>' +
                    '            <div class="room-text">' +
                    '                <div class="room-details">' +
                    '                    <div class="room-title">' +
                    '                        <h5 class="name_c">' + element["name"] + '</h5>' +
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
                    '                            <span class="lotSize_c">' + element["habSpace"] + '</span> <span>sqft</span>' +
                    '                        </div>' +
                    '                        <div class="beds">' +
                    '                            <p>Rooms</p>' +
                    '                            <img src="img/rooms/bed.png" alt="">' +
                    '                            <span class="rooms_c">' + element["nrooms"] + '</span>' +
                    '                        </div>' +
                    '                        <div class="baths">' +
                    '                            <p>Baths</p> ' +
                    '                            <img src="img/rooms/bath.png" alt="">' +
                    '                            <span class="bathrooms_c">' + element["nbathrooms"] + '</span>' +
                    '                        </div>' +
                    '                        <div class="garage">' +
                    '                            <p>Garage</p>' +
                    '                            <img src="img/rooms/garage.png" alt="">' +
                    '                            <span class="garage_c">' + element["garage"] + '</span>' +
                    '                        </div>' +
                    '                    </div> ' +
                    '                </div> ' +
                    '                <div class="room-price"> ' +
                    '                    <p>Price Per Month</p> ' +
                    '                    <span>$</span><span class="price_c">' + element["price"] + '</span> ' +
                    '                </div> ' +
                    '                <a href="/getProperty?id=' + element["houseId"] + '" class="site-btn btn-line">View Property</a> ' +
                    '            </div> ' +
                    '        </div> ' +
                    '    </div>';
            });

            document.getElementById("housesRow").innerHTML = innerHouseRow;

            // For testing Purposes
            console.log(response);
        },
    });
}