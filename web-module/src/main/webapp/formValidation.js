function validate(formName) {
    var elements = document.forms[formName].elements;
    for (var i = 0; i < elements.length; i++) {
        if ((elements[i].value == "") || (elements[i].value.trim() == '')) {
            alert("Fields must be filled out!");
            return;
        }
    }
    document.forms[formName].submit();
}
