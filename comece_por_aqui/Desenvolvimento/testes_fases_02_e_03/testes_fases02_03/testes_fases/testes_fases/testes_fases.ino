// Bibliotecas do ESP32-CAM

#include "WiFi.h"
#include "WiFiClientSecure.h"
#include "soc.h"
#include "rtc_cntl_reg.h"
#include "Base64.h"
#include "esp_camera.h"

#define buzina  2
#define ledAmarelo 3
#define ledVermelho 4
#define trig  8
#define eccho 9
#define sensorPir A0
#define smokeA0 = A5;
float distancia_cm;
float duracao;
float leituraPir;

//Dados para conexão com a rede Wi-fi
const char* ssid = "Lunara"; //Insira o SSID da rede
const char* password = "d9-48Z11"; //Insira a senha da rede

//Dados para conexão com o Google Drive
const char* host = "script.google.com";
String meuScript = "/macros/s/AKfycbx3Yw3vKdp9GuNdxeeWvZVtPMBS-bmE38MbSUpzOIu7gULcfUAU/exec";

 
//Dados para o arquivo de imagem
String nomedoArquivo = "filename=ESP32-CAM.jpg";
String mimeType = "&mimetype=image/jpeg";
String Imagem = "&data=";
 
int tempo_espera = 10000;  //Espera 10 segundos para o Google responder
 
//Definição dos pinos
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
#define flash 4
 

// Ligações originais do vídeo: ledVermelho = 8 , ledVerde = 7, buzina = 9 , eccho (envia sinal) = 2, 
//trigger(recebe sinal) = 3 ; resistor na perna negativa. O negativo vai direto pro 
// negativo do polo na placa. O positivo que liga direto no pino que deseja. O vídeo usa o Fritzing.
// A buzina tem a perna positiva ligada na porta direta.

