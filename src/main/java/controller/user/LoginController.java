/*
package controller.user;

import DAO.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Users;
import utils.GoogleAuth;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * LoginController handles the login functionality for users.
 * It processes login requests, verifies user credentials, and handles
 * Google OAuth login redirection.
 * 
 * @author a
 */
//@WebServlet("/LoginController")
//public class LoginController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private LoginDAO loginDAO;  // DAO to interact with database for login verification
    
//    // Google OAuth credentials, should ideally be stored in environment variables for security
//    private static final String GOOGLE_CLIENT_ID = System.getenv("GOOGLE_CLIENT_ID");
//    private static final String GOOGLE_CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");
//    private static final String GOOGLE_REDIRECT_URI = System.getenv("GOOGLE_REDIRECT_URI");

//    // Logger to log errors and important information
//    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

//    /**
//     * Initializes the LoginDAO which is used for verifying user credentials.
//     */
//    public void init() {
//        loginDAO = new LoginDAO();  // Initialize the LoginDAO
//    }

//    /**
//     * Handles POST requests for logging in the user.
//     * Verifies the credentials (username and password) against the database.
//     * On success, redirects to the home page or the page the user attempted to access before login.
//     * On failure, redirects to the login page with an error message.
//     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String username = request.getParameter("username");  // Get username from request
//        String password = request.getParameter("password");  // Get password from request

//        try {
//            // Verify the credentials in the database (password should ideally be hashed)
//            Users account = loginDAO.verifyCredentials(username, password);
//            if (account != null) {
//                // If credentials are correct, create a session and store the user account
//                HttpSession session = request.getSession();
//                session.setAttribute("account", account);
                
//                // Check if there's a previous page the user was trying to access
//                String redirectPage = (String) session.getAttribute("redirectPage");
//                if (redirectPage == null) {
//                    redirectPage = "index.jsp";  // Default redirect page if no previous page
//                }
//                response.sendRedirect(redirectPage);  // Redirect to the page
//            } else {
//                // If credentials are incorrect, set an error attribute and forward to login page
//                request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
//                request.getRequestDispatcher("LoginView.jsp").forward(request, response);
//            }
//        } catch (Exception e) {
//            // Log error and redirect to error page
//            logger.log(Level.SEVERE, "Login error", e);  // Log the exception
//            response.sendRedirect("error.jsp");  // Redirect to error page
//        }
//    }

//    /**
//     * Handles GET requests for Google login redirection.
//     * When the user clicks the Google login button, this method constructs the
//     * Google OAuth authorization URL and redirects the user to Google's authentication page.
//     */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");  // Get the action parameter

//        if ("google".equals(action)) {
//            // If the action is 'google', initiate the Google login process
//            GoogleAuth authHelper = new GoogleAuth(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET, GOOGLE_REDIRECT_URI);
//            String authUrl = authHelper.buildAuthorizationUrl();  // Build the authorization URL
//            response.sendRedirect(authUrl);  // Redirect the user to Google login
//        }
//    }
//}
