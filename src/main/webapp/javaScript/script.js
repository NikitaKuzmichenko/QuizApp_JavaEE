var x = 0;
function removeInput() {
    if(x>0){
        var currentId = this.id.match(/(\d+)/)[0];
        var elem = document.getElementById("remove" + currentId);
        elem.remove();
        elem = document.getElementById("statement" + currentId);
        elem.remove();
        elem = document.getElementById("correct" + currentId);
        elem.remove();
        elem = document.getElementById("input" + currentId);
        elem.remove();
        x--;
    }
}

function addInput() {

    var str = '<input required id="statement' + (x) + '" type="text" name="statement" style="width: 500px;" class="link mx-5 my-1"/>' +
     '<input type="checkbox" class="largerCheckbox mx-5" id="correct' + (x) + '" name="correct" value="' + (x) + '"/>' +
     '<img class="clickable mx-5" id="remove' + (x) + '" src="<c:url value="/img/trash_can.png"/>" width="30" height="30"/>'

    var div = document.createElement("div");
    div.setAttribute("id", "input" + x)
    div.innerHTML = str;
    document.getElementById("statements").appendChild(div);

    const button =  document.getElementById('remove' + x)
    button.setAttribute("onclick","removeInput()");
    button.onclick = removeInput;

    x++;
};

function changeTestName(){
        var elem = document.getElementById("textName");
        elem.remove();

        var input = document.createElement("input");
        input.setAttribute("name","testName");
        input.setAttribute("type","text");
        input.setAttribute("class","mx-2");
        input.setAttribute("maxlength","40");
        input.required = true;
        input.setAttribute("id","testName");

        var element = document.getElementById("nameMark");
        var parent = element.parentNode;
        parent.insertBefore(input, element);

}

function changeQuestionName(){
        var elem = document.getElementById("questionName");
        elem.remove();

        var input = document.createElement("input");
        input.setAttribute("name","questionName");
        input.setAttribute("type","text");
        input.setAttribute("class","mx-2");
        input.setAttribute("maxlength","40");
        input.required = true;
        input.setAttribute("id","questionName");

        var element = document.getElementById("nameMark");
        var parent = element.parentNode;
        parent.insertBefore(input, element);

}
