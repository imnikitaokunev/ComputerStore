var oldName;
var oldCost;

function onLoad() {
    oldName = document.getElementById("name").value;
    oldCost = document.getElementById("salary").value;
}

function showMessage() {
    var newName = document.getElementById("name").value;
    var newCost = document.getElementById("salary").value;
    alert('Вакансия изменена\n' +
        'Название: ' + oldName + ' -> ' + newName + '\n' +
        'Зар.плата: ' + oldCost + ' -> ' + newCost);
}