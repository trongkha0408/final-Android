<?php
require_once('dbConnect.php');

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

require_once('vendor/phpmailer/src/Exception.php');
require_once('vendor/phpmailer/src/PHPMailer.php');
require_once('vendor/phpmailer/src/SMTP.php');
$gmail = $_POST['gmail'];
function createOTP()
{
    return rand(10000, 100000);
}
// passing true in constructor enables exceptions in PHPMailer
$mail = new PHPMailer(true);
try {
    // Server settings
    // $mail->SMTPDebug = SMTP::DEBUG_SERVER; // for detailed debug output
    $mail->SMTPDebug = 0;
    $mail->isSMTP();
    $mail->Host = 'smtp.gmail.com';
    $mail->SMTPAuth = true;
    $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
    $mail->Port = 587;

    $mail->Username = 'kksoftwarelanguagecenter@gmail.com'; // YOUR gmail email
    $mail->Password = 'plffbgscyaylniio'; // YOUR gmail password

    // Sender and recipient settings
    $mail->setFrom('kksoftwarelanguagecenter@gmail.com', 'Language Center');
    $mail->addAddress($gmail);
    $mail->addReplyTo('kksoftwarelanguagecenter@gmail.com', 'Language Center'); // to set the reply to

    $OTP = createOTP();
    // Setting the email content
    $mail->IsHTML(true);
    $mail->Subject = "VERIFY OTP FOR ACCOUNT IN Language Center";
    $mail->Body = "<b>Your OTP: $OTP</b>";
    $mail->AltBody = "<b>Your OTP: $OTP</b>";

    $mail->send();
    echo $OTP;
} catch (Exception $e) {
    echo "error";
}
