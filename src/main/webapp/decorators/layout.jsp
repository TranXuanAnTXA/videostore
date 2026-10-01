<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
    body {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
</style>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/></title>
    <sitemesh:write property="head"/>
</head>

<!-- Bổ sung Flexbox và chiều cao 100vh vào body -->
<body style="margin: 0; font-family: Arial; background: #f5f5f5; display: flex; flex-direction: column; min-height: 100vh;">

    <!-- Header -->
    <jsp:include page="/decorators/header.jsp"/>

    <!-- Bổ sung flex: 1 để thẻ div này phình to ra, đẩy Footer xuống đáy -->
    <div style="padding: 20px; flex: 1;">
        <sitemesh:write property="body"/>
    </div>

    <!-- Footer -->
    <jsp:include page="/decorators/footer.jsp"/>

</body>
</html>