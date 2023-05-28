<?php
require "dbConnect.php";
$query = "SELECT * FROM daystudy";
$data = mysqli_query($connect, $query);
class DayStudy
{
    function __construct($dayid, $day, $time)
    {
        $this->DayID = $dayid;
        $this->Day = $day;
        $this->Time = $time;
    }
}
$ST = array();
while ($row = mysqli_fetch_assoc($data)){
    array_push($ST, new DayStudy($row['DayID'], $row['Day'], $row['Time']));
}
echo json_encode($ST);
?>