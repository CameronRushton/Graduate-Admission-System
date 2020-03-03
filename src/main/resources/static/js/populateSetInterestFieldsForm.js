$(setUpForm);

//populate the dropdown menu and set default choice
function setUpForm() {
    $.get("/department/", function(data, status){
        data.forEach(addOption);
        $("#departments").val($("#department").val());
    });

    $("#submit").click(addDepartmentToForm);
}

//add an option to the default menu
function addOption(item, index) {
    $("#departments").append("<option value='" + item + "'>" + item + "</option>");
}

//when submit is clicked, include the selected option in the form
function addDepartmentToForm(){
    $("#department").val($("#departments option:selected").val());
}