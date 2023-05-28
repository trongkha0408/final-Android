<?php
require "dbConnect.php";
$name = $_POST['Name'];
$price = $_POST['Price'];
$img = $_POST['upload'];
$des = $_POST['Descr'];
$qty = $_POST['Qty'];
$phone = $_POST['Phone'];
$address = $_POST['Address'];
$fb = $_POST['Fb'];
$timeUpload = date("H:i d/m/Y");
$langID = $_POST['LangID'];
$dayID = $_POST['DayID'];
$certID = $_POST['CertID'];

$filename = "IMG" . $langID ."_.jpg";
file_put_contents("../images/" . $filename, base64_decode($img));

$qry = "INSERT INTO Post (Name, Image, Price, Descr, Qty, Phone, Address, Fb, TimeUpload, LangID, DayID, CertID) VALUES ('$name', '$filename', '$price', '$des', '$qty', '$phone', '$address', '$fb', '$timeUpload', '$langID', '$dayID', '$certID')";
// $qry = "INSERT INTO post (Name, Image, Price, Descr, Qty, Phone, Address, Fb, TimeUpload, LangID, DayID, CertID)VALUES ('abc', 'aiuysgdyudsouhfsiudhfsd', '23.0', 'asdyufgaysudfgyasudgas', '4', '0123456789', 'aisgduiasgduas', 'asdyjgasyudgasy', '20/10/2022', '1', '1', '1')";

$res = mysqli_query($connect, $qry);

if ($res)
	echo "success";
else
	echo "error";
