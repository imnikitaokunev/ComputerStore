function submitRegister(){
    const firstName = document.getElementById("firstName");
    const lastName = document.getElementById("lastName");
    const patronymicName = document.getElementById("patronymicName");
    const userName = document.getElementById("username");
    const userPassword = document.getElementById("password");
    const phone = document.getElementById("phone");
    const email = document.getElementById("email");

    let xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/registration";
    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let json = JSON.parse(xhr.responseText);
        }
    };

    const data = JSON.stringify({
        "firstName": firstName.value,
        "lastName": lastName.value,
        "patronymicName": patronymicName.value,
        "userName": userName.value,
        "userPassword": userPassword.value,
        "roleName": "ROLE_BUYER",
        "phone": phone.value,
        "email": email.value
    });
    xhr.send(data);
    alert("Регистрация прошла успешно")
    relocateToMainPage();
}

function relocateToMainPage() {
    location.href = "http://localhost:8080/login";
}