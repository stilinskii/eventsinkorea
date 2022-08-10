$(function () {
  if ($('#introduction').length) {
    ClassicEditor.create(document.querySelector('#introduction')).catch(
      (error) => {
        console.log(error);
      }
    );
  }
});
