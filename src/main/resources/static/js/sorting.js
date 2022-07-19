/**
 * events sorting
 * @param {*} sortMenu : sorting의 기준이 될 값이 들어있는 element의 클래스 이름
 * @param {*} clicked_this : 클릭한 자기자신
 */
function sortElementByDate(sortMenu, clicked_this) {
  const allEvents = document.getElementById('all_events');
  const elements = Array.from(allEvents.querySelectorAll('.event'));
  const sortIn = sortingOrder(clicked_this);
  managingClassesOfSortBtn(clicked_this, sortIn);

  //Sort elements
  const sortedElement = elements.sort((a, b) => {
    const aVal = a.querySelector('.' + CSS.escape(sortMenu)).value;
    const bVal = b.querySelector('.' + CSS.escape(sortMenu)).value;
    if (sortIn == 'asc') {
      return aVal - bVal;
    } else {
      return bVal - aVal;
    }
  });

  //Remove all existing event divs from container
  while (allEvents.firstChild) {
    allEvents.removeChild(allEvents.firstChild);
  }

  // Re-add the newly sorted event divs
  allEvents.append(...sortedElement);
}

/**
 *
 * @param {*} clicked_this
 * @returns sorting in what?
 */
function sortingOrder(clicked_this) {
  const sortBtn = clicked_this.querySelector('i');
  console.log(sortBtn);
  //desc 있는 상태에서 버튼을 눌렀다는 건 asc를 하고싶다는 뜻.
  if (sortBtn.classList.contains('fa-sort-desc')) {
    return 'asc';
  } else {
    //처음 눌렀을때도 desc로
    return 'desc';
  }
}

/**
 *
 * @param {*} clicked_this
 * @param {*} sortIn
 */
//sort button add or remove asc/desc/basic class
function managingClassesOfSortBtn(clicked_this, sortIn) {
  const sortBtn = clicked_this.querySelector('i');
  if (sortIn == 'desc') {
    sortBtn.classList.remove('fa-sort-asc');
    //처음 눌렀을때도 desc로
    sortBtn.classList.remove('fa-sort');
    sortBtn.classList.add('fa-sort-desc');
  } else if (sortIn == 'asc') {
    sortBtn.classList.remove('fa-sort-desc');
    sortBtn.classList.add('fa-sort-asc');
  }

  //주변 i에 sort에 적용 안됐다는 표시 basic fa-sort넣기
  const sortBtns = Array.from(document.querySelectorAll('.sorting-btns i'));
  sortBtns.forEach((e) => {
    if (e != sortBtn) {
      e.classList.remove('fa-sort-desc');
      e.classList.remove('fa-sort-asc');
      e.classList.add('fa-sort');
    }
  });
}
