<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${place.getTitle()}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
<div class="image-block">
    <h3>Фотографии</h3>
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${place.getImage()}" class="d-block w-100" alt="main_img" width="200">
            </div>
            <#if images??>
                <#list images as image>
                    <div class="carousel-item">
                        <img src="${image.src}" class="d-block w-100" alt="${image.description}">
                    </div>
                </#list>
            </#if>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<form action="list">
    <input class="back-button" type="submit" value="Назад">
</form>
<form action="add-review">
    <input type="hidden" name="placeId" value="${place.getPlaceId()}">
    <input class="review-button" type="submit" value="Оставить отзыв">
</form>
</body>
</html>