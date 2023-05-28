<?php
    require "dbConnect.php";
    $id = $_POST['id'];
    $query = "DELETE FROM sinhvien WHERE ID='$id'";
    $result = mysqli_query($connect, $query);
    if ($result){
        echo "success";
    }
    else {
        echo "error";
    }
?>