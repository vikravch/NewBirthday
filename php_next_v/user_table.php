<?php require_once 'user_manager.php'; ?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Пользователи</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <h1 class="text-center">Пользователи</h1>
	<div class ="container">
		<div class = "row">
			<div class = "col-md-4 col-md-offset-4">
			<table class="table table-bordered table-striped">
				<tr>
					<th>id</th>
					<th>name</th>
					<th>password</th>
					<th>email</th>
					<th>tools</th>
				</tr>
				<?php
					$userManager = new UserManager();
					$userArray = $userManager->getAllAppUserTable();
					//var_dump($userArray);
					
					foreach ($userArray as $user){
						$userClass = $user["user_class"];
						$id = $userClass->_id;
						$name = $userClass->name;
						$password = $userClass->password;
						$email = $userClass->email;
						$phone_id = $userClass->phone_id;
						
						echo "<tr>
								<td>$id</td>
								<td>$name</td>
								<td>$password</td>
								<td>$email</td>
								<td><a href=\"#\"> <span class=\"glyphicon glyphicon-edit\" id=\"$id\"></span></a> <a href=\"#\"><span class=\"glyphicon glyphicon-remove delete\" id=\"$id\"></span></a> </td>
						</tr>";
					}
					
				?>
			</table>
			</div>
		</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	<script>
		$(".delete").click(
			function(){
				var idUser = $(this).attr("id");
				
				var postArray = {
					id: idUser
				}
				
				$.ajax({
					url:'delete_user.php',
					type:'post',
					data:postArray,
					success: function(data){
						alert(data);
					}
				});
				location.reload();
			}
		);
	</script>
  </body>
</html>
