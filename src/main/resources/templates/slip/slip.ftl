<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Slips</title>
		<#include "../stubs/header.ftl">
	</head>
	<body id="body">

		<#include "../stubs/navbar.ftl">

			<!-- content -->
			<div id="content" class="container">


				<!-- add/edit-->
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
				 <!--add/edit -->

				<!-- view all -->
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">Current Slips</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Id</th>
											<th>Created</th>
											<th>Company</th>
											<th>Signature</th>
											<th>Job</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<#list slips as slip>
											<tr>
												<td>${(slip.id)!}</td>
												<td>${(slip.created?string.medium_short)!}</td>
												<td>${(slip.customer.company)!}</td>
												<td>${(slip.signature??)?string('<a href="/secure/signature/slip.signature.id">slip.signature.signedBy</a>', 'Not Signed')}</td>
												<td>${('Job #' + slip.jobNumber?c)!}</td>
												<td>
													<a href="/secure/slip/${(slip.id)!}" class="btn btn-xs btn-primary">
														<i class="fa fa-pencil"></i>
													</a>
													<a href="#" class="btn btn-danger btn-xs" data-id="${(slip.id)!}" data-toggle="modal" data-target="#deleteCheck">
														<i class="fa fa-trash-o"></i>
													</a>
												</td>
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

			<!-- content -->

			<#include "../stubs/footer.ftl">

				<#include "../stubs/scripts.ftl">

					<script>
            $(document).ready(function() {

                // toggle safe delete modal popup
                $('a[data-toggle="modal"]').click(function(){
                    var id = $(this).data('id');
                    var form = $('.modal #delete');
                    form.html(form.html().replace('{id}',id));
                });
            });
        </script>

	</body>
</html>
