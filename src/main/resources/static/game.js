var isSubscribed = false;

function subscribe() {
    // Получение числа из поля ввода
    var number = document.getElementById('numberInput').value;

    // AJAX-запрос на ваш контроллер
    var xhr = new XMLHttpRequest();
    xhr.open('GET', `/apiGame/getNum/${Number(number)}`, true);

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            console.log('Ответ от сервера:', xhr.responseText);

            isSubscribed = true;
            updateMessages();
        } else {
            console.error('Ошибка:', xhr.status, xhr.statusText, xhr.responseText);
        }
    };

    xhr.onerror = function() {
        console.error('Произошла ошибка при выполнении запроса.');
    };

    xhr.send();
}
function updateMessages() {
    fetch('apiGame/say')
        .then(response => response.json())
        .then(data => {
            var messageList = document.getElementById("messageList");

            messageList.innerHTML = '';

            data.forEach(message => {
                var listItem = document.createElement("li");
                listItem.textContent = message;

                console.log(listItem);

                messageList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}
