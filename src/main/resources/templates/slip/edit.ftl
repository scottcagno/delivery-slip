<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Slip</title>
		<#include "../stubs/header.ftl"/>
	</head>
	<body id="body">

		<#include "../stubs/navbar.ftl"/>

		<!-- content -->
		<div id="content" class="container">
			<!-- view all -->
			<div class="col-lg-offset-2 col-lg-8 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">Slip ${slip.id?c}'s Customer</div>
					<div class="panel-body">
						<div class="col-lg-6 col-sm-6">
							<table class="table table-condensed table-bordered">
								<tr><td class="text-right">Customer: </td><td>${(slip.customer)!}</td></tr>
								<tr><td class="text-right">Contact: </td><td>${(slip.contact)!}</td></tr>
								<tr><td class="text-right">Phone: </td><td>${(slip.phone)!}</td></tr>
								<tr><td class="text-right">Email: </td><td>${(slip.email)!}</td></tr>
								<tr><td class="text-right">Address: </td><td>${(slip.address)!}</td></tr>
								<tr><td class="text-right">City: </td><td>${(slip.city)!}</td></tr>
								<tr><td class="text-right">State: </td><td>${(slip.state)!}</td></tr>
								<tr><td class="text-right">Zip: </td><td>${(slip.zip)!}</td></tr>
							</table>
						</div>
						<div class="col-lg-6 col-sm-6">
							<form class="form-horizontal" role="form" method="post" action="/secure/slip">
								<div class="form-group">
									<label for="cartons" class="col-xs-3 control-label">Cartons</label>
									<div class="col-xs-9">
										<input type="number" id="cartons" name="cartons" value="${(slip.cartons)!}" class="form-control" placeholder="Pieces" required="true"/>
									</div>
								</div>
								<div class="form-group">
									<label for="cartons" class="col-xs-3 control-label">Quantity</label>
									<div class="col-xs-9">
										<input type="number" id="quantity" name="quantity" value="${(slip.quantity?c)!}" class="form-control" placeholder="Quantity" required="true"/>
									</div>
								</div>
								<div class="col-xs-12">
									<div class="form-group">
										<textarea class="form-control" style="resize:none;" name="notes" id="notes" rows="5" placeholder="Notes">${(slip.notes)!}</textarea>
									</div>
									<input type="hidden" name="id" value="${(slip.id?c)!}"/>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<div class="form-group">
										<button class="btn btn-primary btn-block" type="submit">Save</button>
									</div>
									<div class="form-group">
										<a href="/secure/slip" class="btn btn-default btn-block">Cancel</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- content -->

		<#include "../stubs/footer.ftl"/>

		<#include "../stubs/scripts.ftl"/>

	</body>
</html>
