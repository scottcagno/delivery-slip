<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Select Slip</title>
		<#include "../stubs/header.ftl"/>
	</head>
	<body id="body">

		<#include "../stubs/navbar.ftl"/>

		<!-- content -->
		<div class="container">
			<div id="content" class="col-sm-12">
				<div class="col-lg-offset-3 col-lg-6">
					<img class="img-responsive text-center" src="${signature.signature_bin}">
					<br><br>
					<form id="signatureForm" class="test-center" role="form" method="post" action="/secure/signature/${signature.id}">
						<label id="error" class="text-danger hide">* Signed By is required</label>
						<div class="input-group">
							<span class="input-group-addon">
								Signed By:
							</span>
							<input type="text" id="signedBy" name="signedBy" class="form-control" required="true"/>
							<span class="input-group-btn">
								<button id="done" class="btn btn-md btn-primary" type="submit" disabled="disabled">Complete</button>
							</span>
						</div>
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
										${slip.customer}
									</p>
								</div>
								<button id="addSlip" data-added="0" data-slipId="${slip.id}" class="btn-default btn">Add</button>
							</div>
						</#list>
					</div>
				</div>
			</div>
			<!-- add card spinner -->
			<div id="addSpinner" class="text-center hide">
				<p class="lead">
					<strong>Adding signature to slips...</strong><br/>
				</p>
				<i class="fa fa-5x fa-circle-o-notch fa-spin"></i>
				<p class="lead">One moment please.</p>
			</div>
			<!-- add card spinner -->
		</div>
		<!-- content -->

		<#include "../stubs/footer.ftl"/>

		<#include "../stubs/scripts.ftl"/>
		<script src="/static/js/custom.js"></script>
		<script src="/static/js/signatureSlip.js"></script>
	</body>
</html>
