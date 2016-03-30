<? 
	require_once 'user_manager.php';
	
	$id = $_POST['id'];
	
	$userManager = new UserManager();
	
	if ($userManager-> removeAppUser($id)>0){
		echo "Успешно!! ".$id;
	}
	else{
		echo "Ошибка удаления ".$id;
	}
	
	
