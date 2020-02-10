
// https://stackoverflow.com/questions/1402698/binding-arrow-keys-in-js-jquery
// I used this thread to figure out how to use the keydown method.

$(document).ready( ()=>{
    // Grabs the morse, cleans it up, hides the ugly morse with the slashes, displays the clean morse
    // and gets the correct answers
    const correctAnswerMorse = checkMorse($("#output").text());
    const correctAnswerEnglish = $("#correctAnswer").val();
    $("#output").hide();
    $("#correctAnswer").hide();
    const cleanMorse = displayMorse($("#output").text());
    $("#display").text(cleanMorse);

    // delete removes the last character input
    // space bar creats a space in the input
    // left arrow makes a dit sound and inputs a "."
    // right arrow makes a dah sound and inputs a "-"


    // Could add comments to explain which keys are which - Done
    $(document).keydown(function(event){
        if(event.which==8){ // delete key
            $("#input").text(()=> $("#input").text().slice(0, $("#input").text().length-1));
         }
        if (event.which == 32) { // space bar
            $("#input").text(()=> $("#input").text() + " ");
        }
        if (event.which == 37) { // left arrow
            playDit();
            $("#input").text(()=> $("#input").text() + ".");
         }
        if (event.which == 39) { // right arrow
            playDah();
            $("#input").text(()=> $("#input").text() + "-");
         }

    })

    // pressing play plays the audio of the output

    const morse = $("#output").text();
    $("#play").click(function(){
    playAudio(morse);
    })
    // checks the input against the correct answer for english to morse
    $("#checkMorse").click(function checkMorse(event){
    if(correctAnswerMorse == $("#input").text()){
    alert("Correct!");

    } else {
        event.preventDefault();
        $("#input").text("");
        alert("Incorrect!");
    }
    })
    // checks the input against the correct answer for morse to english
    $("#checkEnglish").click(function checkEnglish(event){
    if(correctAnswerEnglish == $("#userAnswer").val().toLowerCase()){
        alert("Correct!");
    } else {
    console.log(correctAnswerEnglish, $("#userAnswer").val().toLowerCase());
        event.preventDefault();
        alert("Incorrect!");
    }
    })
    $("#messageSubmit").click(()=>{
    $("#messageText").val($("#input").text());
    })
});

// plays the audio with the correct timing
function playAudio(string){
console.log(string);
    counter = 0;
    bar = false;
    space = false;
    timeUnit = 300;
    for(i = 0; i < string.length; i++){
        if(string.charAt(i) == "."){
            if(space){
            setTimeout(playDit,((7 * timeUnit) + (timeUnit * counter)));
            counter += 8;
            space = false;
            } else if(bar){
            console.log("dit " + ((3 * timeUnit) + (timeUnit * counter)))
                setTimeout(playDit,((3 * timeUnit) + (timeUnit * counter)));
                counter += 4;
                bar = false;
            } else {
            console.log("dit " + (timeUnit * counter))
                setTimeout(playDit,(timeUnit * counter));
                counter++;
            }
        } else if(string.charAt(i) == "-"){
            if(space){
                setTimeout(playDah,((7 * timeUnit) + (timeUnit * counter)));
                counter++;
                space = false;
                } else if(bar){
                console.log("dah " + ((3 * timeUnit) + (timeUnit * counter)))
                    setTimeout(playDah,((3 * timeUnit) + (timeUnit * counter)));

                    counter++;
                    bar = false;
                } else {
                console.log("dah " + (timeUnit * counter))
                    setTimeout(playDah,(timeUnit * counter));

                    counter++;
                }
            } else if(string.charAt(i) == "/"){
            bar = true;
        } else if (string.charAt(i) == " "){
            space = true;
        }
    }
}

// plays the dit sound
function playDit(){
    $("audio#dit")[0].currentTime = 0;
    $("audio#dit")[0].play();
}

// plays the dah sound
function playDah(){
    $("audio#dah")[0].currentTime = 0;
    $("audio#dah")[0].play();
}

// cleans the morse up by changing the /s to spaces for display
function displayMorse(code){
     result = "";
     for(i = 0;i<code.length;i++){
         if(code.charAt(i) == "."){
             result += ".";
         } else if(code.charAt(i) == "-"){
             result += "-";
         } else if(code.charAt(i) == "/"){
             result += " ";
         }
     }
     return result;
 }

// cleans the morse up by getting rid of /s to use as a check for the correct answer
 function checkMorse(code){
     result = "";
     for(i = 0;i<code.length;i++){
         if(code.charAt(i) == "."){
             result += ".";
         } else if(code.charAt(i) == "-"){
             result += "-";
         }
     }
     return result;
 }

