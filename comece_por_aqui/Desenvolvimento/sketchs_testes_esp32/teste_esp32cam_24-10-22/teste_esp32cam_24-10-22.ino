// Bibliotecas do ESP32-CAM
 
#include <WiFi.h>
#include <WiFiClientSecure.h>
#include <soc/soc.h>
#include <soc/rtc_cntl_reg.h>
#include <Base64.h>
#include <esp_camera.h>
//instalar no Arduino
#include <UniversalTelegramBot.h>
#include <ArduinoJson.h>
#include <Tone32.h>
 
 
const int buzina = 21;
const int ledAmarelo = 18;
const int ledVermelho = 20;
const int trig = 24;
const int eccho = 25;
const int sensorPir = 17;
const int smokeA0 =  22;
float distancia_cm;
float duracao;
float leituraPir;
float leituraGas;
#define BUZZER_CHANNEL 0
 
 String header;
 
String sensorBuzina = "Desligada";
String sensorPresenca = "Desligada";
String sensorGas = "Desligada";
String sensorLedAmarelo = "Desligada";
String sensorLedVermelho = "Desligada";
String alarme = "Desligada";
String gas = "Desligada";
 
unsigned long currentTime = millis();
unsigned long previousTime = 0;
const long timeoutTime = 2000;
 
float h ;
float t ;
float q ;
 
//Dados para conexao com a rede Wi-fi
const char* ssid = "Rede5"; //Insira o SSID da rede
const char* password = ""; //Insira a senha da rede
 
 
// Initialize Telegram BOT
String BOTtoken = "5650609996:AGG-r_caN8UBLxDtNZZo7mdeaIFAgOB7kN8";  // your Bot Token (Get from Botfather)
 
// Use @myidbot to find out the chat ID of an individual or a group
// Also note that you need to click "start" on a bot before it can
// message you
String CHAT_ID = "1765125698";
 
bool sendPhoto = false;
 
WiFiClientSecure clientTCP;
UniversalTelegramBot bot(BOTtoken, clientTCP);
 
#define FLASH_LED_PIN 4
bool flashState = LOW;
 
//Checks for new messages every 1 second.
int botRequestDelay = 1000;
unsigned long lastTimeBotRan;
 
//CAMERA_MODEL_AI_THINKER
#define PWDN_GPIO_NUM     32
#define RESET_GPIO_NUM    -1
#define XCLK_GPIO_NUM      0
#define SIOD_GPIO_NUM     26
#define SIOC_GPIO_NUM     27
 
#define Y9_GPIO_NUM       35
#define Y8_GPIO_NUM       34
#define Y7_GPIO_NUM       39
#define Y6_GPIO_NUM       36
#define Y5_GPIO_NUM       21
#define Y4_GPIO_NUM       19
#define Y3_GPIO_NUM       18
#define Y2_GPIO_NUM        5
#define VSYNC_GPIO_NUM    25
#define HREF_GPIO_NUM     23
#define PCLK_GPIO_NUM     22
 
 
void configInitCamera(){
  camera_config_t config;
  config.ledc_channel = LEDC_CHANNEL_0;
  config.ledc_timer = LEDC_TIMER_0;
  config.pin_d0 = Y2_GPIO_NUM;
  config.pin_d1 = Y3_GPIO_NUM;
  config.pin_d2 = Y4_GPIO_NUM;
  config.pin_d3 = Y5_GPIO_NUM;
  config.pin_d4 = Y6_GPIO_NUM;
  config.pin_d5 = Y7_GPIO_NUM;
  config.pin_d6 = Y8_GPIO_NUM;
  config.pin_d7 = Y9_GPIO_NUM;
  config.pin_xclk = XCLK_GPIO_NUM;
  config.pin_pclk = PCLK_GPIO_NUM;
  config.pin_vsync = VSYNC_GPIO_NUM;
  config.pin_href = HREF_GPIO_NUM;
  config.pin_sscb_sda = SIOD_GPIO_NUM;
  config.pin_sscb_scl = SIOC_GPIO_NUM;
  config.pin_pwdn = PWDN_GPIO_NUM;
  config.pin_reset = RESET_GPIO_NUM;
  config.xclk_freq_hz = 20000000;
  config.pixel_format = PIXFORMAT_JPEG;
 
  //init with high specs to pre-allocate larger buffers
  if(psramFound()){
    config.frame_size = FRAMESIZE_UXGA;
    config.jpeg_quality = 10;  //0-63 lower number means higher quality
    config.fb_count = 2;
  } else {
    config.frame_size = FRAMESIZE_SVGA;
    config.jpeg_quality = 12;  //0-63 lower number means higher quality
    config.fb_count = 1;
  }
 
  // camera init
  esp_err_t err = esp_camera_init(&config);
  if (err != ESP_OK) {
    Serial.printf("Camera init failed with error 0x%x", err);
    delay(1000);
    ESP.restart();
  }
 
  // Drop down frame size for higher initial frame rate
  sensor_t * s = esp_camera_sensor_get();
  s->set_framesize(s, FRAMESIZE_CIF);  // UXGA|SXGA|XGA|SVGA|VGA|CIF|QVGA|HQVGA|QQVGA
}
 
