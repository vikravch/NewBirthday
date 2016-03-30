<?php

require_once 'user_manager.php';

$manager = new UserManager();
if (!isset($_GET['id'])){
	$manager->getAllUser();
}else {
	$local_id = $_GET['id'];
	$manager->getOneUser($local_id);
}

/*$type = $_GET['type'];
$manager = new UserManager();
if ($type == "all"){
	$manager->getAllUser();
}else if ($type=="id"){
	$local_id = $_GET['id'];
	$manager->getOneUser($local_id);
}*/



?>
