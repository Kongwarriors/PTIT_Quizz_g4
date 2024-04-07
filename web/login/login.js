/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
/* global errorMessage */
const errorMessage = document.getElementById('error-message');
function loginUser(){
    //Lay du lieu tu cac element
    var data = {
        username : document.getElementById("username").value,
        password : document.getElementById("password").value
    };
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("POST", "../LoginServlet", true);
    xmlHttpRequest.setRequestHeader("Content-Type", "application/json");

    xmlHttpRequest.onload = function () {
        if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            var jsonResponse = JSON.parse(xmlHttpRequest.responseText);
            console.log(jsonResponse);
            // Xử lý phản hồi JSON từ Servlet tại đây
            handleLoginResponse(jsonResponse);
        }
    };

    xmlHttpRequest.send(JSON.stringify(data));
    
}

function handleLoginResponse(response){
    if(response.success){
        if(response.role === "user"){
            console.log("hello");
            window.location.href = "../user/jsps/userhome.jsp";
        }
        else if(response.role === "admin"){
            window.location.href = "../admin/jsps/adminhome.jsp";
        }
        else{
            errorMessage.textContent = response.message;
        }
    }
}

