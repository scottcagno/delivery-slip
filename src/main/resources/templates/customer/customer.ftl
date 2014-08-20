<!DOCTYPE html>
<html lang="en">
<head id="head">
    <title>Customers</title>
    <#include "../stubs/header.ftl">
</head>
    <body id="body">

        <#include "../stubs/navbar.ftl">

        <!-- content -->
        <div id="content" class="container">
            <!-- add/edit -->
            <div class="col-sm-4">
                <div class="panel panel-default">
                    <div class="panel-heading">Add or Update Customer <span class="pull-right"><a href="/secure/user">Add New</a></span></div>
                    <div class="panel-body">
                        <form role="form" method="post" action="/secure/customer">
                            <div class="form-group">
                                <input type="text" id="company" name="company" value="${(customer.company)!}" class="form-control" placeholder="Company" required="true" autofocus="true"/>
                            </div>
                            <div class="form-group">
                                <input type="text" id="contact" name="contact" value="${(customer.contact)!}" class="form-control" placeholder="Contact" required="true"/>
                            </div>
                            <div class="form-group">
                                <input type="text" id="phone" name="phone" value="${(customer.phone)!}" class="form-control" placeholder="Phone" required="true"/>
                            </div>
                            <div class="form-group">
                                <input type="text" id="email" name="email" value="${(customer.email)!}" class="form-control" placeholder="Email" required="true"/>
                            </div>
                            <div class="form-group">
                                <input type="text" id="street" name="street" value="${(customer.street)!}" class="form-control" placeholder="Street" required="true"/>
                            </div>
                            <div class="form-group">
                                <input type="text" id="city" name="city" value="${(customer.city)!}" class="form-control" placeholder="City" required="true"/>
                            </div>
                            <div class="form-group">
                                <input type="text" id="state" name="state" value="${(customer.state)!}" class="form-control" placeholder="State" required="true"/>
                            </div>
                            <div class="form-group">
                                <input type="text" id="zip" name="zip" value="${(customer.zip)!}" class="form-control" placeholder="Zipcode" required="true"/>
                            </div>
                            <input type="hidden" name="id" value="${(customer.id)!}"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class="btn btn-md btn-primary btn-block" type="submit">Save</button>
                        </form>
                    </div>
                </div>
            </div>
            <!-- add/edit -->
            <!-- view all -->
            <div class="col-sm-8">
                <div class="panel panel-default">
                    <div class="panel-heading">Current Customers</div>
                    <div class="panel-body">
						<div id="pagenator" class="text-center">
							<#assign prev = ((customers.firstPage) ? string('1', customers.number))/>
							<#assign next = ((customers.lastPage) ? string (customers.number +1, customers.number + 2)) />
							<ul class="pagination">
								<li ${(customers.firstPage)?string('class="disabled"', '')}><a href="/secure/customer?page=1&sort=${(RequestParameters.sort)!}">First</a></li>
								<li ${(customers.firstPage)?string('class="disabled"', '')}><a href="/secure/customer?page=${prev}&sort=${(RequestParameters.sort)!}">&laquo;</a></li>
								<#list lb..ub as n>
									<li ${(n == customers.number + 1)?string('class="active"', '')}><a href="/secure/customer?page=${n}&sort=${(RequestParameters.sort)!}">${n}</a></li>
								</#list>
								<li ${(customers.lastPage)?string('class="disabled"', '')}><a href="/secure/customer?page=${next}&sort=${(RequestParameters.sort)!}">&raquo;</a></li>
								<li ${(customers.lastPage)?string('class="disabled"', '')}><a href="/secure/customer?page=${customers.totalPages}&sort=${(RequestParameters.sort)!}">Last</a></li>
							</ul>
						</div>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
									<th><a href="/secure/customer?page=${(RequestParameters.page)!}&sort=id">Id</a></th>
                                    <th><a href="/secure/customer?page=${(RequestParameters.page)!}&sort=company">Company</a></th>
                                    <th><a href="/secure/customer?page=${(RequestParameters.page)!}&sort=contact">Contact</a></th>
                                    <th><a href="/secure/customer?page=${(RequestParameters.page)!}&sort=email">Email</a></th>
                                    <th><a href="/secure/customer?page=${(RequestParameters.page)!}&sort=street">Address</a></th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list customers.content as customer>
                                        <tr>
											<td>${(customer.id)!}</td>
                                            <td>${(customer.company)!}</td>
                                            <td>${(customer.contact)!}</td>
                                            <td>${(customer.email)!}</td>
                                            <td>${(customer.street + ' ' + customer.city + ', ' + customer.state + ' ' + customer.zip)!}</td>
                                            <td>
                                                <a href="/secure/customer/${(customer.id)!}?page=${(RequestParameters.page)!}&sort=${(RequestParameters.sort)!}" class="btn btn-xs btn-primary">
                                                    <i class="fa fa-pencil"></i>
                                                </a>
                                                <a href="#" class="btn btn-danger btn-xs" data-id="${(customer.id)!}" data-toggle="modal" data-target="#deleteCheck">
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
                        Permantly remove user? This action cannot be undone.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-md pull-left" data-dismiss="modal">No, Cancel</button>
                        <span id="delete">
                            <form role="form" method="post" action="/secure/customer/{id}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-primary btn-md">Yes, Remove User</button>
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
