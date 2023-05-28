<?php
require "dbConnect.php";
$query = "SELECT * FROM student";
$data = mysqli_query($connect, $query);
class Student
{
    function __construct($studentid, $username, $pwd, $gmail, $OTP)
    {
        $this->StudentID = $studentid;
        $this->Username = $username;
        $this->PWD = $pwd;
        $this->Gmail = $gmail;
        $this->OTP = $OTP;
    }
}
$ST = array();
while ($row = mysqli_fetch_assoc($data)){
    array_push($ST, new Student($row['StudentID'], $row['Username'], $row['PWD'], $row['Gmail'], $row['OTP']));
}
echo json_encode($ST);
?>