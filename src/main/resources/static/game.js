function subscribe() {
    // Получение числа из поля ввода
    var number = document.getElementById('numberInput').value;

    console.log(number)

    // AJAX-запрос на ваш контроллер
    var xhr = new XMLHttpRequest();
    xhr.open('GET', `/apiGame/getNum/${Number(number)}`, true);  // Явное преобразование в число

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            // Обработка успешного ответа
            console.log('Ответ от сервера:', xhr.responseText);
        } else {
            // Обработка ошибок
            console.error('Ошибка:', xhr.statusText);
        }
    };

    xhr.onerror = function() {
        console.error('Произошла ошибка при выполнении запроса.');
    };

    xhr.send();}

const interval = 30000;

setInterval(getMessages, interval);

function getMessages() {
    fetch("/apiGame/say")
        .then(res => res.json())
        .then(messages => {
            updateMessagesContainer(messages);
        })
        .catch(error => console.error('Error fetching messages:', error));
}
function updateMessagesContainer(messages) {
    const messagesContainer = document.getElementById('messagesContainer');
    messagesContainer.innerHTML = ''; // Очистить контейнер перед обновлением

    // Проверить, является ли messages массивом и не пустым
    if (Array.isArray(messages) && messages.length > 0) {
        // Если это массив, обрабатываем каждый элемент
        messages.forEach(message => {
            const messageElement = document.createElement('div');
            messageElement.textContent = message;
            messagesContainer.appendChild(messageElement);
        });
    } else if (typeof messages === 'string' && messages.trim() !== '') {
        // Если это не массив, и строка не пуста, обрабатываем как одиночное сообщение
        const messageElement = document.createElement('div');
        messageElement.textContent = messages;
        messagesContainer.appendChild(messageElement);
    } else {
        console.warn('Пустой массив сообщений.');
    }
}
