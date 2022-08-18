/**
 * like
 * @param {*} ele like button element
 * @param {*} buddyId buddy Id
 */
function like(ele, buddyId) {
  let addVal;
  if (ele.classList.contains('fa-heart')) {
    addVal = false;
  } else {
    addVal = true;
  }
  //하트 채우기 비우기
  ele.classList.toggle('fa-heart');
  ele.classList.toggle('fa-heart-o');

  let value = { buddyId: buddyId, add: addVal };
  let likeCnt = ele.nextElementSibling;
  console.log(likeCnt);
  $.ajax({
    type: 'post',
    url: '/buddy/like',
    data: value,
    success: function (data) {
      likeCnt.innerHTML = data;
    },
  });
  alert('좋아용완료');
}
