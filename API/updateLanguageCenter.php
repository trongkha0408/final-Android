<?php
    require "dbConnect.php";
    // $id = $_POST['id'];
    // $username = $_POST['Username'];
    $pwd = $_POST['PWD'];
    $OTP = $_POST['OTP'];
    $gmail = $_POST['Gmail'];
    $postid = $_POST['PostID'];
    $query = "UPDATE lang_center SET PWD='$pwd', OTP='$OTP', PostID='$postid' WHERE Gmail='$gmail'";
    $result = mysqli_query($connect, $query);
    if($result){
        echo "success";
    }
    else{
        echo "error";
    }
?>