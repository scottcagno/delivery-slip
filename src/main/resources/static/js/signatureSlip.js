var slipId = []
$(document).ready(function() {
	$('button[id="addSlip"]').click(function() {
		if (this.getAttribute('data-added') == '0') {
			slipId.push(this.getAttribute('data-slipId'));
			this.innerText = "Added";
			this.setAttribute('class', 'btn-primary btn');
			this.setAttribute('data-added', '1');
			$('button[id="done"]').removeAttr('disabled');
		} else {
			slipId = removeValue(slipId, this.getAttribute('data-slipId'));
			this.innerText = "Add";
			this.setAttribute('class', 'btn-default btn');
			this.setAttribute('data-added', '0');
			if (slipId.length < 1) {
				$('button[id="done"]').attr('disabled', 'disabled');
			};
		}
	});
	$('button[id="done"]').click(function(e){
		e.preventDefault();
		if ($('input[id="signedBy"]').val() == '') {
			$('label[id="error"]').removeClass('hide')
		} else {
			$('input[id="slipIds"]').val(slipId);
			$('div[id="content"]').addClass('hide');
			$('div[id="addSpinner"]').removeClass('hide');
			$('form[id="signatureForm"]').submit();
		}
	});
});