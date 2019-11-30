function getFlats() {
    const url = 'http://localhost:8080/testspring_war/flats';
    $.get(url, function (flats) {
        flats.forEach(function (flat) {
            displayFlat(flat)
        });
    })
}

function displayFlat(flat) {
    const contents = $('#flatList');

    const id = flat.id;
    const streetName = flat.streetName;
    const price = flat.price;
    const ownerPhone = flat.ownerPhone;

    const markup = `<tr>
        <td>${streetName}</td>
        <td>${price}</td>
        <td>${ownerPhone}</td>
        <td>
            <button onclick="relocateToEditFlatPage(${id})">edit</button>
            <button onclick="deleteFlat(${id})">delete</button>
            <button onclick="relocateToGetMoreInfoPage(${id})">get more</button>
         </td>
        </tr>`;
    contents.append(markup);
}

function deleteFlat(id) {
    const url = `http://localhost:8080/testspring_war/deleteFlat/${id}`;
    $.get(url, function () {
        alert("Flat data was successfully deleted");
        location.reload();
    })
}

function relocateToEditFlatPage(id) {
    location.href = `http://localhost:8080/testspring_war/editFlat/` + id;
}

function relocateToAddFlatPage() {
    location.href = `http://localhost:8080/testspring_war/addFlat`;
}

function relocateToAddHousePage() {
    location.href = `http://localhost:8080/testspring_war/addHouse`;
}

function relocateToEditHousePage(id) {
    location.href = `http://localhost:8080/testspring_war/editHouse/` + id;
}

function relocateToGetMoreInfoPage(id) {
    location.href = `http://localhost:8080/testspring_war/flatInfo/` + id;
}

function getHouses() {
    const url = 'http://localhost:8080/testspring_war/houses';
    $.get(url, function (houses) {
        houses.forEach(function (house) {
            displayHouse(house)
        });
    })
}

function displayHouse(house) {
    const contents = $('#houseList');

    const id = house.id;
    const streetName = house.streetName;
    const price = house.price;
    const ownerPhone = house.ownerPhone;

    const markup = `<tr>
        <td>${streetName}</td>
        <td>${price}</td>
        <td>${ownerPhone}</td>
        <td>
            <button onclick="relocateToEditHousePage(${id})">edit</button>
            <button onclick="deleteHouse(${id})">delete</button>
            <button onclick="getMore(${id})">get more</button>
         </td>
        </tr>`;
    contents.append(markup);
}

function deleteHouse(id) {
    const url = `http://localhost:8080/testspring_war/deleteHouse/${id}`;
    $.get(url, function () {
        alert("House data was successfully deleted");
        location.reload();
    })
}
