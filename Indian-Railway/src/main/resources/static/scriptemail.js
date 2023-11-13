document.querySelector("#form1").addEventListener("submit", function (e) {
  e.preventDefault();

  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;

  let reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
  let passw = /^(?=.*\d)(?=.*[a-z])(?=.*[^a-zA-Z0-9])(?!.*\s).{7,15}$/;
  // console.log(reg)

  if (email.match(reg)) {
    console.log("email equal");
    document.getElementById("errorText").innerHTML = "";
  } else {
    console.log("email Not equal");
    document.getElementById("errorText").innerHTML = "Enter the valid email";
    document.getElementById("errorText").style.color = "red";
  }

  if (password.match(passw)) {
    console.log("password equal");
    document.getElementById("pass").innerHTML = "";
  } else {
    console.log("password Not equal");
    document.getElementById("pass").innerHTML =
      "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters";
    document.getElementById("pass").style.color = "red";
  }

  if (email.match(reg) && password.match(passw)) {
    location.href = "/SaveReg";
  }

  localStorage.setItem("email", email);
  localStorage.setItem("password", password);
});

let reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
