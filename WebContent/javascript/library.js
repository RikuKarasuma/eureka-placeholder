function createMovableDiv()
{
    for(var i = 0; i < 44; i++)
      document.getElementById("main-left-content").innerHTML += "<div class='moveDiv'></div>";
}

function createLinesOfMovableDivs(number_of_lines)
{
    for(var i = 0; i < number_of_lines; i++ )
      createMovableDiv();
}
