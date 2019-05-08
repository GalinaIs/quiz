package com.sbt.controller;


import com.sbt.dao.QuizDao;
import com.sbt.entity.Quiz;
import com.sbt.injection.Inject;
import com.sbt.injection.servlet.DependencyInjectionServlet;
import com.sbt.transactionManager.TransactionManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sbt.utils.ClassNameUtil.getCurrentClassName;

public class QuizController extends DependencyInjectionServlet {
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_QUIZ = "quiz";
    private static final String PAGE_OK = "quiz.jsp";
    private static final String PAGE_ERROR = "quizAll.do";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("quizDao")
    private QuizDao quizDao;
    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null && !idStr.equals("")) {
            try {
                final Integer id = Integer.valueOf(idStr);
                Quiz quiz = selectById(id);
                if (quiz != null) {
                    request.setAttribute(ATTRIBUTE_QUIZ, quiz);
                    logger.debug("set attribute '" + ATTRIBUTE_QUIZ + "' to " + quiz);
                    request.getRequestDispatcher(PAGE_OK).forward(request, response);
                    logger.debug("PAGE_OK: RequestDispatcher.forward(...) to '" + PAGE_OK + "'");
                    return;
                }
            } catch (Exception e) {
                logger.warn("Some exception", e);
            }
        }
        response.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: Redirect to " + PAGE_ERROR);
    }

    private Quiz selectById(Integer id) throws Exception {
        return txManager.doInTransaction(() -> quizDao.selectById(id));
    }
}