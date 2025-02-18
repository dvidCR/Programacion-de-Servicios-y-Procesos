Set-ExecutionPolicy Restricted
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12

$botToken = "7702472621:AAFrUTYuTrJzKm7kkx4GMKRMumUn_Gzf384" 
$chatID = "-4574653463" 
$messageText = "Adios"

$params = @{
    chat_id = $chatID
    text = $messageText
}

$url = "https://api.telegram.org/bot$botToken/sendMessage"

try {
    $response = Invoke-RestMethod -Uri $url -Method Post -ContentType "application/json" -Body ($params | ConvertTo-Json)
    Write-Host "Mensaje enviado correctamente"
    $response | ConvertTo-Json -Depth 3
}
catch {
    Write-Host "Error al enviar el mensaje: $_"
}