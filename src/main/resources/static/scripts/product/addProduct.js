function submitAdd(){
    const productName = document.getElementById("name");
    const productCost = document.getElementById("cost");

    let xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/admin/addProduct";
    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let json = JSON.parse(xhr.responseText);
        }
    };

    const data = JSON.stringify({
        "productName": productName.value,
        "productCost": productCost.value
    });
    xhr.send(data);
    alert("Продукт добавлен.")
    relocateToProductsPage();
}

function relocateToProductsPage() {
    location.href = "http://localhost:8080/products";
}