//버디 필터링 솔팅 구현
let learningLangSelector = document.querySelector(
  '.filtering-sec .learning-lang'
);
let nativeLangSelector = document.querySelector('.filtering-sec .native-lang');
let locaitonSelector = document.querySelector('.filtering-sec .location');
let sortingSelector = document.querySelector('.sorting');

learningLangSelector.addEventListener('change', handleFilteringAndSorting);
nativeLangSelector.addEventListener('change', handleFilteringAndSorting);
locaitonSelector.addEventListener('change', handleFilteringAndSorting);
sortingSelector.addEventListener('change', handleFilteringAndSorting);

let newlyFiltered = false;
function handleFilteringAndSorting() {
  let learningLangVal = learningLangSelector.value;
  let nativeLangVal = nativeLangSelector.value;
  let locationVal = locaitonSelector.value;
  let sortingVal = sortingSelector.value;

  let data = {
    learningLang: learningLangVal,
    nativeLang: nativeLangVal,
    location: locationVal,
    sorting: sortingVal,
  };

  $.post('/buddy/filtering', data).done(function (fragment) {
    $('#buddies').replaceWith(fragment);
  });

  $(document).ajaxComplete(function () {
    if (document.body.contains(document.querySelector('.noMore'))) {
      console.log('ajaxcompeleet access');
      $('.more-btn').css('display', 'none');
    } else {
      $('.more-btn').css('display', 'block');
    }
  });
  newlyFiltered = true;
  page = 1;
}

/**
 * 더보기 구현
 */
const moreBtn = document.querySelector('.more-btn');
moreBtn.addEventListener('click', showMore);
let index = true;
let page = 0;
function showMore() {
  //새롭게 filtering 된게 아니라면(필터링 적용 전인 index도 포함)
  if (!newlyFiltered) {
    page += 1;
  }

  let url = '/buddy/buddypage?page=' + page;
  console.log(url);
  $.get(url).done(function (data) {
    $('#buddies').append(data);
  });
  newlyFiltered = false;

  $(document).ajaxComplete(function () {
    if (document.body.contains(document.querySelector('.noMore'))) {
      console.log('ajaxcompeleet access');
      $('.more-btn').css('display', 'none');
    }
  });
}

// //더이상 다음내용 없으면 더보기버튼 안보이게 하기
// if (document.body.contains(document.getElementById('.noMore'))) {
//   $('.more-btn').css('display', 'none');
// }

/**
 *
 * 더보기 버튼 구현
 */
// const buddies = document.querySelectorAll('.buddy_info');
// let buddiesArr = Array.from(buddies);
// const moreBtn = document.querySelector('.more-btn');
// let maxCnt = buddies.length;
// let showCnt = 1; // default 18개 보이기(17)
// showBuddies(showCnt);
// //전체글이 18개 이하면 안보이게하기

// //more 버튼 누르면 18개씩 보이기
// moreBtn.addEventListener('click', addShowCnt);
// function addShowCnt() {
//   showCnt += 1; //18
//   showBuddies(showCnt);
// }

// function showBuddies(showCnt) {
//   if (maxCnt <= showCnt) {
//     moreBtn.classList.add('hidden');
//   } else {
//     moreBtn.classList.remove('hidden');
//   }

//   buddiesArr.forEach((e) => {
//     if (buddiesArr.indexOf(e) > showCnt) {
//       e.classList.add('hidden');
//     } else {
//       //숨겨놨던 event 다시 보이게 하기.
//       e.classList.remove('hidden');
//     }
//   });
// }

/**
 *
 * to the top of the page button구현
 */
// When the user clicks on the button, scroll to the top of the document
// function topFunction() {
//   document.body.scrollTop = 0; // For Safari
//   document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
//   showEvents(1); //글들 다시 숨기기
// }

// let learningLangSelector = document.querySelector(
//   '.filtering-sec .learning-lang'
// );
// let nativeLangSelector = document.querySelector('.filtering-sec .native-lang');
// let locaitonSelector = document.querySelector('.filtering-sec .location');
// let sortingSelector = document.querySelector('.sorting');
// learningLangSelector.addEventListener('change', testloc);
// nativeLangSelector.addEventListener('change', testloc);
// locaitonSelector.addEventListener('change', testloc);
// sortingSelector.addEventListener('change', testloc);

// function testloc() {
//   let learningLangVal = learningLangSelector.value;
//   let nativeLangVal = nativeLangSelector.value;
//   let locationVal = locaitonSelector.value;
//   let sortingVal = sortingSelector.value;

//   let link =
//     'http://' +
//     location.host +
//     '/buddy?learning=' +
//     learningLangVal +
//     '?native=' +
//     nativeLangVal +
//     '?location=' +
//     locationVal +
//     '?sorting=' +
//     sortingVal;
//   console.log(link);

//   window.location = link;
// }
