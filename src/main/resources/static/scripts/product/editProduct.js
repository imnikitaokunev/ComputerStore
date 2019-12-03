var oldName;
var oldCost;

function onLoad() {
    oldName = document.getElementById("name").value;
    oldCost = document.getElementById("cost").value;
}

function showMessage() {
    var newName = document.getElementById("name").value;
    var newCost = document.getElementById("cost").value;
    alert('Продукт изменен\n' +
        'Название: ' + oldName + ' -> ' + newName + '\n' +
    'Стоимость: ' + oldCost + ' -> ' + newCost);
}