<?php
require "dbConnect.php";
$langid = $_POST['LangID'];
$query = "SELECT * FROM Post WHERE LangID = '$langid'";
$data = mysqli_query($connect, $query);
class Post
{
    function __construct($postid, $name, $image, $price, $desc, $qty, $phone, $address, $fb, $timeupload, $langid, $dayid, $certid)
    {
        $this->PostID = $postid;
        $this->Name = $name;
        $this->Image = $image;
        $this->Price = $price;
        $this->Descr = $desc;
        $this->Qty = $qty;
        $this->Phone = $phone;
        $this->Address = $address;
        $this->Fb = $fb;
        $this->TimeUpload = $timeupload;
        $this->LangID = $langid;
        $this->DayID = $dayid;
        $this->CertID = $certid;
    }
}
$ST = array();
while ($row = mysqli_fetch_assoc($data)) {
    array_push($ST, new Post($row['PostID'], $row['Name'], $row['Image'], $row['Price'], $row['Descr'], $row['Qty'], $row['Phone'], $row['Address'], $row['Fb'], $row['TimeUpload'], $row['LangID'], $row['DayID'], $row['CertID']));
}
echo json_encode($ST);
