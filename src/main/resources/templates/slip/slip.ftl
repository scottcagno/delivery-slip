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
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">Current Slips</div>
					<div class="panel-body">
						<#if slips.totalPages != 0>
							<div id="pagenator" class="text-center">
								<#assign isFirst = (slips.number + 1) == 1/>
								<#assign isLast = (slips.number + 1) == slips.totalPages/>
								<#assign prev = ((isFirst) ? string('1', slips.number))/>
								<#assign next = ((isLast) ? string (slips.number +1, slips.number + 2)) />
								<ul class="pagination">
									<li class="${(isFirst)?string('disabled', '')}"><a href="/secure/slip?page=1&sort=${(RequestParameters.sort)!}">First</a></li>
									<li class="${(isFirst)?string('disabled', '')}"><a href="/secure/slip?page=${prev}&sort=${(RequestParameters.sort)!}">&laquo;</a></li>
									<#list lb..ub as n>
										<li class="${(n == slips.number + 1)?string('active', '')}"><a href="/secure/slip?page=${n}&sort=${(RequestParameters.sort)!}">${n}</a></li>
									</#list>
									<li class="${(isLast)?string('disabled', '')}"><a href="/secure/slip?page=${next}&sort=${(RequestParameters.sort)!}">&raquo;</a></li>
									<li class="${(isLast)?string('disabled', '')}"><a href="/secure/slip?page=${slips.totalPages}&sort=${(RequestParameters.sort)!}">Last</a></li>
								</ul>
							</div>
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=id">Id</a></th>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=created">Created</a></th>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=customer.company">Company</a></th>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=po">PO</a></th>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=jobNumber">Job</a></th>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=jobName">Job Name</a></th>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=quantity">Quantity</a></th>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=cartons">Cartons</a></th>
											<th><a href="/secure/slip?page=${(RequestParameters.page)!}&sort=samples">Samples</a></th>
											<th>Signature</th>
											<th></th>
											<!--<th>Actions</th>-->
										</tr>
									</thead>
									<tbody>
	
										<#list slips.content as slip>
											<tr>
												<td>${(slip.id)!}</td>
												<td>${(slip.created)!}</td>
												<td><a href="/secure/slip/${slip.id}">${(slip.customer)!}</a></td>
												<td>${(slip.po)!}</td>
												<td>${('Job #' + slip.jobNumber?c)!}</td>
												<td>${(slip.jobName)!}</td>
												<td>${(slip.quantity)!}</td>
												<td>${(slip.cartons)!}</td>
												<td>${(slip.samples)!}</td>
												<td>
													<#if slip.signature??>
														<a href="/secure/signature/${slip.signature.id}/view">${slip.signature.signedBy}</a>
													<#else/>
														Not Signed
													</#if>
												</td>
	
												<td>
													<a id="notes_${slip.id}" class="notes" href="#">Notes</a>
													<span id="notes_${slip.id}" hidden="hidden">${slip.notes!}</span>
												</td>
											</tr>
										</#list>
									</tbody>
								</table>
							</div>
						</#if>
					</div>
				</div>
			</div>
			<!-- view all -->
		</div>
		<div class="modal fade" id="notesModal" tabindex="-1" role="dialog" aria-hidden="true" >
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span
								aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title" id="myModalLabel">Notes</h4>
					</div>
					<div id="notesModalBody" class="modal-body">
					</div>
				</div>
			</div>
		</div>

		<!-- content -->

		<#include "../stubs/footer.ftl"/>

		<#include "../stubs/scripts.ftl"/>

		<script src="/static/js/slip.js"></script>

	</body>
</html>
