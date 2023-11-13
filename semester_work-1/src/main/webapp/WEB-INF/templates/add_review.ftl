<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${place.getTitle()}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/place_detail.css">
</head>
<body>
<#include "nav.ftl"/>
<div class="place-page">
    <h2 class="place-name">${place.getTitle()}</h2>
    <p class="place-address">${place.getAddress()}</p>
    <p class="place-category">${place.getCategory()}</p>
    <p class="place-description">${place.getDescription()}</p>
    <p class="place-price">${place.getPrice()}</p>
</div>
<form action="places-list">
    <input class="back-button" type="submit" value="Назад">
</form>
<div class="places-add-review">
    <form class="review-form" method="post">
        <label for="review-text">Отзыв:</label>
        <textarea id="review-text" required name="review-text"></textarea>
        <span class="message">Максимальное количество символов - 200. Сейчас введено: </span>
        <span id="counter">0</span>
        <div>
            <input type="radio" id="choice1" name="assessment" value="1"/>
            <label for="choice1">1</label>
            <input type="radio" id="choice2" name="assessment" value="2"/>
            <label for="choice2">2</label>
            <input type="radio" id="choice3" name="assessment" value="3"/>
            <label for="choice3">3</label>
            <input type="radio" id="choice4" name="assessment" value="4"/>
            <label for="choice4">4</label>
            <input type="radio" id="choice5" name="assessment" value="5"/>
            <label for="choice5">5</label>
        </div>
        <script>
            const obj = document.getElementById('review-text');
            obj.oninput = function() {
                let textArea = document.getElementById("review-text");
                document.getElementById('counter').innerText = textArea.value.length;
                if(textArea.value.length === 20){
                    alert('Введено максимальное количество символов😊');
                }
            };
        </script>
            <input class="review-button" type="submit" value="Оставить отзыв">
    </form>
</div>
</body>
</html>