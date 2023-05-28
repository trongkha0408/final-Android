<?php
require "dbConnect.php";
$query = "SELECT * FROM certificate";
$data = mysqli_query($connect, $query);
class Certificate
{
    function __construct($id, $name)
    {
        $this->CertID = $id;
        $this->Name = $name;
    }
}
$ST = array();
while ($row = mysqli_fetch_assoc($data)){
    array_push($ST, new Certificate($row['CertID'], $row['Name']));
}
echo json_encode($ST);
?>