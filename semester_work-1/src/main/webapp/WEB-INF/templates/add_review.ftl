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
    <div class="place-rating">
        <span class="rating-stars"></span>
        <span class="rating-value">${place.getRating()}</span>
    </div>
</div>
<form action="list">
    <input class="back-button" type="submit" value="Назад">
</form>
<div class="add-review">
    <form class="review-form">
        <label for="review-text">Отзыв:</label>
        <textarea id="review-text" required></textarea>
        <input class="review-button" type="submit" value="Оставить отзыв">
    </form>
</div>
</body>
</html>