compare_list = [];
nHouses = 3;

//disable button
$(document).ready(function () {
    document.getElementById("compareHouses").disabled = true;
});

function button_comparator_clicked(button) {

    if (button.clicked == "true") {
        //change color
        button.style.backgroundColor = "rgba(252, 185, 65, 0.5)";
        button.clicked = "false";

        //remove from compare_list
        compare_list.pop(fromButtonToHouseDic(button));
    } else {
        if (add_to_compare_list(button)) {
            //change color
            button.style.backgroundColor = "rgba(252, 185, 65, 1)";
            button.clicked = "true";
        }
    }
    treatCompareHousesButton();
}


function add_to_compare_list(button) {
    if (compare_list.length < nHouses) {
        compare_list.push(fromButtonToHouseDic(button));
        return true;
    } else {
        alert("It is only possible to compare " + nHouses + " houses!");
        return false;
    }
}


function fromButtonToHouseDic(button) {

    var houseInfoDiv = (((button.parentElement).parentElement).parentElement);

    var houseSpecs = {}
    houseSpecs["name"] = (houseInfoDiv.getElementsByClassName("name_c"))[0].innerHTML;
    houseSpecs["lotSize"] = parseFloat((houseInfoDiv.getElementsByClassName("lotSize_c"))[0].innerHTML);
    houseSpecs["rooms"] = parseFloat((houseInfoDiv.getElementsByClassName("rooms_c"))[0].innerHTML);
    houseSpecs["bathrooms"] = parseFloat((houseInfoDiv.getElementsByClassName("bathrooms_c"))[0].innerHTML);
    houseSpecs["price"] = parseFloat((houseInfoDiv.getElementsByClassName("price_c"))[0].innerHTML);
    houseSpecs["garage"] = parseFloat((houseInfoDiv.getElementsByClassName("garage_c"))[0].innerHTML);

    return houseSpecs;
}


function compareHouses() {
    better = getMaxs()
    // modal styling

    // THEAD
    var theadTr = document.getElementById("comparator_thead_tr");

    var colLg = compare_list.length == 2 ? 4 : 3;
    var classBootstrap = " class=\"col-lg-" + colLg + "\" style=\"text-align:center";

    var innerHTMLThead = "<td" + classBootstrap + "\"></td>";
    compare_list.forEach(function (house) {
        innerHTMLThead += "<td" + classBootstrap + "\"><b>" + house["name"] + "</td>";
    });
    theadTr.innerHTML = innerHTMLThead;

    //TABLE BODY
    var tableBody = document.getElementById("comparator_tbody");
    var tableRow = "";

    var keys = Object.keys(compare_list[0]);

    var index = keys.indexOf("name");
    if (index > -1) {
        keys.splice(index, 1);
    }
    console.log(keys);

    keys.forEach(function (key) {
        console.log(key);
        tableRow += "<tr>";
        tableRow += "<td" + classBootstrap + "\"><b>" + key + "</td>";


        compare_list.forEach(function (house) {
            // better option
            console.log(house);
            console.log(better);
            if (house[key] == better[key])
                tableRow += "<td" + classBootstrap + "; background-color:rgba(135, 211, 124, 0.3)\">" + house[key] + "</td>";
            else
                tableRow += "<td" + classBootstrap + "\">" + house[key] + "</td>";
        });
        tableRow += "</tr>";
    });
    console.log(tableRow)
    tableBody.innerHTML = tableRow;
}


function getMaxs() {
    console.log(compare_list[0]);
    var keys = Object.keys(compare_list[0]);
    var index = keys.indexOf("name");
    if (index > -1) {
        keys.splice(index, 1);
    }
    var maxsDic = {};

    keys.forEach(function (key) {
        if (key != "price")
            maxsDic[key] = -999999999;
        else
            maxsDic[key] = 999999999;

        compare_list.forEach(function (house) {
            console.log(key);
            if (key != "price") {
                if (house[key] > maxsDic[key])
                    maxsDic[key] = house[key];
            } else {
                if (house[key] < maxsDic[key])
                    maxsDic[key] = house[key];
            }
        });
    });
    return maxsDic;
}

function treatCompareHousesButton() {
    if (compare_list.length > 1) {
        document.getElementById("compareHouses").disabled = false;
        return true;
    } else {
        document.getElementById("compareHouses").disabled = true;
        return false;
    }
}