const ratingEle = document.querySelector('.rating-num');

let rating = parseInt(ratingEle.textContent);

let ratingText = '';
for (let i = rating; i > 0.5; i--) {
  //   ratingText += '&#xf005; ';
  ratingText.concat(' ', '&#xf005;');
}

if (rating == 0.5) {
  ratingText.concat(' ', '&#xf089;');
  //   ratingText += '&#xf089; ';
}

rating = ratingText;

//add element 로 바꿔보자 <i class="fa fa-star"></i> <i class="fa fa-star-o"></i>
