<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Template</title>
		<#include "../stubs/header.ftl" />
	</head>

	<body id="body">
		<div id="navbar" class="navbar navbar-default navbar-static-top navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/"><i class="fa fa-home"></i> Home</a>
					<a href="#" id="accept" class="navbar-brand">Acccept</a>
					<a href="#" id="clear" class="navbar-brand">Clear</a>
				</div>
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/secure/slip"><i class="fa fa-truck"></i> Slips</a></li>
						<li><a href="/secure/signature"><i class="fa fa-file-o"></i> New Signature</a></li>
					</ul>
				</div>
			</div>
		</div>

		<#if alert??>
			<div id="alert" class="col-sm-10 col-sm-offset-1">
				<div class="alert alert-info alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					${alert}
				</div>
			</div>
		<#elseif alertError??/>
			<div id="alert" class="col-sm-10 col-sm-offset-1">
				<div class="alert alert-danger alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					${alertError}
				</div>
			</div>
		<#elseif alertSuccess??/>
			<div id="alert" class="col-sm-10 col-sm-offset-1">
				<div class="alert alert-success alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					${alertSuccess}
				</div>
			</div>
		</#if>

		<div id="content" class="container">
			<div class="row">
				<div class="col-xs-12">
					<form class="form-inline col-xs-6" id="signatureForm" role="form" method="post" action="/secure/signature">
						<input type="hidden" id="signature" name="signature_bin"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</div>
			</div>
			<div class="row">
				<div id="signature" class="col-xs-12">
				</div>
			</div>
		</div>

		<#include "../stubs/scripts.ftl"/>

		<script src="/static/js/jSignature.min.js"></script>
		<script src="/static/js/signature.js"></script>
	</body>
</html>