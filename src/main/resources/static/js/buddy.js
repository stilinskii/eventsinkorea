//버디 리퀘스트 확인창 띄우기
$('a.confirmSendReq').click(function () {
  let buddyName = document.querySelector('.buddyName').innerText;
  if (!confirm('send request to ' + buddyName + '?')) return false;
});
