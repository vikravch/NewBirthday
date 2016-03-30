<?php
	class RegUserManager{
	
	public function register($name, $password_user, $phone_id, $email){
		$serverName = "mysql.hostinger.com.ua";
		$userName = "u374069841_admin";
		$password = "123454321";
		$dbName = "u374069841_users";
		$tableName = "users";
		$connection = new mysqli($serverName, $userName, $password, $dbName);
		if ($connection->connect_error!=null){
			echo 0;
		} 
		$queryCheckName = "SELECT * FROM `app_users` WHERE `name`='$name'";
		$queryCheckEmail = "SELECT * FROM `app_users` WHERE `email`='$email'";
		$queryCheckLogin = "SELECT * FROM `app_users` WHERE `name`='$name' AND `password_user`='$password_user'";
		$query = "INSERT INTO `app_users` (name, password_user, phone_id, email) VALUES ('$name', '$password_user', '$phone_id', '$email')";
		
		if ($connection->query($queryCheckName)->num_rows>0){
			echo 3; 
		} else
		if ($connection->query($queryCheckEmail)->num_rows>0){  
			echo 4;
		} else
		if ($connection->query($query)){
			echo 1;
		} else{
			echo 2;
			}
		$connection->close();
	}
	
	public function login($name, $password_user){
		$serverName = "mysql.hostinger.com.ua";
		$userName = "u374069841_admin";
		$password = "123454321";
		$dbName = "u374069841_users";
		$tableName = "users";
		$connection = new mysqli($serverName, $userName, $password, $dbName);
		if ($connection->connect_error!=null){
			echo 0;
		} 
		$queryCheckLogin = "SELECT * FROM `app_users` WHERE `name`='$name' AND `password_user`='$password_user'";
		if ($connection->query($queryCheckLogin)->num_rows>0)
			{ 
				echo 5; 
			}
		else{ 
				echo 6; 
			} 
		
	}
	
	}
	
	if (isset($_POST["name"])){
		$name = $_POST["name"];}
	
	if (isset($_POST["password_user"])){
		$password_user = $_POST["password_user"];}
	
	if (isset($_POST["phone_id"])){
		$phone_id = $_POST["phone_id"];}
	
	if (isset($_POST["email"])){
		$email = $_POST["email"];}
		
	if (isset($_POST["type"])){
		$type = $_POST["type"];}
	
	$regObject = new RegUserManager();
	switch ($type){
		case 0:
			$regObject->register($name, $password_user, $phone_id, $email);
			break;
		case 1:
			$regObject->login($name, $password_user);
			break;
		}
	
	
