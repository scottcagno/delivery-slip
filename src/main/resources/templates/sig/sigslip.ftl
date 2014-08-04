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
				<div class="col-lg-offset-3 col-lg-6">
					<img class="img-responsive text-center" src="${signature.signature}">
					<br><br>
					<form id="signatureForm" class="form-inline test-center" role="form" method="post" action="/signature">
						<div class="input-group">
							<span class="input-group-addon">
								Signed By:
							</span>
							<input type="text" id="signedBy" name="signedBy" class="form-control" required="true" />
							<span class="input-group-btn">
								<button id="done" class="btn btn-md btn-primary" type="submit">Complete</button>
							</span>
						</div>
						<input type="hidden"  name="signature" value="${signature.signature}">
						<input type="hidden"  name="id" value="${signature.id}">
						<input type="hidden"  id="slipIds" name="slipIds">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
					<br>
					<div id="slipDiv" class="list-group" >
						<#list slips as slip>
							<div class="list-group-item">
								<div class="col-xs-10">
									<h4 class="list-group-item-heading">${slip.jobName}</h4>
									<p class="list-group-item-text">
										${slip.customer.contact}
									</p>
								</div>
								<button id="addSlip" data-added="0" data-slipId="${slip.id}" class="btn-default btn">Add</button>
							</div>
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
					} else {
						slipId = removeValue(slipId, this.getAttribute('data-slipId'));
						this.innerText = "Add";
						this.setAttribute('class', 'btn-default btn');
						this.setAttribute('data-added', '0');
					}
				});

				$('button[id="done"]').click(function(e){
					e.preventDefault();
					$('input[id="slipIds"]').val(slipId);
					$('form[id="signatureForm"]').submit();
				});
			});
		</script>
	</body>
</html>
