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


			<!-- add/edit
			<div class="col-sm-4">
				<div class="panel panel-default">
					<div class="panel-heading">Slip Details</div>
					<div class="panel-body">
						<form role="form" method="post" action="/secure/slip">
							<div class="form-group">
								<span>${('Created: ' + slip.created)!}</span>
							</div>
							<div class="form-group">
								<#if slip??>
									<a href="/secure/customer/${(slip.customer.id?c)!}">${('Company: ' + slip.customer.company)!}</a>
								</#if>
							</div>
							<div class="form-group">
								<#if slip??>
									<a href="/signature/${(slip.signature.id?c)!}/view">Signed By: ${(slip.signature.signedBy)!'Not signed'}</a>
								</#if>
							</div>
							<div class="form-group">
								<input type="number" id="sort" name="sort" value="${(slip.sort?c)!}" class="form-control" placeholder="Sort" required="true"/>
							</div>
							<div class="form-group">
								<input type="number" id="jobNumber" name="jobNumber" value="${(slip.jobNumber?c)!}" class="form-control" placeholder="Job Number" required="true"/>
							</div>
							<div class="form-group">
								<input type="number" id="quantity" name="quantity" value="${(slip.quantity?c)!}" class="form-control" placeholder="Quantity" required="true"/>
							</div>
							<div class="form-group">
								<input type="number" id="cartons" name="cartons" value="${(slip.cartons?c)!}" class="form-control" placeholder="Cartons" required="true"/>
							</div>
							<div class="form-group">
								<input type="number" id="samples" name="samples" value="${(slip.samples?c)!}" class="form-control" placeholder="Samples" required="true"/>
							</div>
							<div class="form-group">
								<input type="number" id="complete" name="complete" value="${(slip.complete?c)!}" class="form-control" placeholder="Complete" required="true"/>
							</div>
							<div class="form-group">
								<input type="number" id="active" name="active" value="${(slip.active?c)!}" class="form-control" placeholder="Active" required="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="jobName" name="jobName" value="${(slip.jobName)!}" class="form-control" placeholder="Job Name" required="true"/>
							</div>
							<div class="form-group">
								<input type="text" id="po" name="po" value="${(slip.po)!}" class="form-control" placeholder="PO #" required="false"/>
							</div>
							<div class="form-group">
								<input type="text" id="notes" name="notes" value="${(slip.jobName)!}" class="form-control" placeholder="Job Name" required="true"/>
							</div>
							<input type="hidden" name="id" value="${(slip.id)!}"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<button class="btn btn-md btn-primary btn-block" type="submit">Save</button>
						</form>
					</div>
				</div>
			</div>
			 add/edit  -->

			<!-- view all -->
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">Current Slips</div>
					<div class="panel-body">
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
											<td><a href="/secure/customer/${(slip.customer.id)!}">${(slip.customer.company)!}</a></td>
											<td>${(slip.po)!}</td>
											<td>${('Job #' + slip.jobNumber?c)!}</td>
											<td>${(slip.jobName)!}</td>
											<td>${(slip.quantity)!}</td>
											<td>${(slip.cartons)!}</td>
											<td>${(slip.samples)!}</td>
											<td>
												<#if slip.signature??>
													<a href="/secure/signature/${slip.signature.id}/view">${slip.signature.signedBy}</a>
												<#else>
													Not Signed
												</#if>
											</td>

											<!--
											<td>
												<a id="notes_${slip.id}" class="notes" href="#">Notes</a>
												<span id="notes_${slip.id}" hidden="hidden">${slip.notes}</span>
											</td>
											<td>
												<a href="/secure/slip/${(slip.id)!}" class="btn btn-xs btn-primary">
													<i class="fa fa-pencil"></i>
												</a>
												<a href="#" class="btn btn-danger btn-xs" data-id="${(slip.id)!}" data-toggle="modal" data-target="#deleteCheck">
													<i class="fa fa-trash-o"></i>
												</a>
											</td>
											-->

										</tr>
									</#list>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- view all -->
		</div>

		<div class="modal fade" id="deleteCheck" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Are you sure?</h4>
					</div>
					<div class="modal-body">
						Permantly remove slip? This action cannot be undone.
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default btn-md pull-left" data-dismiss="modal">No, Cancel</button>
                        <span id="delete">
                            <form role="form" method="post" action="/secure/slip/{id}">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<button type="submit" class="btn btn-primary btn-md">Yes, Remove Slip</button>
							</form>
                        </span>
					</div>
				</div>
			</div>
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

		<script>
            $(document).ready(function() {

                // toggle safe delete modal popup
                $('a[data-toggle="modal"]').click(function(){
                    var id = $(this).data('id');
                    var form = $('.modal #delete');
                    form.html(form.html().replace('{id}',id));
                });

                // populate notes modal
                $('a[class="notes"]').click(function() {
					$('div[id="notesModalBody"]').html($('span[id="' + this.id + '"]').html());

					$('div[id="notesModal"]').modal();
                });
            });
        </script>

	</body>
</html>
