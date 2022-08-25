$('a.confirmDeletion').click(function () {
  if (!confirm('Are you sure you want to delete?')) return false;
});

$('a.confirmCancel').click(function () {
  if (!confirm('Are you sure you want to cancel the request?')) return false;
});
