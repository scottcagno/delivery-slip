<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Select Slip</title>
		<#include "../stubs/header.ftl"/>
	</head>
	<body id="body">

		<#include "../stubs/navbar.ftl"/>

		<!-- content -->
		<div id="content" class="container">
			<div id="" class="col-sm-12" >
				<div class="col-sm-offset-3 col-sm-6">
					<img class="img-responsive text-center" src="${sig.signature}">
					<br><br>
					<form id="signatureForm" class="form-inline test-center" role="form" method="post" action="#">
						<div class="input-group">
							<span class="input-group-addon">
								Signed By:
							</span>
							<input type="text" id="signedBy" name="signedBy" class="form-control" required="true" />
							<span class="input-group-btn">
								<button id="done" class="btn btn-md btn-primary" type="submit">Complete</button>
							</span>
						</div>
						<input type="hidden"  name="signature" value="${sig.signature}">
						<input type="hidden"  id="slipIds" name="slipIds">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
					<br>
					<div id="slipDiv" class="list-group" >
						<#list slips as slip>
							<span class="list-group-item">
								<span class="col-xs-10">
									<h4 class="list-group-item-heading">${slip.job}</h4>
									<p class="list-group-item-text">
										${slip.customer.name}
									</p>
								</span>
								<button id="addSlip" data-added="0" data-slipId="${slip.id}" class="btn-default btn">Add</button>
							</span>
						</#list>
					</div>
				</div>
			</div>
		</div>
		<!-- content -->

		<#include "../stubs/footer.ftl"/>

		<#include "../stubs/scripts.ftl"/>
		<script src="/static/js/custom.js"></script>
		<script>
			var slipId = []
			$(document).ready(function() {
				$('button[id="addSlip"]').click(function() {
					if (this.getAttribute('data-added') == '0') {
						slipId.push(this.getAttribute('data-slipId'));
						this.innerText = "Added";
						this.setAttribute('class', 'btn-primary btn');
						this.setAttribute('data-added', '1');
						alert(slipId);
					} else {
						slipId = removeValue(slipId, this.getAttribute('data-slipId'));
						this.innerText = "Add";
						this.setAttribute('class', 'btn-default btn');
						this.setAttribute('data-added', '0');
						alert(slipId);
					}
				});

				$('button[id="done"]').click(function(e){
					e.preventDefault();
					$('input[id="slipIds"]').val(slipId);
					alert($('input[id="slipIds"]').val());
				});
			});
		</script>
	</body>
</html>
