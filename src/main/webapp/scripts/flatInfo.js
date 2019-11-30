function showFlatInfo() {
    const id = findFlat();
    const url = `http://localhost:8080/testspring_war/findFlat/${id}`;
    $.get(url, function (flat) {
        displayFlat(flat);
    })
}

function findFlat() {
    const currentLocation = window.location.href;
    const regId = /([0-9]+)/g;
    const id = currentLocation.match(regId)[1];
    return id;
}

function displayFlat(flat) {
    const ownerId = flat.ownerId;
    const streetId = flat.streetId;
    const imgId = flat.imgId;
    displayOwner(ownerId);
    displayStreet(streetId);
    displayImage(imgId, flat);

    const contents = $('#flatInfo');

    const price = flat.price;
    const floor = flat.floor;
    const hasElevator = flat.hasElevator;
    const roomsNumber = flat.roomsNumber;

    const markup = `<p>price: ${price}</p>
                        <p>floor: ${floor}</p>
                        <p>has elevator: ${hasElevator}</p>
                        <p>rooms number: ${roomsNumber}</p>`;
    contents.append(markup);
}

function displayOwner(id) {
    const url = `http://localhost:8080/testspring_war/findOwner/${id}`;
    $.get(url, function (owner) {
        const contents = $('#flatInfo');

        const name = owner.name;
        const surname = owner.surname;
        const email = owner.email;
        const phone = owner.phone;

        const markup = `<p>owner name: ${name}</p>
                        <p>owner surname: ${surname}</p>
                        <p>owner email: ${email}</p>
                        <p>owner phone: ${phone}</p>`;
        contents.append(markup);
    })
}

function displayStreet(id) {
    const url = `http://localhost:8080/testspring_war/findStreet/${id}`;
    $.get(url, function (street) {
        const contents = $('#flatInfo');

        const name = street.name;
        const isPrestigious = street.prestigious;

        const markup = `<p>street: ${name}</p>
                        <p>is prestigious: ${isPrestigious}</p>`;
        contents.append(markup);
    })
}

function displayImage(imgId, flat) {
    const contents = $('#imgButton');
    if (imgId == 0) {
        const markup = `<input name="image" id = "image" type="file">
                        <button onclick="addImage(${flat})">add image</button>`;
        contents.append(markup);
        return;
    }

    const url = `http://localhost:8080/testspring_war/findImage/${imgId}`;
    $.get(url, function (image) {
        const markup = `${image}`;
        contents.append(markup);
    })
}
//
// function addImage(flat) {
//     console.log(flat)
//     const img = document.getElementById("image").value;
//     const url = `http://localhost:8080/testspring_war/addFlatImage`;
//     $.get(url, flat, img, function (image) {
//         console.log("sdkcnd");
//     })
// }