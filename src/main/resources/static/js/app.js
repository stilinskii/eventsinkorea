const totalCntSpan = document.getElementById('totalCnt');

function getTotalCntOfEventDivs() {
  totalCntSpan.innerText =
    'Total Events: ' + document.getElementsByClassName('event').length;
}

getTotalCntOfEventDivs();
