<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>куда пойти</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script type="application/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link rel="stylesheet" href="styles/place_list.css">
</head>
<body>
<#include "nav.ftl"/>
<h1>Кая барырга?</h1>
<div class="place-page">
<#if places??>
    <#list places as place>
        <div class="place__card">
            <a class="place-name" href="places-detail?placeId=${place.placeId}">${place.title}
                <img src="${place.image}" alt="${place.title}_photo">
            </a>
            <button class="button-like" value="${place.placeId}">
                <i class="heart"></i>
            </button>
        </div>
    </#list>
</#if>
</div>
<script>
    $(".button-like").on('click', function (event) {
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
        $(this).style.backgroundColor = '#ff0000';
    });
</script>
</body>
</html>
