package ru.otus.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.otus.dataSets.UsersDataSet;
import ru.otus.dbService.DBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author v.chibrikov
 */
public class LoginServlet extends HttpServlet {

    public static final String SAVE_USER_NAME = "save";
    public static final String LOAD_USER_NAME = "load";
    public static final String COUNT_USER = "count";
    private static final String LOGIN_VARIABLE_NAME = "user";
    private static final String COUNT_VARIABLE_NAME = "count";
    private static final String LOGIN_PAGE_TEMPLATE = "login.html";
    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    private final TemplateProcessor templateProcessor;
    private final DBService dbService;
//    String login;

    LoginServlet(TemplateProcessor templateProcessor, DBService dbService) {
        this.dbService = dbService;
        this.templateProcessor = templateProcessor;
    }

    private String getPage(String login, long count) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(LOGIN_VARIABLE_NAME, login == null ? "" : login);
        pageVariables.put(COUNT_VARIABLE_NAME, count == 0 ? "" : count);
        return templateProcessor.getPage(LOGIN_PAGE_TEMPLATE, pageVariables);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        String loadUser = request.getParameter(LOAD_USER_NAME);
        UsersDataSet user = loadUserDB(loadUser, response);
        String currentLogin = user == null ? "no request" : user.getName();

        String countUser = request.getParameter(COUNT_USER);
        long count = countAllUser(countUser, response);

        setOK(response);
        String page = getPage(currentLogin, count); //save to the page
        response.getWriter().println(page);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String saveUser = request.getParameter(SAVE_USER_NAME);
        saveUserDB(saveUser, response);

        setOK(response);
        String page = getPage(null, 0); //save to the page
        response.getWriter().println(page);

    }

    private UsersDataSet loadUserDB(String loadUser, final HttpServletResponse response) throws IOException {
        UsersDataSet user = null;
        if (loadUser != null) {
            try {
                user = dbService.load(Long.parseLong(loadUser), UsersDataSet.class);
                if (user == null) {
                    throw new SQLException();
                }
            } catch (SQLException e) {
                logger.error("DB can't load user", e);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "B can't load user"); }
        }
        return user;
    }

    private long countAllUser(String countUser, final HttpServletResponse response) throws IOException {
        long count = 0;
        if (countUser != null) {
            try {
                count = (long) dbService.countUser();
            } catch (SQLException e) {
                logger.error("DB can't count user", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "B can't count user");
            }
        }
        return count;
    }

    private void saveUserDB(String saveUser, final HttpServletResponse response) {
        if (saveUser != null) {
            try {
                dbService.save(new UsersDataSet(saveUser));
            } catch (SQLException e) {
                logger.error("DB can't save user", e);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }


    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
