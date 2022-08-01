/**
 * 날짜검색에서 기간입력누르면 기간입력 폼 나오게 햐기
 */

const joined_date = document.querySelector('#joined_date');
const dateInputDiv = document.querySelector('.periodSelect');

function OnChange() {
  let selectOption = joined_date.options[joined_date.selectedIndex].value;
  if (selectOption == '기간입력') {
    dateInputDiv.classList.remove('d-none');
  } else {
    dateInputDiv.classList.add('d-none');
  }
}
console.dir(joined_date);

/**
 * 삭제 확인 alert 나오게 하기
 */
$(function () {
  $('a.confirmDeletion').click(function () {
    if (!confirm('Are you sure you want to delete this user?')) return false;
  });
});
