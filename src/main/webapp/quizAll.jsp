<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
<br />
<a href="login.do?from=quizAll.do">LOGIN</a>
<br />
<a href="logout.do?from=quizAll.do"$>LOGOUT</a>
<br />
Login: ${user.login}
<br />
Name: ${user.name}
<br />
<br />
<h2>All quizzes</h2>
<c:forEach  items="${listQuiz}" var="quiz">
    <ul>
        <li>Id: ${quiz.id}</li>
        <li>Caption: ${quiz.caption}</li>
        <li>Question: ${quiz.question}</li>
        <li>Answers: ${quiz.answers}</li>
        <li>Explanation: ${quiz.explanation}</li>
    </ul>
</c:forEach>

<a href="quizAll.do">All quizzes</a>
</body>
</html>