void handleNewMessages(int numNewMessages) {
  Serial.print("Handle New Messages: ");
  Serial.println(numNewMessages);
 
  for (int i = 0; i < numNewMessages; i++) {
    String chat_id = String(bot.messages[i].chat_id);
    if (chat_id != CHAT_ID){
      bot.sendMessage(chat_id, "Unauthorized user", "");
      continue;
    }
   
    // Print the received message
    String text = bot.messages[i].text;
    Serial.println(text);
   
    String from_name = bot.messages[i].from_name;
    if (text == "/start") {
      String welcome = "Welcome , " + from_name + "\n";
      welcome += "Use the following commands to interact with the ESP32-CAM \n";
      welcome += "/photo : takes a new photo\n";
      welcome += "/flash : toggles flash LED \n";
      bot.sendMessage(CHAT_ID, welcome, "");
    }
    if (text == "/flash") {
      flashState = !flashState;
      digitalWrite(FLASH_LED_PIN, flashState);
      Serial.println("Change flash LED state");
    }
    if (text == "/photo") {
      sendPhoto = true;
      Serial.println("New photo request");
    }
  }
}
 
String sendPhotoTelegram() {
  const char* myDomain = "api.telegram.org";
  String getAll = "";
  String getBody = "";
 
  camera_fb_t * fb = NULL;
  fb = esp_camera_fb_get();  
  if(!fb) {
    Serial.println("Camera capture failed");
    delay(1000);
    ESP.restart();
    return "Camera capture failed";
  }  
 
  Serial.println("Connect to " + String(myDomain));
 
 
  if (clientTCP.connect(myDomain, 443)) {
    Serial.println("Connection successful");
   
    String head = "--RandomNerdTutorials\r\nContent-Disposition: form-data; name=\"chat_id\"; \r\n\r\n" + CHAT_ID + "\r\n--RandomNerdTutorials\r\nContent-Disposition: form-data; name=\"photo\"; filename=\"esp32-cam.jpg\"\r\nContent-Type: image/jpeg\r\n\r\n";
    String tail = "\r\n--RandomNerdTutorials--\r\n";
 
    uint16_t imageLen = fb->len;
    uint16_t extraLen = head.length() + tail.length();
    uint16_t totalLen = imageLen + extraLen;
 
    clientTCP.println("POST /bot"+BOTtoken+"/sendPhoto HTTP/1.1");
    clientTCP.println("Host: " + String(myDomain));
    clientTCP.println("Content-Length: " + String(totalLen));
    clientTCP.println("Content-Type: multipart/form-data; boundary=RandomNerdTutorials");
    clientTCP.println();
    clientTCP.print(head);
 
    uint8_t *fbBuf = fb->buf;
    size_t fbLen = fb->len;
    for (size_t n=0;n<fbLen;n=n+1024) {
      if (n+1024<fbLen) {
        clientTCP.write(fbBuf, 1024);
        fbBuf += 1024;
      }
      else if (fbLen%1024>0) {
        size_t remainder = fbLen%1024;
        clientTCP.write(fbBuf, remainder);
      }
    }  
   
    clientTCP.print(tail);
   
    esp_camera_fb_return(fb);
   
    int waitTime = 10000;   // timeout 10 seconds
    long startTimer = millis();
    boolean state = false;
   
    while ((startTimer + waitTime) > millis()){
      Serial.print(".");
      delay(100);      
      while (clientTCP.available()) {
        char c = clientTCP.read();
        if (state==true) getBody += String(c);        
        if (c == '\n') {
          if (getAll.length()==0) state=true;
          getAll = "";
        }
        else if (c != '\r')
          getAll += String(c);
        startTimer = millis();
      }
      if (getBody.length()>0) break;
    }
    clientTCP.stop();
    Serial.println(getBody);
  }
  else {
    getBody="Connected to api.telegram.org failed.";
    Serial.println("Connected to api.telegram.org failed.");
  }
  return getBody;
}
 
