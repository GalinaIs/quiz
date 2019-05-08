<html>
<body>
<br />
<a href="login.do?from=quiz.do;id=${quiz.id}">LOGIN</a>
<br />
<a href="logout.do?from=quiz.do;id=${quiz.id}"$>LOGOUT</a>
<br />
Login: ${user.login}
<br />
Name: ${user.name}
<br />
<br />
<h2>Quiz #${quiz.id}</h2>
<ul>
    <li>Id: ${quiz.id}</li>
    <li>Caption: ${quiz.caption}</li>
    <li>Question: ${quiz.question}</li>
    <li>Answers: ${quiz.answers}</li>
    <li>Explanation: ${quiz.explanation}</li>
</ul>
<br />
<a href="quizAll.do">All quizzes</a>
</body>
</html>