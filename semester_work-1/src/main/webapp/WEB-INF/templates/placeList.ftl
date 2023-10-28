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
    <label for="site-search">Search the site:</label>
    <input type="search" id="site-search" name="q" />
    <button>Search</button>
    <#list places as place>
        <div class="place-card">
            <#if place??>
                <a class="place-name" href="detail?placeId=${place.placeId}">${place.title}</a>
            </#if>
        </div>
    </#list>
</body>
</html>
