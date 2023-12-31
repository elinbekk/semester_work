<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script type="application/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link rel="stylesheet" href="styles/place_search.css">
</head>
<body>
<#include "nav.ftl"/>
<h1 class="header">Кая барырга?</h1>
<div class="content">
    <div class="search-block">
        <label for="site-search">Поиск</label>
        <input class="query" id="query" oninput="search()"/>
        <select name="category" id="category-select" onchange="search()">
            <option value="">Категория</option>
            <option value="Еда">Еда</option>
            <option value="Культура">Культура</option>
            <option value="Природные объекты">Природные объекты</option>
            <option value="Архитектура">Архитектура</option>
            <option value="Развлечения">Развлечения</option>
            <option value="Детям">Детям</option>
        </select>
    </div>
    <div class="result-block">
    </div>
</div>

<#--TODO: вынести в js-файл скрипт-->
<script type="application/javascript">
    function search() {
        let query = $("#query").val();
        let category = $("#category-select").val();
        $.ajax({
            url: "dosearch",
            data: {
                "query": query,
                "category": category
            },
            dataType: "json",
            success: function (response) {
                if (response.objects.length > 0) {
                    $(".result-block").html("");
                    for (let i = 0; i < response.objects.length; i++) {
                        $(".result-block").append('<div class="place__card"><a class="place-name" href="places-detail?placeId=' + response.objects[i].placeId + '">' + response.objects[i].title +
                            '<img src="'+ response.objects[i].image + '" alt="'+ response.objects[i].image+ '_photo">'+ '</a></div>');
                    }
                } else {
                    $(".result-block").html("Не найдено");
                }
            }
        })
    }
</script>
</body>
</html>
