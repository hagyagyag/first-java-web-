<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>错误警告</title>

<!-- BOOTSTRAP STYLES-->
<link href="./css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="./css/font-awesome.css" rel="stylesheet" />
<!-- PAGE LEVEL STYLES-->
<link href="./css/error.css" rel="stylesheet" />
</head>
<body>
	<div class="container">

		<div class="row text-center">

			<div class="col-md-12 set-pad">

				<strong class="error-txt">${erro}</strong> 
				<a href="toFind.do"
					class="btn btn-danger"> 
					<i class="fa fa-mail-reply"></i>&nbsp;重新查找
				</a>
			</div>


		</div>
	</div>
	<div class="container">
		<div class="row text-center">
			<div class="col-md-12">All Rights Reserved | &copy
				Yourdomain.com</div>

		</div>

	</div>
</body>
</html>