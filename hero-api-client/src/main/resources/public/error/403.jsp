<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title th:text="${title}"></title>
    <meta charset="UTF-8">
</head>
<body class="layout">
<div class="wrap">
    <!-- S top -->
    <div th:include="/header/module-header::module-header"></div>
    <!-- S 内容 -->
    <div class="panel-l container clearfix">
        <div class="error">
            <p class="title"><span class="code" th:text="${status}"></span>非常抱歉，资源不可用</p>
          <!--  <a href="/" class="btn-back common-button">返回首页
                <img class="logo-back" src="/img/back.png">
            </a>-->
            <div class="common-hint-word">
                <div th:text="${#dates.format(timestamp,'yyyy-MM-dd HH:mm:ss')}"></div>
                <div th:text="${messages}"></div>
                <div th:text="${error}"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>