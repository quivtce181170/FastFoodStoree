<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="model.TaiKhoan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.TaiKhoanDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chill Ik Coffee</title>
        <style>
            html, body {
                margin: 0;
                padding: 0;
                height: 100%; /* Ensure full-page height */
                overflow-x: hidden; /* Hide horizontal scrollbar */
            }

            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-image: url('https://img4.thuthuatphanmem.vn/uploads/2020/12/25/background-ca-phe-chuyen-nghiep-nhat_034336848.jpg');
                background-size: cover;
                background-position: center;
                background-attachment: fixed; /* Keep background fixed while scrolling */
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px;
                height: 130px;
                background-color: cornsilk;
                max-width: 100%;
                box-sizing: border-box;
            }

            .header img {
                height: 10vh;
                margin-left: 20px;
            }
            h1 {
                margin-top: 20px;
                height: 25vh;
                display: flex;
                justify-content: flex-start;
                align-items: center;
                font-size: 2.5em;
                font-style: oblique;
                font-family: cursive;
                color: #a67c52;
            }

            .navbar {
                position: relative;
                display: inline-block;
                margin-right: 30px;
            }



            .navbar a {
                text-decoration: none;
                font-weight: bold;
                color: #333;
                padding: 10px 20px;
                display: inline-block;
                text-align: center;
            }

            .navbar .dropdown {
                display: none;
                position: absolute;
                background-color: cornsilk;
                min-width: 120px;
                box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
                z-index: 1;
                max-width: 100%;
                overflow: hidden;
                box-sizing: border-box;
            }

            .navbar .dropdown a {
                display: block;
                padding: 8px 10px;
                text-decoration: none;
                color: #333;
            }

            .navbar:hover .dropdown {
                display: block;
            }

            .dropdown a:hover {
                background-color: #f1f1f1;
            }

            .slideshow-container {
                position: relative;
                max-width: 90%;
                margin: auto;
                margin-top: 20px;
                box-sizing: border-box;
            }

            .mySlides img {
                width: 100%;
                height: auto;
                max-height: 400px;
                object-fit: cover;
            }

            .main-content {
                text-align: center;
                margin: 20px;
                background-color: rgba(255, 255, 255, 0.9);
                padding: 20px;
            }

            .dot-container {
                text-align: center;
                padding: 20px;
            }

            .dot {
                height: 15px;
                width: 15px;
                margin: 0 5px;
                background-color: #bbb;
                border-radius: 50%;
                display: inline-block;
                transition: background-color 0.6s ease;
            }

            .active {
                background-color: #717171;
            }

            @media screen and (max-width: 768px) {
                .header img {
                    height: 8vh;
                }

                .nav a {
                    font-size: 14px;
                }
            }

            @media screen and (max-width: 480px) {
                .header {
                    flex-direction: column;
                    align-items: center;
                }

                .nav {
                    flex-direction: column;
                    align-items: center;
                }

                .nav a {
                    margin: 5px 0;
                }
            }

            footer {
                background-color: cornsilk;
                text-align: center;
                padding: 10px 0;
                position: relative;
                bottom: 0;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <div class="header">

            <img src="https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/465719813_889632846567474_4385764391692164502_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=127cfc&_nc_ohc=XOQsL-VMi7MQ7kNvgF-adpv&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=AIo5UIk4vnlXyBRXjajW0OC&oh=00_AYCfrYsafDlZosl-EHr2syoIjkH_9jwOOpi_QrvM9BqKsA&oe=6730CE8A"alt="Chill Ik Coffee Logo" width="80px" height="80px">
            <h1>Chill Ik Coffee</h1>

            <c:if test="${not empty sessionScope.user}">
                <c:if test="${sessionScope.user.isAdmin}">

                    <div class="navbar">
                        <a href="#">Manager</a>
                        <div class="dropdown">

                            <c:if test="${not empty sessionScope.user}">
                                <a href="<c:url value='/QuanliTaiKhoan' />">Account Management</a>
                                <a href="<c:url value='/Quanly/voucher/ListVoucher' />">Voucher Management</a>
                                <a href="<c:url value='/Quanly/DoanhThu' />">Revenue Management</a>
                                <a href="<c:url value='/paymentList' />">Orders</a>

                            </c:if>
                        </div>
                    </div>
                </c:if>
                <span>
                    <img src="https://png.pngtree.com/png-vector/20220225/ourmid/pngtree-black-coffee-png-image_4463597.png" alt="User Icon" style="width:16px; height:16px; margin-right:5px; vertical-align:middle;">
                    Hello, ${sessionScope.user.username}
                </span>
            </c:if>



            <div class="navbar">
                <a href="#">Home</a>
                <div class="dropdown">
                    <a href="<c:url value="/Menu"/>">Menu</a>
                    <c:if test="${empty sessionScope.user}">
                        <a href="<c:url value="/login"/>">Login</a>
                        <a href="<c:url value="/register"/>">Register</a>
                        <a href="<c:url value="/DonDK"/>">Apply for a job</a>

                    </c:if>
                    <c:if test="${not empty sessionScope.user}">
                        <a href="<c:url value="/Logout" />">Logout</a>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="slideshow-container">
            <div class="mySlides">
                <img src="https://thuthuatnhanh.com/wp-content/uploads/2022/05/hinh-cafe-kem-banh-quy.jpg" alt="Drink 1">
            </div>
            <div class="mySlides">
                <img src="https://cdn.tgdd.vn/2021/11/CookDish/nuoc-ep-trai-cay-de-duoc-bao-lau-cach-bao-quan-nuoc-ep-trai-avt-1200x676-2.jpg" alt="Drink 2">
            </div>
            <div class="mySlides">
                <img src="https://img5.thuthuatphanmem.vn/uploads/2021/09/22/background-cac-loai-tra-sua_095413594.jpg" alt="Drink 3">
            </div>
            <div class="mySlides">
                <img src="https://img4.thuthuatphanmem.vn/uploads/2020/12/25/quan-cafe-mau-sac-tieu-chuan-mau-muc_061437880.jpg" alt="Drink 4">            </div><!-- comment -->
            <div class="mySlides">
                <img src="https://b.zmtcdn.com/data/reviews_photos/e1c/38c98e96813be3f11e4832b12f6ebe1c_1670912281.jpg" alt="Drink 5">
            </div>
        </div>

        <div class="dot-container">
            <span class="dot" onclick="currentSlide(1)"></span>
            <span class="dot" onclick="currentSlide(2)"></span>
            <span class="dot" onclick="currentSlide(3)"></span>
            <span class="dot" onclick="currentSlide(4)"></span>
            <span class="dot" onclick="currentSlide(5)"></span>
        </div>

        <div class="main-content">
            <div class="voucher">
                <h2>Special Vouchers</h2>
                <img src="https://th.bing.com/th/id/R.be8918ad0cf341bd9047f13eb332f7ef?rik=GdkI%2fxj5I6DfYA&pid=ImgRaw&r=0" alt="Voucher" width="300px" height="210">
                <img src="https://tamanhduong.vn/public/uploads/0d150d3243b9d50a9969c65770355c26/images/thiet-ke-voucher-2.jpg" alt="Voucher" width="300px" height="210">
                <img src="https://inkythuatso.com/uploads/thumbnails/800/2021/12/mau-voucher-dep-vector-inkythuatso-30-14-32-54.jpg" alt="Voucher" width="300px" height="210">
            </div>
        </div>

        <footer>
            <p>Address: FPT University Can Tho | Phone: 0584428016</p>
        </footer>

        <script>
            let slideIndex = 0;
            showSlides();

            function showSlides() {
                let i;
                let slides = document.getElementsByClassName("mySlides");
                let dots = document.getElementsByClassName("dot");
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                slideIndex++;
                if (slideIndex > slides.length) {
                    slideIndex = 1;
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active", "");
                }
                slides[slideIndex - 1].style.display = "block";
                dots[slideIndex - 1].className += " active";
                setTimeout(showSlides, 3000); // Change image every 3 seconds
            }

            function currentSlide(n) {
                let i;
                let slides = document.getElementsByClassName("mySlides");
                let dots = document.getElementsByClassName("dot");
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active", "");
                }
                slides[n - 1].style.display = "block";
                dots[n - 1].className += " active";
            }
        </script>
    </body>
</html>
