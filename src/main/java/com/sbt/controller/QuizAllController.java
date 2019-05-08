package com.sbt.controller;

import com.sbt.dao.QuizDao;
import com.sbt.entity.Quiz;
import com.sbt.injection.Inject;
import com.sbt.injection.servlet.DependencyInjectionServlet;
import com.sbt.transactionManager.TransactionManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.sbt.utils.ClassNameUtil.getCurrentClassName;

public class QuizAllController extends DependencyInjectionServlet {
    private static final String ATTRIBUTE_QUIZ = "listQuiz";
    private static final String PAGE_OK = "quizAll.jsp";
    private static final String PAGE_ERROR = "index.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("quizDao")
    private QuizDao quizDao;
    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Quiz> allQuiz = selectAll();
            request.setAttribute(ATTRIBUTE_QUIZ, allQuiz);
            logger.debug("set attribute '" + ATTRIBUTE_QUIZ + "' to " + allQuiz);
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
            logger.debug("PAGE_OK: RequestDispatcher.forward(...) to '" + PAGE_OK + "'");
            return;
        } catch (Exception e) {
            logger.warn("Some exception", e);
        }

        response.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: Redirect to " + PAGE_ERROR);
    }

    private List<Quiz> selectAll() throws Exception {
        return txManager.doInTransaction(quizDao::selectAll);
    }
}
