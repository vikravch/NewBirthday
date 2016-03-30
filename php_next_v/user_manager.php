<?php
require_once 'user.php';
require_once 'appuser.php';

class UserManager {
	
	public function __construct(){}
	
	public function getOneUser($id){
		$serverName = "mysql.hostinger.com.ua";
		$userName = "u374069841_admin";
		$password = "123454321";
		$dbName = "u374069841_users";
		$tableName = "users";
		$connection = new mysqli($serverName, $userName, $password, $dbName);
		if ($connection->connect_error!=null){
			echo("Connection failed with err ".$connection->connect_error);
		}
		$sql = "SELECT * FROM `$tableName`";
		$result = $connection->query($sql);
		$users_array = array();
		if ($result->num_rows>0){
			while ($row = $result -> fetch_assoc()){
				$loc_user = new User($row);
				$users_array[$row['id']] = array ( 
					'user_class' => $loc_user, 
					'json'=> $loc_user-> getJson()
				);
			}
			echo $users_array[$id]['json'];
		}
	else {echo "";}
	}

	public function getAllUser(){
		$serverName = "mysql.hostinger.com.ua";
		$userName = "u374069841_admin";
		$password = "123454321";
		$dbName = "u374069841_users";
		$tableName = "users";
		$connection = new mysqli($serverName, $userName, $password, $dbName);
		if ($connection->connect_error!=null){
			echo("Connection failed with err ".$connection->connect_error);
		}
		$sql = "SELECT * FROM `$tableName`";
		$result = $connection->query($sql);
		$users_array = array();
		if ($result->num_rows>0){
			while ($row = $result -> fetch_assoc()){
				$loc_user = new User($row);
				$users_array[] = array ( 
					'user_class' => $loc_user 
				);
			}
			echo json_encode($users_array);
		}
	else {echo "";}
	}
	
	public function getAllUserTable(){
		$serverName = "mysql.hostinger.com.ua";
		$userName = "u374069841_admin";
		$password = "123454321";
		$dbName = "u374069841_users";
		$tableName = "users";
		$connection = new mysqli($serverName, $userName, $password, $dbName);
		if ($connection->connect_error!=null){
			echo("Connection failed with err ".$connection->connect_error);
		}
		$sql = "SELECT * FROM `$tableName`";
		$result = $connection->query($sql);
		$users_array = array();
		if ($result->num_rows>0){
			while ($row = $result -> fetch_assoc()){
				$loc_user = new User($row);
				$users_array[] = array ( 
					'user_class' => $loc_user 
				);
			}
		}
		return $users_array;
	}
	
	public function getAllAppUserTable(){
		$serverName = "mysql.hostinger.com.ua";
		$userName = "u374069841_admin";
		$password = "123454321";
		$dbName = "u374069841_users";
		$tableName = "app_users";
		$connection = new mysqli($serverName, $userName, $password, $dbName);
		if ($connection->connect_error!=null){
			echo("Connection failed with err ".$connection->connect_error);
		}
		$sql = "SELECT * FROM `$tableName`";
		$result = $connection->query($sql);
		$users_array = array();
		if ($result->num_rows>0){
			while ($row = $result -> fetch_assoc()){
				$loc_user = new AppUser($row);
				$users_array[] = array ( 
					'user_class' => $loc_user 
				);
			}
		}
		return $users_array;
	}
	
	public function removeAppUser($id){
		$serverName = "mysql.hostinger.com.ua";
		$userName = "u374069841_admin";
		$password = "123454321";
		$dbName = "u374069841_users";
		$tableName = "app_users";
		$connection = new mysqli($serverName, $userName, $password, $dbName);
		if ($connection->connect_error!=null){
			echo("Connection failed with err ".$connection->connect_error);
		}
		$sql = "DELETE FROM `$tableName` WHERE `_id`=$id";
		return $connection->query($sql);
	}
}
