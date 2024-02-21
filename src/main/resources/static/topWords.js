function subscribe() {
    var numberValue = document.getElementById("numberInput").value;
    var textValue = document.getElementById("textInput").value;
    var data = {
        word: textValue,
        limit: parseInt(numberValue)
    };

    fetch('/apiFunc/processWords', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(result => {
            updateTable(result);
        })
        .catch(error => console.error('Error:', error));
}

function updateTable(result) {
    var tableBody = document.getElementById("resultTableBody");
    tableBody.innerHTML = "";

    for (var key in result) {
        if (result.hasOwnProperty(key)) {
            var row = tableBody.insertRow();
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            cell1.innerHTML = key;
            cell2.innerHTML = result[key];
        }
    }
}
