<?php
class User {
	public $_id;
	public $first_name;
	public $last_name;
	public $birthday;
	public $email;
	
	public function __construct($row){
		$this->_id = $row['id'];
		$this->first_name = $row['first_name'];
		$this->last_name = $row['last_name'];
		$this->birthday = $row['birthday'];
		$this-> email = $row['email'];
	}
	
	public function getJson(){
		return json_encode($this);
	}
}
