<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.TaiKhoan" %>
<%@ page import="DAO.TaiKhoanDAO" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Account</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                padding-top: 70px;
                background-color: #f3ece7;
                color: #5c4033;
            }

            h1 {
                padding-top: 20px;
                margin-left: 18px;
                text-align: left;
                color: #8b5a2b;
            }

            .ma p {
                margin-left: 18px;
                color: #5c4033;
            }

            /* Back Button Styling */
            #back {
                background-color: #8b5a2b;
                color: white;
                padding: 8px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
                font-weight: bold;
            }

            #back:hover {
                background-color: #704214;
            }

            /* Delete Button Styling */
            #submit {
                background-color: #b22222;
                color: white;
                padding: 8px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: bold;
            }

            #submit:hover {
                background-color: #8b0000;
            }

            /* Container for the confirmation text */
            .m_title {
                font-weight: bold;
                color: #8b5a2b;
                margin-bottom: 20px;
            }

            .m_id {
                font-weight: bold;
                color: #704214;
            }

            /* Button container */
            .nut {
                display: flex;
                margin-left: 18px;
                gap: 10px;
                align-items: center;
            }

            /* Error Message Styling */
            p.error-message {
                color: #b22222;
                font-weight: bold;
                margin-left: 18px;
            }
        </style>
    </head>
    <body>

        <h1>Delete Account</h1>

        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <c:choose>
            <c:when test="${not empty taiKhoan}">
                <form action="<c:url value='/QuanliTaiKhoan'/>" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${taiKhoan.id}">

                    <div class="ma">
                        <p>Are you sure you want to delete 
                            <b class="m_title">${taiKhoan.username}</b> 
                            with ID 
                            <b class="m_id">${taiKhoan.id}</b>?
                        </p>
                    </div>
                    <div class="nut">
                        <a href="<c:url value='/QuanliTaiKhoan' />" id="back">Back</a>
                        <button type="submit" id="submit">Delete</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <p>No account selected for deletion.</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
