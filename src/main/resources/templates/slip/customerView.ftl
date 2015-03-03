<!DOCTYPE html>
<html lang="en">
	<head id="head">
		<title>Slip</title>
		<#include "../stubs/header.ftl"/>
	</head>
	<body id="body">

		<!-- content -->
		<div id="content" class="container">
			<div class="col-lg-offset-3 col-lg-6">
				<div class="row">
					<div class="col-lg-7">
						<img src="/static/img/nei.jpg" class="img-responsive"/>
					</div>
					<div  class="col-lg-5 text-right">
						<h3>Delivery Receipt</h3>
						<h3>Job #${slip.jobNumber}</h3>
					</div>
				</div>
				<hr/>
				<div class="row">
					<div class="col-lg-6">
						<h3><u>Your PO #:</u></h3>
						<p>${slip.po}</p>
						<h3><u>Job Name:</u></h3>
						<p>${slip.jobName}</p>
					</div>
					<div class="col-lg-6">
						<h3><u>Delivered To:</u></h3>
						<p>
							<p>${slip.customer}</p>
							${slip.address}<br>
							${slip.city}, ${slip.state} ${slip.zip}
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<h3><u>Details:</u></h3>
						<p>Envelope: ${slip.envelope}</p>
						<p>Quantity: ${slip.quantity}</p>
						<p>Pieces: ${slip.cartons}</p>
						<p>Samples: ${slip.samples}</p>
					</div>
					<div class="col-lg-6">
						<h3><u>Shipping Notes:</u></h3>
						<p>
							${slip.notes}
						</p>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-offset-2 col-lg-8 text-center">
						<h3><u>This Was Received By</u></h3>
						<p>${slip.signature.signedBy}</p><p>${slip.signature.completed}</p>
						<img src="${slip.signature.signature_bin}" class="img-responsive" alt=""/>
					</div>
				</div>
			</div>
		</div>
		<!-- content -->



		<#include "../stubs/scripts.ftl"/>

	</body>
</html>
