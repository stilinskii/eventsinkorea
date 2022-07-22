var map;
function initMap() {
  let x = parseFloat(document.querySelector('.mapX').value);
  let y = parseFloat(document.querySelector('.mapY').value);
  //127.0591874466
  //37.5117592813
  var seoul = {
    lat: y,
    lng: x,
  };
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 12,
    center: seoul,
  });

  new google.maps.Marker({
    position: seoul,
    map: map,
  });
}

function clipboardShare() {
  // 1. 새로운 element 생성
  var tmpTextarea = document.createElement('textarea');

  // 2. 해당 element에 복사하고자 하는 value 저장
  tmpTextarea.value = window.location.href;

  // 3. 해당 element를 화면에 안보이는 곳에 위치
  tmpTextarea.setAttribute('readonly', '');
  tmpTextarea.style.position = 'absolute';
  tmpTextarea.style.left = '-9999px';
  document.body.appendChild(tmpTextarea);

  // 4. 해당 element의 value를 시스템 함수를 호출하여 복사
  tmpTextarea.select();
  tmpTextarea.setSelectionRange(0, 9999); // 셀렉트 범위 설정
  var successChk = document.execCommand('copy');

  // 5. 해당 element 삭제
  document.body.removeChild(tmpTextarea);

  // 클립보드 성공여부 확인
  if (!successChk) {
    alert('공유링크 복사에 실패하였습니다.');
  } else {
    alert('공유링크가 클립보드에 복사되었습니다!');
  }
}
