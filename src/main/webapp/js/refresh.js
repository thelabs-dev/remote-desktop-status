var refresh_rate 		= 30; //<-- In seconds, change to your needs
var last_user_action 	= 0;
var has_focus 			= true;


function reset() {
    last_user_action = 0;
    //console.log("Reset");
}

function windowHasFocus() {
    has_focus = true;
	//console.log("windowHasFocus");
}

function windowLostFocus() {
    has_focus = false;
    //console.log("windowLostFocus");
}

setInterval(function () {
    last_user_action++;
    refreshCheck();
}, 1000);

function refreshCheck() {
	//console.log("refreshCheck");
	
    var focus = window.onfocus;
    if (has_focus && (last_user_action >= refresh_rate  && document.readyState == "complete")) {
        reset();
		window.location.reload();
    }

}
window.addEventListener("focus", windowHasFocus, false);
window.addEventListener("blur", windowLostFocus, false);