void setup(){
  WRITE_PERI_REG(RTC_CNTL_BROWN_OUT_REG, 0);
  // Init Serial Monitor
  Serial.begin(115200);
 
  // Set LED Flash as output
  pinMode(FLASH_LED_PIN, OUTPUT);
  digitalWrite(FLASH_LED_PIN, flashState);
  pinMode(trig,OUTPUT);
  pinMode(eccho,INPUT);
  pinMode(buzina,OUTPUT);
  pinMode(ledAmarelo,OUTPUT);
  pinMode(ledVermelho,OUTPUT);
  pinMode (sensorPir,INPUT);
  pinMode(smokeA0, INPUT);
  Serial.println("Iniciei...");
  digitalWrite(trig,LOW);
  digitalWrite(eccho,LOW);
  digitalWrite(buzina,LOW);
  digitalWrite(ledAmarelo,LOW);
  digitalWrite(ledVermelho,LOW);
 
  // Config and init the camera
  configInitCamera();
 
  // Connect to Wi-Fi
  WiFi.mode(WIFI_STA);
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  clientTCP.setCACert(TELEGRAM_CERTIFICATE_ROOT); // Add root certificate for api.telegram.org
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("ESP32-CAM IP Address: ");
  Serial.println(WiFi.localIP());
}
 
void leituraUltrassom(){
 
  // Limpa o pino trigger
digitalWrite(trig, LOW);
delayMicroseconds(150);
digitalWrite(trig, HIGH);
delayMicroseconds(300);
digitalWrite(trig, LOW);
 
 // Faz a leitura no pino eccho
duracao = pulseIn(eccho, HIGH);
distancia_cm = duracao * 0.034/2;
Serial.println("Leitura no Ultrassom com valor em cm: ");
Serial.print (distancia_cm);
Serial.println("-------------------------");
delay(5000);  
 
} //fim leituraUltrassom()
 
 
void desligarBuzina(){
  Serial.println("Buzina desligada");
  Serial.println("---------------------/");
  noTone(buzina, BUZZER_CHANNEL);
  delay(5000);
  } // fim desligarBuzina()
 
 void leituraSensorPresenca(){
  float leituraPir = digitalRead(sensorPir);
  Serial.println("Leitura no sensor de presenca com movimento valorado em: ");
  Serial.print(leituraPir);
  Serial.println(" ---------------------- ");
   delay(5000);
 } // fim leituraSensorPresenca()
 
 void leituraSensorFumaca(){
  float leituraGas = digitalRead(smokeA0);
  Serial.print("Pin A0: ");
  Serial.println(leituraGas);
  Serial.println("--------------");
  delay(5000);
  } // fim leituraSensorFumaca()
 
 void CondicoesFumaca(){
  if (leituraGas>100){
sensorGas = "Ligado";
sensorLedAmarelo = "Desligado";
sensorLedVermelho = "Ligado";
digitalWrite(ledVermelho, HIGH);
digitalWrite(ledAmarelo, LOW);
sensorBuzina = "Ligado";
tone(buzina, 1000, 200, BUZZER_CHANNEL);
//se estiver fora da esp32-cam, use: tone(buzina, 1000, 200);
delay(1000);
sensorBuzina = "Desligado";
noTone(buzina, BUZZER_CHANNEL);
   }
 
  else {
sensorGas = "Ligado";
sensorLedAmarelo = "Ligado";
sensorLedVermelho = "Desliigado";
digitalWrite(ledVermelho, LOW);
digitalWrite(ledAmarelo, HIGH);
sensorBuzina = "Desligado";
noTone(buzina, BUZZER_CHANNEL);
delay(1000);
    }
    delay(5000);
   
  } // fim CondicoesFumaca()
 
