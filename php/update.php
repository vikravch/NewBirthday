<?php
	public class UpdateUser{
		public function update($id, $first_name, $last_name, $birthday, $email){
			$serverName = "mysql.hostinger.com.ua";
			$userName = "u374069841_admin";
			$password = "123454321";
			$dbName = "u374069841_users";
			$tableName = "users";
			$connection = new mysqli($serverName, $userName, $password, $dbName);
			if ($connection->connect_error!=null){
				echo 0;
			}
			
			$sql = "UPDATE `$tableName` SET first_name = '$first_name', last_name = '$last_name',
			birthday = '$birthday', email='$email' WHERE _id=$id";
			
			if ($connection->query($sql))
				 {echo 8;}
			else {echo 9;}
		}
	}
	
	if (isset($_POST['_id'])){
		$id = $_POST['_id'];
	}
	if (isset($_POST['first_name'])){
		$first_name = $_POST['first_name'];
	}
	if (isset($_POST['last_name'])){
		$last_name = $_POST['last_name'];
	}
	if (isset($_POST['birthday'])){
		$birthday = $_POST['birthday'];
	}
	if (isset($_POST['email'])){
		$email = $_POST['email'];
	}
	$updateUser = new UpdateUser();
	$updateUser->update($id, $first_name, $last_name, $birthday, $email);
