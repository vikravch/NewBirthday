<?php
class AppUser {
	public $_id;
	public $name;
	public $password;
	public $email;
	public $phone_id;
	
	public function __construct($row){
		$this->_id = $row['_id'];
		$this->name = $row['name'];
		$this->password = $row['password_user'];
		$this-> email = $row['email'];
		$this-> phone_id = $row['phone_id'];
	}
	
	public function getJson(){
		return json_encode($this);
	}
}
