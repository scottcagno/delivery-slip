<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Slips</title>
		<#include "../stubs/header.ftl"/>
	</head>
	<body id="body">

		<#include "../stubs/navbar.ftl"/>

		<!-- content -->
		<div id="content" class="container">
			<!-- view all -->
			<div class="col-lg-offset-4 col-sm-4">
				<div class="panel panel-default">
					<div class="panel-heading">Slip ${slip.id}'s Customer</div>
					<div class="panel-body">
						<form role="form" method="post" action="/secure/slip">
							<div class="form-group">
								<input type="text" id="company" name="company" value="${(slip.customer)!}" class="form-control" placeholder="Company" required="true" autofocus="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="contact" name="contact" value="${(slip.contact)!}" class="form-control" placeholder="Contact" required="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="phone" name="phone" value="${(slip.phone)!}" class="form-control" placeholder="Phone" required="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="email" name="email" value="${(slip.email)!}" class="form-control" placeholder="Email" required="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="street" name="street" value="${(slip.address)!}" class="form-control" placeholder="Street" required="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="city" name="city" value="${(slip.city)!}" class="form-control" placeholder="City" required="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="state" name="state" value="${(slip.state)!}" class="form-control" placeholder="State" required="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="zip" name="zip" value="${(slip.zip)!}" class="form-control" placeholder="Zip" required="true"/>
							</div>
							<input type="hidden" name="id" value="${(slip.id)!}"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<button class="btn btn-primary btn-block" type="submit">Save</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- content -->

		<#include "../stubs/footer.ftl"/>

		<#include "../stubs/scripts.ftl"/>

	</body>
</html>
