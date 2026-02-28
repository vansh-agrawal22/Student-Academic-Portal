const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", async (e) => {

    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const msg = document.getElementById("msg");
    msg.innerText = "";

    try {

       const response = await fetch("/api/login", {
           method: "POST",
           headers: {
               "Content-Type": "application/json"
           },
           body: JSON.stringify({
               username: username,
               password: password
           })
       });

       const token = await response.text();

       if (!response.ok) {
           msg.innerText = token; // error message
           return;
       }

       // ✅ store JWT
       localStorage.setItem("jwt", token);

       // ✅ decode JWT
       const payload = JSON.parse(atob(token.split('.')[1]));

       const role = payload.role;

       // ✅ redirect based on role
       if (role === "ADMIN") {
           window.location.href = "admin-dashboard.html";
       } else {
           window.location.href = "student-dashboard.html";
       }

    } catch (error) {
        msg.innerText = "Server error. Please try again.";
        console.error(error);
    }

});