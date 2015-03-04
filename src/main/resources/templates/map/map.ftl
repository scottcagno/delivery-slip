<!DOCTYPE html>
<html lang="en">
	<head id="head">
	    <title>Map</title>
	    <#include "../stubs/header.ftl"/>
	</head>
	    <body id="body">

		<div class="container">
			<div class="row">
	   			<img src="/map/map"/>
			</div>
			<div class="row">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Sort</th>
							<th>Company</th>
							<th>PO</th>
							<th>Job</th>
							<th>Job Name</th>
							<th>Quantity</th>
							<th>Cartons</th>
							<th>Samples</th>
						</tr>
					</thead>
					<tbody>

						<#list slips as slip>
							<tr>
								<td>${(slip.sort)!}</td>
								<td>${(slip.customer)!}</td>
								<td>${(slip.po)!}</td>
								<td>${('Job #' + slip.jobNumber?c)!}</td>
								<td>${(slip.jobName)!}</td>
								<td>${(slip.quantity)!}</td>
								<td>${(slip.cartons)!}</td>
								<td>${(slip.samples)!}</td>
							</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>

	    <#include "../stubs/footer.ftl"/>

	</body>
</html>
