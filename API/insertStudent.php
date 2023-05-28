<?php
    require "dbConnect.php";
    $username = $_POST['Username'];
    $pwd = $_POST['PWD'];
    $gmail = $_POST['Gmail'];
    $OTP = $_POST['OTP'];
    $query = "INSERT INTO student (Username, PWD, Gmail, OTP) VALUES('$username', '$pwd', '$gmail', '$OTP')";
    $result = mysqli_query($connect, $query);
    if($result){
        echo "success";
    }
    else{
        echo "error";
    }
?>