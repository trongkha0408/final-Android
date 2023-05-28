<?php
    require "dbConnect.php";
    // $id = $_POST['id'];
    // $username = $_POST['Username'];
    $pwd = $_POST['PWD'];
    $OTP = $_POST['OTP'];
    $gmail = $_POST['Gmail'];
    $query = "UPDATE student SET PWD='$pwd', OTP='$OTP' WHERE Gmail='$gmail'";
    $result = mysqli_query($connect, $query);
    if($result){
        echo "success";
    }
    else{
        echo "error";
    }
?>