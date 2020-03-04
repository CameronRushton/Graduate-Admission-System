$(populate);

//add each interest to the page
function populate(){
    $.get("/interest/", function(data, status){
        data.forEach(addDisplay);
    });
}

//add an interest with update and delete buttons
function addDisplay(interest, index){
    var interestContainer = document.createElement("DIV");
    interestContainer.append(newP(interest.department));
    interestContainer.append(newP(interest.keyword));
    interestContainer.append(newButton("update", function(){
            window.location.href = "/interest/update?id=" + interest.id;
            }));
    interestContainer.append(newButton("delete", function(){
            $.ajax({
                url: "/interest/delete/" + interest.id,
                type: 'DELETE',
                success: function(result) {
                      window.location.reload();
                }
            });}));
    $("#interests").append(interestContainer);
}

//create a p tag with specified text
function newP(text){
    var p = document.createElement("P");
    p.innerText = text;
    return p;
}

//create a button with specified text and onclick handler
function newButton(text, handler){
    var b = document.createElement("BUTTON");
    var label = document.createTextNode(text);
    b.appendChild(label);
    b.addEventListener("click", handler);
    return b;
}