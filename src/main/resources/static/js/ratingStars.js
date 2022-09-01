const ratingNumEle = document.querySelectorAll('.rating-num');

ratingNumEle.forEach((e) => {
  displayRatingStars(e);
});

function displayRatingStars(ratingNumEle) {
  let ratingStartsBox = ratingNumEle.nextElementSibling;
  let rating = parseFloat(ratingNumEle.textContent);

  let star;
  for (let i = rating; i > 0.5; i--) {
    star = document.createElement('i');
    star.classList.add('fa', 'fa-star');
    ratingStartsBox.appendChild(star);
  }

  if (rating % 1 != 0) {
    star = document.createElement('i');
    star.classList.add('fa', 'fa-star-half');
    ratingStartsBox.appendChild(star);
  }
}
