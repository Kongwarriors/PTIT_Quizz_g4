/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const errorMessage = document.getElementById('error-message');
function registerUser(){
    //Lay du lieu tu cac element
    var data = {
        email : document.getElementById("email").value,
        username : document.getElementById("username").value,
        password : document.getElementById("password").value,
        role : "user"
    };
    var re_password = document.getElementById("re-password").value;
    var password = document.getElementById("password").value;
    if(re_password === password){
        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open("POST", "../../registerservlet", true);
        xmlHttpRequest.setRequestHeader("Content-Type", "application/json");

        xmlHttpRequest.onload = function () {
            if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
                var jsonResponse = JSON.parse(xmlHttpRequest.responseText);
                console.log(jsonResponse);
                // Xử lý phản hồi JSON từ Servlet tại đây
                handleRegisterResponse(jsonResponse);
            }
        };
        xmlHttpRequest.send(JSON.stringify(data));
    }
    else{
        errorMessage.textContent = "Không trùng password";
    }
    
}

function handleRegisterResponse(response){
    if(response.success){
        console.log("hello");
        alert(response.message);
        window.location.href = "../login/login.jsp";       
    }
    else{
        errorMessage.textContent = response.message;
    }
}



