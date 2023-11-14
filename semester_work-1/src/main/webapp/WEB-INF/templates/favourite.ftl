<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script type="application/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link rel="stylesheet" href="styles/favourite.css">
</head>
<body>
<#include "nav.ftl"/>
<div class="favourite-list">
    <#if places??>
        <#list places as place>
            <div class="place-card" id="${place.placeId}">
                <a class="place-name" href="places-detail?placeId=${place.placeId}">${place.title}
                    <img src="${place.image}" alt="${place.title}_photo"></a>
                <button class="button-delete" value="${place.placeId}">
                    <i class="trash"></i>
                </button>
            </div>
        </#list>
    <#else>
        <p class="text">Тут пока ничего нет(</p>
    </#if>
</div>
<form action="places-list">
    <input class="back-button" type="submit" value="Назад к просмотру мест">
</form>
<script>
    $(".button-delete").on('click', function () {
        let placeId = $(this).val().toString();
        $.ajax({
            type: "POST",
            url: "favourite",
            data: {"placeId": placeId},
            success: function (result) {
                $('#'+placeId).hide(300);
                console.log(result);
            }
        });
    });
</script>
</body>
</html>