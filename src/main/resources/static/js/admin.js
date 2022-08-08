/**
 * 날짜검색에서 기간입력누르면 기간입력 폼 나오게 햐기
 */

const joinedDate = document.querySelector('#joinedDate');
const dateInputDiv = document.querySelector('.periodSelect');

function OnChange() {
  let selectOption = joinedDate.options[joinedDate.selectedIndex].value;
  if (selectOption == 'Enter Date') {
    dateInputDiv.classList.remove('d-none');
  } else {
    dateInputDiv.classList.add('d-none');
  }
}
console.dir(joinedDate);

/**
 * 삭제 확인 alert 나오게 하기
 */
$(function () {
  $('a.confirmDeletion').click(function () {
    if (!confirm('Are you sure you want to delete?')) return false;
  });
});

function confirmDeletion() {
  $('a.confirmDeletion').click(function () {
    if (!confirm('Are you sure you want to delete?')) return false;
  });
}

//페이지당 카테고리 나오게하기
const pageNameSelect = document.querySelector('.pageName');
pageNameSelect.addEventListener('change', dataSend);

function dataSend() {
  var data = $('.pageName').val();
  var value = { pageId: data };
  $.post('/admin/categories/dataSend', value).done(function (fragment) {
    $('#categories').replaceWith(fragment);
  });
  // $.ajax({
  //   url: '/admin/categories/dataSend',
  //   data: pageSlug,
  //   type: 'POST',
  // }).done(function (fragment) {
  //   $('#categories').replaceWith(fragment);
  // });
}

dataSend();
