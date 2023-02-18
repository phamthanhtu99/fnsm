import { API } from "../com/api.js";

var gl = document.querySelector.bind(document);
var ggl = document.querySelectorAll.bind(document);

gl('#login').addEventListener('click', checklogin)

function checklogin() {
    debugger;
    var us = gl('input[name=username]').value;
    var pw = gl('input[name=password]').value;


    if (us.trim().length == 0 && pw.trim().length == 0) {
        alert("Please enter User & pass ! ");
    } else if (us.trim().length == 0 || pw.trim().length == 0) {
        if (us.trim().length == 0) {
            alert("Please enter User !");
        } else {
            alert("Please enter Pass ! ");
        }
    }

    var getdata = {
        "userName": us,
        "pass": pw
    };

    var options = {
        IDAPI: "check_login",
        url: "/login",
        data: JSON.stringify(getdata),
        callback: callback
    };

    API.POST(options)
}

function callback(rs) {
    var id_api = rs.id;
    debugger
    switch (id_api) {
        case "check_login":
            if (rs.LOGIN_CHK == 0) {
                showOTP()
            }else{
                alert("Please enter user and pass")
            }
            break;
        case "check_otp":
            if(rs.MES == true){
                alert("ok");
                select_role(rs.role)

            }else{
                alert("OPT invalid");
                gl('#login_check').children.opt.value = '';
            }
            break;
        default:
            break;
    }
}


function showOTP() {
    gl('#login_check').innerHTML = ` <div class="hide-md-lg">
                                        <p>OTP:</p>
                                    </div>

                                    <input type="text" name="opt" placeholder="OTP" required>
                                   
                                    <input type="button" style="background-color: sandybrown;" value="SEND" id="otp">`
    //add event click 
    gl('#login_check').children.otp.addEventListener('click', check_otp)
}


function select_role(roles){
    var role = roles.map(item=>`<input type="button" style=" background-color: sandybrown;" name="${item.name}" value="${item.name}" id="${item.id}">`).join('')
    gl('#login_check').innerHTML = ` <div class="hide-md-lg">
                                        <p>CHON QUYEN TRY CAP:</p>
                                    </div>
                                    ${role}
                                    `
    var btn_role = gl('#login_check').querySelectorAll("input");

    btn_role.forEach(item=>{
        item.addEventListener('click',check_role)
    })
}

function check_otp() {
    var otp = gl('#login_check').children.opt.value;

    if (otp.trim().length == 0) {
        alert('Please enter otp !')
        return;
    }

    var getdata = {
        "OTP" : otp
    };

    var options = {
        IDAPI: "check_otp",
        url: "/check_otp",
        data: JSON.stringify(getdata),
        callback: callback
    };

    API.POST(options)
}

function check_role(){
    debugger
    var role = this.value
    
}