document.addEventListener("DOMContentLoaded", () => {

    const token = localStorage.getItem("jwt");

    if (!token) {
        window.location.href = "login.html";
        return;
    }

    const payload = JSON.parse(atob(token.split('.')[1]));
    const username = payload.sub;

    document.getElementById("welcomeText").innerText =
        "Welcome " + username;

    // Show first letter in profile circle
    document.getElementById("profileInitial").innerText =
        username.charAt(0).toUpperCase();
});

function logout() {
    localStorage.removeItem("jwt");
    window.location.href = "login.html";
}
