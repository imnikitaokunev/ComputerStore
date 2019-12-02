function tableCreate() {
    var url = "http://localhost:8080/all/allProducts/";
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, false);
    xhr.send();

    if (xhr.status === 200) {
       var myList = JSON.parse(xhr.responseText);
    }

    for (var i = 0; i < myList.length; i++) {
        var obj = myList[i];
        $(table)
            .find("tbody")
            .append(
                `<tr><td>${obj.id}</td><td>${obj.productName}</td><td>${obj.productCost}</td></tr>`
            );
    }
}
