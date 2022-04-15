<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Events</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="card text-center">
    <h3>${connectionMessage}</h3>
    <div>Events by today (${date}):</div>
    <div class="card-body">
        <table class="table table-striped table-hover table-bordered">
            <thead>
            <tr>
                <th>Patient</th>
                <th>Time</th>
                <th>Doctor</th>
                <th>Status</th>
                <th>Therapy</th>
                <th>Dose</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="theme">
                <tr>
                    <td>${theme.patientName}</td>
                    <td>${theme.dateTime.toLocalTime()}</td>
                    <td>${theme.doctorName}</td>
                    <td>${theme.eventStatus}</td>
                    <td>${theme.therapy}</td>
                    <td>${theme.therapyDose}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script>
    $(document).ready(function() {
        const socket = new SockJS('');
        const stompClient = Stomp.over(socket);

        stompClient.connect({ }, function() {
            stompClient.subscribe("/update", function() {
                window.location.reload();
            });
        });
    });
</script>
