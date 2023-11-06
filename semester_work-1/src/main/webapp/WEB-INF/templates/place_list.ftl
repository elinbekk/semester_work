<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>куда пойти</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script type="application/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<#include "nav.ftl"/>
<h1>Кая барырга?</h1>
<#if places??>
    <#list places as place>
        <div class="place-card">
            <a class="place-name" href="detail?placeId=${place.placeId}">${place.title}</a>
            <button class="button-like" value="${place.placeId}">
                <i class="heart"></i>
                <span>Like</span>
            </button>
        </div>
    </#list>
</#if>
<script>
    $(".button-like").on('click', function (event) {
        let placeId = $(this).val();
        console.log(placeId)
        $(".button-like").toggleClass("liked");
        setTimeout(() => {
            $(event.target).removeClass('liked')
        }, 1000)

        $.ajax({
            type: "POST",
            url: "list",
            data: {"placeId": placeId},
            success: function (result) {
                console.log(result);
            },
            dataType: "text/plain"
        });
    });
</script>
</body>
</html>
