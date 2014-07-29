<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Template</title>
		<#include "../stubs/header.ftl" />
		<link rel="stylesheet" href="/static/css/thomasSignature/jquery.signaturepad.css">
	</head>
	<body id="body">


		<!-- content -->

		<div id="content" class="col-sm-12" >
			<div id="signature_container">
				<div id="tools">
					<a href="#" class="btn btn-primary" id="accept_button" data-role="button" data-inline="true" data-theme="b">Accept</a>
					<a href="#" class="btn btn-default" id="clear_button" data-role="button" data-inline="true">Clear</a>
				</div>
				<div id="signatureparent">
					<div id="signature"></div>
					<div id="scrollgrabber"></div>
				</div>
			</div>
			<div id="details_container">
				<form method="POST" action="">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<div id="complete_container">
						<div class="form-group">
							<label>Signed By</label>
							<input type="text" id="signedBy" name="signedBy" class="form-control"
								placeholder="Signed By" required="true"/>
						</div>
						<div class="form-group">
							<input type="hidden" id="signature" name="signature" class="form-control"
								placeholder="Signature" required="true"/>
						</div>
						<input type="submit" id="complete_button" value="Complete"/>
					</div>
				</form>
			</div>
		</div>
		<!-- content -->
		<div id="newDiv" class="" style="border:3px dashed green;" hidden>
		</div>

		<div id="" class="container" >
			<div class="col-sm-12">
				<button class="btn btn-md btn-primary ">accept</button>
				<button class="btn btn-md btn-default ">Clear</button>
			</div>

		</div>

		<#include "../stubs/scripts.ftl"/>
		<script src="/static/js/jSignature.min.js"></script>
		<script src="/static/js/plugins/signhere/jSignature.SignHere.js"></script>
		<script src="/static/js/init.js"></script>

		<script src="/static/js/thomasSignature/jquery.signaturepad.js"></script>
		<script src="/static/js/thomasSignature/json2.min.js"></script>
		<script>
			$('.sigPad').signaturePad()
		</script>
	</body>
</html>