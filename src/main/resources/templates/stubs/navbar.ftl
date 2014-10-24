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
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav navbar-right">
                    <li><a href="/secure/login?forward=customer"><i class="fa fa-user"></i> Customers</a></li>
                    <li><a href="/secure/login?forward=slip"><i class="fa fa-truck"></i> Slips</a></li>
                    <li><a href="/secure/login?forward=signature"><i class="fa fa-file-o"></i> New Signature</a></li>
                <#if authenticated??>
                    <li><a href="/logout"><i class="fa fa-unlock"></i> Logout (${authenticated})</a></li>
                </#if>
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
	<#elseif alertError??>
		<div id="alert" class="col-sm-10 col-sm-offset-1">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${alertError}
			</div>
		</div>
	<#elseif alertSuccess??>
		<div id="alert" class="col-sm-10 col-sm-offset-1">
			<div class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${alertSuccess}
			</div>
		</div>
	</#if>