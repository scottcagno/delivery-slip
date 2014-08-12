<!doctype html>
<html lang="en-US" style="font-family: 'Noto Sans', sans-serif;font-size: 14px; color:#9c9c9c;">
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		<div style="margin: 25px;">
			<div style="width: 600px;margin: 0px auto;background-color: #f5f6f5;border:1px solid #dddddd;-moz-border-radius: 3px;-webkit-border-radius: 3px;">
				<div style="padding-left: 27px;padding-right: 27px; padding-bottom: 27px;">
					<p>Hello ${slip.customer.contact},</p>
					<p>Here are the details on your delivery</p>
					<p>
						<strong>
							Signature: <a href="http://localhost:8080/signature/${slip.signature.id}/mail">Click Here</a> <br>
							Signed On: ${slip.signature.completed?date} <br>
							Signed By: ${slip.signature.signedBy} <br>
							PO: ${slip.po} <br>
							Quantity: ${slip.quantity} <br>
							Cartons: ${slip.cartons} <br>
							Samples: ${slip.samples} <br>
						</strong>
					</p>
					<p>
						NOTICE: If you received this email in error, please delete it;
						we apologize for the inconvenience.
					</p>
				</div>
			</div>
		</div>
	</body>
</html>