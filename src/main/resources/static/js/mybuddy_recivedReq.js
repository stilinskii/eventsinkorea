const requestsBtn = document.querySelector('.requests');
const historybtn = document.querySelector('.history');

const requestsDiv = document.querySelector('.requests-list');
const historyDiv = document.querySelector('.history-list');

requestsBtn.addEventListener('click', handleRequestsAndhistoryBtn);
historybtn.addEventListener('click', handleRequestsAndhistoryBtn);

function handleRequestsAndhistoryBtn() {
  requestsBtn.classList.toggle('active');
  historybtn.classList.toggle('active');
  requestsDiv.classList.toggle('d-none');
  historyDiv.classList.toggle('d-none');
}

$('a.confirmAccept').click(function () {
  if (!confirm('Accept the request?')) return false;
});

$('a.confirmDecline').click(function () {
  if (!confirm('Are you sure you want to refuse the request?')) return false;
});
