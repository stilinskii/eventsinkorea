//버디 필터링 구현
const filterBtn = document.querySelector('.filter-btn');

filterBtn.addEventListener('click', handleFiltering);

function handleFiltering() {
  let learningLangVal = document.querySelector('.learning-lang').value;
  let nativeLangVal = document.querySelector('.native-lang').value;
  let locationVal = document.querySelector('.location').value;

  let data = {
    learningLang: learningLangVal,
    nativeLang: nativeLangVal,
    location: locationVal,
  };

  $.post('/buddy/filtering', data).done(function (fragment) {
    $('#buddies').replaceWith(fragment);
  });
}

/**
 *
 * 더보기 버튼 구현
 */
const buddies = document.querySelectorAll('.buddy_info');
let buddiesArr = Array.from(buddies);
const moreBtn = document.querySelector('.more-btn');
let maxCnt = buddies.length;
let showCnt = 1; // default 18개 보이기(17)
showBuddies(showCnt);
//전체글이 18개 이하면 안보이게하기

//more 버튼 누르면 18개씩 보이기
moreBtn.addEventListener('click', addShowCnt);
function addShowCnt() {
  showCnt += 1; //18
  showBuddies(showCnt);
}

function showBuddies(showCnt) {
  if (maxCnt <= showCnt) {
    moreBtn.classList.add('hidden');
  } else {
    moreBtn.classList.remove('hidden');
  }

  buddiesArr.forEach((e) => {
    if (buddiesArr.indexOf(e) > showCnt) {
      e.classList.add('hidden');
    } else {
      //숨겨놨던 event 다시 보이게 하기.
      e.classList.remove('hidden');
    }
  });
}

/**
 *
 * to the top of the page button구현
 */
// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0; // For Safari
  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
  showEvents(1); //글들 다시 숨기기
}
