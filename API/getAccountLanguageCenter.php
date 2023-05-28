<?php
require "dbConnect.php";
$query = "SELECT * FROM lang_center";
$data = mysqli_query($connect, $query);
class LangCenter
{
    function __construct($langid, $username, $pwd, $gmail, $OTP)
    {
        $this->LangID = $langid;
        $this->Username = $username;
        $this->PWD = $pwd;
        $this->Gmail = $gmail;
        $this->OTP = $OTP;
    }
}
$LC = array();
while ($row = mysqli_fetch_assoc($data)){
    array_push($LC, new LangCenter($row['LangID'], $row['Username'], $row['PWD'], $row['Gmail'], $row['OTP']));
}
echo json_encode($LC);
?>