void setup() {
  // put your setup code here, to run once:

  pinMode(trig,OUTPUT);
  pinMode(eccho,INPUT);
  pinMode(buzina,OUTPUT);
  pinMode(ledAmarelo,OUTPUT);
  pinMode(ledVermelho,OUTPUT);
  pinMode (sensorPir,INPUT);
  pinMode(smokeA0, INPUT);
   Serial.println("Iniciei...");

  WRITE_PERI_REG(RTC_CNTL_BROWN_OUT_REG, 0);
   
  Serial.begin(115200);
  delay(100);
   
  WiFi.mode(WIFI_STA);
 
  Serial.println("");
  Serial.print("Conectando na rede: ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);  

  
  while (WiFi.status() != WL_CONNECTED) {  
    Serial.print(".");
    delay(500); 
  }
//Definindo flash da câmera
  pinMode(flash,OUTPUT);
 
  Serial.println("Conectado!");
  Serial.println();
  Serial.print("endereço STAIP: ");
  Serial.println(WiFi.localIP());
  Serial.println();
 
  //Configuração do pinos da câmera
  camera_config_t config;
  config.ledc_channel = LEDC_CHANNEL_0;
  config.ledc_timer = LEDC_TIMER_0;
  config.pin_d0    = Y2_GPIO_NUM;
  config.pin_d1    = Y3_GPIO_NUM;
  config.pin_d2    = Y4_GPIO_NUM;
  config.pin_d3    = Y5_GPIO_NUM;
  config.pin_d4    = Y6_GPIO_NUM;
  config.pin_d5    = Y7_GPIO_NUM;
  config.pin_d6    = Y8_GPIO_NUM;
  config.pin_d7    = Y9_GPIO_NUM;
  config.pin_xclk  = XCLK_GPIO_NUM;
  config.pin_pclk  = PCLK_GPIO_NUM;
  config.pin_vsync = VSYNC_GPIO_NUM;
  config.pin_href  = HREF_GPIO_NUM;
  config.pin_sscb_sda = SIOD_GPIO_NUM;
  config.pin_sscb_scl = SIOC_GPIO_NUM;
  config.pin_pwdn     = PWDN_GPIO_NUM;
  config.pin_reset    = RESET_GPIO_NUM;
  config.xclk_freq_hz = 20000000;
  config.pixel_format = PIXFORMAT_JPEG;
  config.frame_size   = FRAMESIZE_VGA;  
  config.jpeg_quality = 4;
  config.fb_count     = 1;
   
  esp_err_t err = esp_camera_init(&config); //Inicialização da câmera
   
 
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
 
}
 void desligarBuzina(){
  Serial.println("Buzina desligada");
  Serial.println("---------------------/");
  noTone(buzina);
  delay(5000);
  }


 void leituraSensorPresenca(){
  leituraPir = analogRead(sensorPir);
  Serial.println("Leitura no sensor de presenca com movimento valorado em: ");
  Serial.print(leituraPir);
  Serial.println(" ---------------------- ");
   delay(5000);
 }

 void leituraSensorFumaca(){
  int analogSensor = analogRead(smokeA0);
  Serial.print("Pin A0: "); 
  Serial.println(analogSensor);
  Serial.println("--------------"); 
  delay(5000);
  }


  void CondicoesFumaca(){
  if (analogSensor>100){


digitalWrite(ledVermelho, HIGH); 
digitalWrite(ledAmarelo, LOW); 
tone(buzina, 1000, 200);
delay(1000); 
noTone(buzzer);             
  }

  else {
    digitalWrite(ledVermelho, LOW); 
digitalWrite(ledAmarelo, HIGH); 
noTone(buzina);
delay(1000);
    }
    delay(5000);
    
  }


  void Captura_Imagem() {
   
  Serial.println("Conectando ao " + String(host));
   
  WiFiClientSecure client;
   
  if (client.connect(host, 443)) { //Conectando no Google
     
    Serial.println("Conexão com sucesso!");
   
    camera_fb_t * fb = NULL;
     
    digitalWrite(flash,HIGH);
    delay(100);
     
    fb = esp_camera_fb_get(); //Função que captura imagem na ESP
     
    digitalWrite(flash,LOW);
    delay(100);
     
    if(!fb) {
       
      Serial.println("Falha ao capturar imagem!");
      delay(1000);
      ESP.restart();
      return;
     
    }
     
    char *input = (char *)fb->buf;
    char output[base64_enc_len(3)];
    String imageFile = "";
     
    for (int i=0;i<fb->len;i++) {
      base64_encode(output, (input++), 3);
      if (i%3==0) imageFile += urlencode(String(output));
    }
     
    String Data = nomedoArquivo+mimeType+Imagem;
     
    esp_camera_fb_return(fb); 
     
    Serial.println("Enviando imagem capturada ao Google Drive.");
     
    client.println("POST " + meuScript + " HTTP/1.1");
    client.println("Host: " + String(host));
    client.println("Content-Length: " + String(Data.length()+imageFile.length()));
    client.println("Content-Type: application/x-www-form-urlencoded");
    client.println();
     
    client.print(Data);
     
    int Index;
     
    for (Index = 0; Index < imageFile.length(); Index = Index+1000) {
      client.print(imageFile.substring(Index, Index+1000));
    }
     
    Serial.println("Aguardando resposta."); 
     
    long int tempo_inicio = millis();
     
    while (!client.available()) { //Aguarda resposta do envio da imagem
      Serial.print(".");
      delay(100);
      if ((tempo_inicio+tempo_espera) < millis()) {
        Serial.println();
        Serial.println("Sem Resposta.");
        break;
      }
    }

     Serial.println();   
     
    while (client.available()) {
      Serial.print(char(client.read())); //Mostra na tela a resposta
    }  
  } else {         
    Serial.println("Conexão ao " + String(host) + " falhada.");
  }
  client.stop();
}

String urlencode(String str)  // Função de codificação
{
    String encodedString="";
    char c;
    char code0;
    char code1;
    char code2;
     
    for (int i =0; i < str.length(); i++){
      c=str.charAt(i);
      if (c == ' '){
        encodedString+= '+';
      } else if (isalnum(c)){
        encodedString+=c;
      } else{
        code1=(c & 0xf)+'0';
        if ((c & 0xf) >9){
            code1=(c & 0xf) - 10 + 'A';
        }
        c=(c>>4)&0xf;
        code0=c+'0';
        if (c > 9){
            code0=c - 10 + 'A';
        }
        code2='\0';
        encodedString+='%';
        encodedString+=code0;
        encodedString+=code1;
      }
      yield();
    }
    return encodedString;
}


    
void loop() {
  
leituraUltrassom();
  leituraSensorPresenca();
  leituraSensorFumaca();
  
  if (leituraPir>0){
     if (err != ESP_OK) {
     
    Serial.printf("O início da câmera falhou com erro 0x%x", err);   //Informa erro se a câmera não for iniciada corretamente
    delay(1000);
    ESP.restart();//Reinicia o ESP
  
  }

   Captura_Imagem(); //Captura e envia imagem
  delay(1000);
   

  if(distancia_cm > 10.0){
 Serial.println("Movimento detectado em medio alcance!");
 digitalWrite(ledAmarelo, HIGH);
 delay(5000);
 tone(buzina, 4500);
 delay(5000);
 desligarBuzina();
 digitalWrite(ledAmarelo,LOW);
}

if(distancia_cm<=10.0){
  Serial.println ("Perigo! Movimento detectado muito perto!");
  digitalWrite(ledVermelho, HIGH);
  delay(5000);
  tone(buzina,600);
  delay (5000);
  desligarBuzina();
  digitalWrite(ledVermelho, LOW);
  }

  }


void CondicoesFumaca();
  
   
  
    
 
}
