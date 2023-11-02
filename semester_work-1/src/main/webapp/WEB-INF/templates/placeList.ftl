<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>куда пойти</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<#include "nav.ftl"/>
<h1>Кая барырга?</h1>
<label for="site-search">Поиск</label>
<p><input id="query" oninput="f()"/></p>
<button>Найти</button>


<div id="res"></div>

<script type="application/javascript">
    function f() {
        if ($("#query").val().length >= 1) {
            $.ajax({
                url: "/dosearch",
                data: {"query": $("#query").val()},
                dataType: "json",
                success: function (msg) {
                    if (msg.objects.length > 0) {
                        $("#res").html("");
                        for (var i = 0; i < msg.objects.length; i++) {
                            $("#res").append("<li>" + msg.objects[i].name + "</li>");
                        }
                    } else {
                        $("#res").html("No results..");
                    }
                }
            })
        } else {
            $("#res").html("");
        }
    }
</script>

<#list places as place>
    <div class="place-card">
        <#if place??>
            <a class="place-name" href="detail?placeId=${place.placeId}">${place.title}</a>
        </#if>
    </div>
</#list>
</body>
</html>
