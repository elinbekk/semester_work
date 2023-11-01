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
    <p class="place-description">${place.getDescription()}</p>
    <p class="place-address">Адрес: ${place.getAddress()}</p>
    <p class="place-category">Категория: ${place.getCategory()}</p>
    <p class="place-price">${place.getPrice()}</p>
    <div class="place-rating">
        <span class="rating-stars"></span>
        <span class="rating-value">Рейтинг: ${place.getRating()}</span>
    </div>
</div>
<form action="list">
    <input class="back-button" type="submit" value="Назад">
</form>
<form action="add-review">
    <input type="hidden" name="placeId" value="${place.getPlaceId()}">
    <input class="review-button" type="submit" value="Оставить отзыв">
</form>
<div class="image-block">
    <h3>Фотографии</h3>
    <#if images??>
        <#list images as image>
            <img src="${image.src}" alt="${image.description}" width="200">
        </#list>
    <#else>
        <p class="aa">фотографий пока нет(</p>
    </#if>
</div>
<div class="place-reviews-block">
    <h3>Отзывы</h3>
    <#list reviews as review>
        <#if review??>
            <a href="/detail/rc">
                <div class="review-card">
                    <p class="review-text">${review.text}</p>
                    <p class="review-author">${review.authorId}</p>
                    <p class="review-assessment">${review.assessment}</p>
                    <p class="review-date">${review.date}</p>
                    <p class="re"></p>
                </div>
            </a>
        </#if>
    </#list>
</div>
</body>
</html>