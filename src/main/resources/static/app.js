document.addEventListener("DOMContentLoaded", () => {

    // REGISTER
    const registerForm = document.getElementById("registerForm");

    if (registerForm) {
        registerForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const username = document.getElementById("regUsername").value;
            const password = document.getElementById("regPassword").value;

            const response = await fetch("/api/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ username, password })
            });

            const result = await response.text();
            document.getElementById("msg").innerText = result;
        });
    }

    // LOGIN
    const loginForm = document.getElementById("loginForm");

    if (loginForm) {
        loginForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const username = document.getElementById("loginUsername").value;
            const password = document.getElementById("loginPassword").value;

            const response = await fetch("/api/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ username, password })
            });

          const result = await response.text();

          if (result === "User not exist" || result === "Invalid password") {
              document.getElementById("msg").innerText = result;
              return;
          }

          // Only store if real token
          localStorage.setItem("jwt", result);
          window.location.href = "dashboard.html";


        });
    }

});