void EnviarImagem(){
  if (sendPhoto) {
    Serial.println("Preparing photo");
    sendPhotoTelegram();
    sendPhoto = false;
  }
  if (millis() > lastTimeBotRan + botRequestDelay)  {
    int numNewMessages = bot.getUpdates(bot.last_message_received + 1);
    while (numNewMessages) {
      Serial.println("got response");
      handleNewMessages(numNewMessages);
      numNewMessages = bot.getUpdates(bot.last_message_received + 1);
    }
    lastTimeBotRan = millis();
  }
 
}// fim EnviarImagem()
 
 
 
 
 
 
void loop() {
  WiFiServer server(80);
  WiFiClient client = server.available();
 
  leituraSensorPresenca();
 
if (client) {
currentTime = millis();
previousTime = currentTime;
 
Serial.println("New Client.");
String currentLine = "";
 
while (client.connected() && currentTime - previousTime <= timeoutTime) {
currentTime = millis();
 
if (client.available()) {
char c = client.read();
Serial.write(c);
header += c;
 
if (c == '\n') {
 
if (currentLine.length() == 0) {
client.println("HTTP/1.1 200 OK");
client.println("Content-type:text/html");
client.println("Connection: close");
client.println();
 
if(leituraPir>0)
{
if (header.indexOf("GET /alarme/ligado") >= 0) {
sensorPresenca = "Ligado";
leituraUltrassom();
sensorBuzina= "Ligado";
  if(distancia_cm > 10.0){
sensorLedAmarelo = "Ligado";
sensorLedVermelho = "Desligado";
 Serial.println("Movimento detectado em medio alcance!");
 digitalWrite(ledAmarelo, HIGH);
 delay(5000);
 tone(buzina, 4500, 5000, BUZZER_CHANNEL);
 desligarBuzina();
 digitalWrite(ledAmarelo,LOW);
}// fim distancia>10
 
if(distancia_cm<=10.0){
 sensorLedAmarelo = "Desligado";
 sensorLedVermelho = "Ligado";
  Serial.println ("Perigo! Movimento detectado muito perto!");
  digitalWrite(ledVermelho, HIGH);
  delay(5000);
  tone(buzina, 600, 5000, BUZZER_CHANNEL);
  delay (5000);
  desligarBuzina();
  digitalWrite(ledVermelho, LOW);
  } // fim distancia<10
 
EnviarImagem();
sensorPresenca = "Desligado";
sensorBuzina = "Desligado";
 
}// fim header alarme
}// fim do leitura pir maior que zero
 
leituraSensorFumaca();
 
if(leituraGas>0){
sensorGas = "Ligado";
sensorBuzina = "Ligado";
if(header.indexOf("GET/gas/ligado")>=0){
CondicoesFumaca();
EnviarImagem();
}
 
}// fim do leituraGas
// Passando o html
h = leituraPir;
t = leituraGas;
q = distancia_cm;
 
client.println("<!DOCTYPE html><html>");
client.println("<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
client.println("<link rel=\"icon\" href=\"data:,\">");
 
client.println("<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}");
 
client.println(".button { background-color: #B84F4F; border: none; color: white; padding: 16px 40px;");
client.println("text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}");
client.println(".button2 {background-color: #4CAF50;}</style></head>");
 
 
client.println("<body><h1>CONTROLE PARA MAQUETE</h1>");
client.println("<hr>");
client.println("<hr>");
client.println("<h3> Leitura do  PIR: " + (String)t + "</h3>");
client.println("<h3>  Leitura do Gás: " + (String)h + "</h3>");
client.println("<h3>  Leitura do Ultrassom: " + (String)q + "</h3>");
 
client.println("<hr>");
client.println("<h3> Alarme -  " + alarme + "</h3>");
if (alarme == "Ligado") {
client.println("<p><a href=\"/alarme/ligado\"><button class=\"button\">MONITORAR ALARME </button></a></p>");
} else {
client.println("<p><a href=\"/gas/ligado\"><button class=\"button button2\">MONITORAR GÁS </button></a></p>");
}
 
client.println("</body></html>");
 
client.println();
break;
} else {
currentLine = "";
}
} else if (c != '\r') {
currentLine += c;
}
}
}
 
header = "";
 
client.stop();
Serial.println("Client disconnected.");
Serial.println("");
}
 
 
}// fim do loop
