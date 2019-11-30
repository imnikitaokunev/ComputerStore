function findStreetNames() {
    const url = 'http://localhost:8080/testspring_war/streetNames';
    $.get(url, function (streets) {
        streets.forEach(function (street) {
            displayStreet(street);
        });
    })
}

function findOwnerNames() {
    const url = 'http://localhost:8080/testspring_war/ownerNames';
    $.get(url, function (owners) {
        owners.forEach(function (owner) {
            displayOwner(owner);
        });
    })
}

function displayStreet(street) {
    const contents = $('#streetNames');

    const markup = `<option value="${street.id}">${street.name}</option>`;
    contents.append(markup);
}

function displayOwner(owner) {
    const contents = $('#ownerNames');

    const markup = `<option value="${owner.id}">${owner.fullName}</option>`;
    contents.append(markup);
}

function submitAdd() {
    const ownerId = document.getElementById("ownerNames");
    const streetId = document.getElementById("streetNames");
    const price = document.getElementById("price");
    const roomsNumber = document.getElementById("roomsNumber");
    const floor = document.getElementById("floor");
    const hasElevator = document.getElementById("hasElevator");

    const flat = {
        "ownerId": ownerId.value,
        "streetId": streetId.value,
        "price": price.value,
        "roomsNumber": roomsNumber.value,
        "floor": floor.value,
        "hasElevator": hasElevator.checked
    };

    const url = 'http://localhost:8080/testspring_war/addFlat';
    $.post(url, flat, function () {
        alert("Flat data was successfully added");
        relocateToMainPage();
    })
}

function relocateToMainPage() {
    location.href = `http://localhost:8080/testspring_war`;
}