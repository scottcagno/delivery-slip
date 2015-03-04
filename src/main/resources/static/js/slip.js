$(document).ready(function() {
    // populate notes modal
    $('a[class="notes"]').click(function() {
        $('div[id="notesModalBody"]').html($('span[id="' + this.id + '"]').html());
        $('div[id="notesModal"]').modal();
    });
});