<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title>Đăng nhập</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Login</title>
        <!-- Add the necessary Bootstrap CSS CDN link here -->
        
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    </head>
    <body>
        <h2>Đăng nhập</h2>
        <% if (request.getAttribute("errorMessage") != null) {%>
        <p style="color:red;"><%= request.getAttribute("errorMessage")%></p>
        <% }%>
        <form action="login" method="post">
            <div data-mdb-input-init class="form-outline form-white mb-4">
                <label class="form-label" for="typeEmailX">*Email</label>
                <input name="email" type="email" id="typeEmailX" class="form-control form-control-lg" required/>
            </div>

            <div data-mdb-input-init class="form-outline form-white mb-4">
                <label class="form-label" for="typePasswordX">*Mật Khẩu</label>
                <input name="password" type="password" id="typePasswordX" class="form-control form-control-lg" required/>
            </div>
            <p class="small mb-5 pb-lg-2"><a class="custom-link" href="Capcha">Quên mật khẩu?</a>
                &nbsp;&nbsp;&nbsp;&nbsp;<a class="custom-link" href="register">Tạo tài khoản</a></p>

            <button data-mdb-button-init data-mdb-ripple-init class="btn custom-btn btn-lg px-5" type="submit">Đăng nhập</button>
        </form>
        <a href="google-login">Đăng nhập bằng Google</a>
    </body>
</html>
