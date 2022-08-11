// 버튼
//필터3개 밸류
//이벤트 실행할 함수

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
