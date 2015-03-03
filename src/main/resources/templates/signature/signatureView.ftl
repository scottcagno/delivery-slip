<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Signature</title>
		<#include "../stubs/header.ftl"/>
	</head>
	<body id="body">

		<#include "../stubs/navbar.ftl"/>

		<!-- content -->
		<div id="content" class="container">
			<p class="lead">Signed By: ${signature.signedBy}</p>
			<p class="lead">Signed On: ${signature.completed}</p>
			<div id="signature" class="col-sm-12" >
				<img class="img-responsive text-center" src="${signature.signature_bin}">
			</div>
		</div>
		<!-- content -->

		<#include "../stubs/footer.ftl"/>

		<#include "../stubs/scripts.ftl"/>

	</body>
</html>
