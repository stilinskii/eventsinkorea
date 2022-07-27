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
