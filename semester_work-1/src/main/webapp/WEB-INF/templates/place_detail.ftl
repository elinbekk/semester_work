<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${place.getTitle()}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script type="application/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link href="styles/place_detail.css" rel="stylesheet" type="text/css">
</head>
<body>
<#include "nav.ftl"/>
<div class="content">
    <h1 class="header">${place.getTitle()}</h1>
    <div class="place-page">
        <div class="info-block">
            <p class="place-description">${place.getDescription()}</p>
            <p class="place-address">Адрес: ${place.getAddress()}</p>
            <p class="place-category">Категория: ${place.getCategory()}</p>
            <p class="place-price">Цена: ${place.getPrice()}</p>
            <div class="place-rating">
                <span class="rating-value">Рейтинг: ${place.getRating()}</span>
                <button class="button button-stars" value="${place.getRating()}" disabled></button>
                <i class="star"></i>
            </div>
        </div>
        <div class="image-block">
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
                    <#else><span>Фотографий пока нет((</span>
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
                <button class="button button-like" value="${place.placeId}">
                    <i class="heart"></i>
                </button>
        </div>
    </div>
    <div class="place-reviews-block">
        <#list reviews as review>
            <#if review??>
                <div class="review-card">
                    <div class="review">
                        <p class="review-author">${review.authorFullName} </p>
                        <p class="review-text">${review.text}</p>
                        <p class="review-assessment">Оценка: ${review.assessment}</p>
                        <p class="review-date">${review.date}</p>
                    </div>
                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal"
                            data-bs-target="#commentModal-${review.reviewId}">комментарии
                    </button>
                    <div class="modal fade" id="commentModal-${review.reviewId}" tabindex="-1"
                         aria-labelledby="commentModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="commentModalLabel}">
                                        Комментарии</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Закрыть"></button>
                                </div>
                                <div id="modal-body-${review.reviewId}" class="modal-body">
                                    <#if review.commentsList??>
                                        <#list review.commentsList as comment>
                                            <div class="comment-card" id="comment-card">
                                                <p class="comment-text">${comment.getText()}</p>
                                                <span class="comment-author">${comment.getAuthorFullname()}, </span>
                                                <span class="comment-date">${comment.getDate()}</span>
                                            </div>
                                        </#list>
                                    <#else>
                                        <span>Пока комментариев нет(</span>
                                    </#if>
                                </div>
                                <div class="modal-footer">
                                    <div class="add-comment-block">
                                        <form class="comment-form" method="post">
                                            <label for="comment-text"></label>
                                            <textarea id="comment-text-text-${review.reviewId}" name="comment-text"
                                                      required></textarea>
                                        </form>
                                    </div>
                                    <button type="button" class="btn btn-primary" value="${review.reviewId}">Добавить
                                        комментарий
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#if>
        </#list>
        <form action="places-add-review">
            <input type="hidden" name="placeId" value="${place.getPlaceId()}">
            <input class="review-button" type="submit" value="Оставить отзыв">
        </form>
    </div>
    <form action="places-list">
        <input class="back-button" type="submit" value="Назад">
    </form>

</div>

<script>
    $(".button-like").on('click', function () {
        let placeId = $(this).val();
        console.log(placeId)
        $.ajax({
            type: "POST",
            url: "places-list",
            data: {"placeId": placeId},
            success: function (result) {
                console.log(result);
            },
            dataType: "text/plain"
        });
    });

    $(".btn-primary").on('click', function () {
        let reviewId = $(this).val();
        let text = $("#comment-text-text" + "-" + reviewId).val();
        console.log("text: ", text);
        console.log("reviewId: ", reviewId);
        $.ajax({
            type: "POST",
            url: "places-add-comment",
            data: {"comment-text": text, "reviewId": reviewId},
            success: function () {
                console.log("success")
                $('#comment-text-text').val('');
            },
            error: function () {
            }

        })
        $.ajax({
            url: "places-add-comment",
            dataType: "json",
            data: {"comment-text": text, "reviewId": reviewId},
            success: function (response) {
                let newComment = '<div class="comment-card" id="comment-card">' +
                    '<p class="comment-text">' + text + '</p>' +
                    '<span class="comment-author">' + response.authorFullname + ', </span>' +
                    '<span class="comment-date">' + response.date + '</span>' +
                    '</div>';
                $("#modal-body" + "-" + reviewId).append(newComment);
                $('#comment-text-text').val('');
            },
            error: function () {

            }
        })
    })
</script>
</body>
</html>