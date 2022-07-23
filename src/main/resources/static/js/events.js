/**
 * event리스트 글 전체 개수 구하기
 */
const totalCntSpan = document.getElementById('totalCnt');
const events = document.querySelectorAll('.event');
function getTotalCntOfEventDivs() {
  totalCntSpan.innerText = 'Total Events: ' + events.length;
}

getTotalCntOfEventDivs();

/**
 * 더보기 버튼 구현
 */
const eventsArr = Array.from(events);
const moreBtn = document.querySelector('.more-btn');
let cnt = 17; // default 18개 보이기
showEvents(cnt);

//more 버튼 누르면 18개씩 보이기
moreBtn.addEventListener('click', showMore);
function showMore() {
  cnt += 18;
  let maxCnt = events.length;
  if (cnt >= maxCnt) {
    cnt = maxCnt;
    moreBtn.classList.add('hidden');
  }
  showEvents(cnt);
}

function showEvents(cnt) {
  eventsArr.forEach((e) => {
    if (eventsArr.indexOf(e) > cnt) {
      e.classList.add('hidden');
    } else {
      //숨겨놨던 event 다시 보이게 하기.
      e.classList.remove('hidden');
    }
  });
}

//더보기 버튼 구현
//
// 몇개 이상 넘어가면 밑에꺼 다 숨기고 더보기 버튼 보이기
//     더보기버튼 클릭하면  리스트 몇개 결정된 수만큼 더 보이기
//    그 밑에 더보기 버튼 보이기.
//    리스트의 맨 끝이면 더보기 버튼 안보이기.

//    document onload
//   if .event.count > 10
//    #all_events의 11th child 부터 .event.count th 까지 안보이게
//   more 버튼 보이게

// event 버튼 onclick
// event 몇개 더 보이게
// if event 다 보인거면 more 버튼 숨기기.
//
