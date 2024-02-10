    //
    // document.addEventListener("DOMContentLoaded", function () {
    //     // Ждем, пока страница полностью загрузится
    //     fetch("/apiFunc/guessStart", {
    //         method: "GET",
    //         headers: {
    //             "Content-Type": "application/json",
    //         },
    //     })
    //         .then(response => {
    //             if (!response.ok) {
    //                 throw new Error(`HTTP error! Status: ${response.status}`);
    //             }
    //             return response.json();
    //         })
    //         .then(data => {
    //             // Обработка успешного ответа от сервера
    //             console.log(data);
    //         })
    //         .catch(error => {
    //             // Обработка ошибок
    //             console.error("Error:", error);
    //         });
    // });
    //
    // document.addEventListener("DOMContentLoaded", function () {
    //     // Ждем, пока страница полностью загрузится
    //     initializePage();
    //
    //     // Функция для инициализации страницы
    //     function initializePage() {
    //         // Добавляем начальное сообщение в тело таблицы
    //         addMessageToTable("Начали... Загаданное число в промежутке от 0 до 100.");
    //
    //         // Выполняем запрос и обновляем страницу
    //         fetchData();
    //     }
    //
    //     // Функция для добавления сообщения в таблицу
    //     function addMessageToTable(message) {
    //         const resultTableBody = document.getElementById("resultTableBody");
    //         const newRow = document.createElement("tr");
    //         const newCell = document.createElement("td");
    //         newCell.textContent = message;
    //         newRow.appendChild(newCell);
    //         resultTableBody.appendChild(newRow);
    //     }
    //
    //     // Функция для выполнения запроса и обновления страницы
    //     async function fetchData() {
    //         try {
    //             const response = await fetch("/guessMessage?voice=Hello", {
    //                 method: "GET",
    //                 headers: {
    //                     "Content-Type": "application/json",
    //                 },
    //             });
    //
    //             if (!response.ok) {
    //                 throw new Error(`HTTP error! Status: ${response.status}`);
    //             }
    //
    //             const data = await response.text();
    //
    //             // Обновляем начальное сообщение на полученное от сервера
    //             updateMessageOnPage(data);
    //         } catch (error) {
    //             // Обработка ошибок
    //             console.error("Error:", error);
    //         }
    //     }
    //
    //     // Функция для обновления начального сообщения на полученное от сервера
    //     function updateMessageOnPage(message) {
    //         const resultTableBody = document.getElementById("resultTableBody");
    //
    //         // Обновляем содержимое первой строки в таблице
    //         resultTableBody.firstElementChild.firstElementChild.textContent = message;
    //     }
    // });
    //
    // function subscribe() {
    //     // Получаем введенное число
    //     var inputNumber = document.getElementById("numberInput").value;
    //
    //     console.log(inputNumber)
    //
    //     // Выполняем HTTP-запрос на ваш контроллер
    //     fetch(`apiFunc/getInt/${inputNumber}`, {
    //         method: "GET",
    //         headers: {
    //             "Content-Type": "application/json"
    //             // Дополнительные заголовки, если необходимо
    //         },
    //         // Тело запроса, если необходимо
    //     })
    //         .then(response => {
    //             if (!response.ok) {
    //                 throw new Error(`HTTP error! Status: ${response.status}`);
    //             }
    //             // Обработка успешного ответа, если необходимо
    //             console.log("Number sent successfully");
    //         })
    //         .catch(error => {
    //             // Обработка ошибок
    //             console.error("Error:", error);
    //         });
    // }
    //
    //
