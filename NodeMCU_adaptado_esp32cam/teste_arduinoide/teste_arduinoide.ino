// Teste do NodeMCUAdaptado, canal: Duds Tecnologia, vídeo: NodeMCU WebServer(Parte 2). Link: youtube.com/watch?v=psqv5z3kYW8&list=PLssIKrX2yyQFRHDZwi2LZu6fR6VhhCrl&index=2

// Led verde no GPIO14, led amarelo no GPIO13, led vermelho no GPIO12, ldr no GPIO2.
// IP do servidor no vídeo: 192.168.0.36

IPAddress ip(192,168,10, 150);
IPAddress gateway(192,168,1, 1);
IPAddress subnet(255,255,255, 0);
IPAddress dns(207,77,112,48);

/*192.168.1.1
*/
//#include<ESP8266.Wifi>
#include <Wifi.h>
#include <WifiClient.h>

#define  ldr 2
#define led1 14
#define led2 12
#define led3 13

const char* ssid     = "Lunara";   //WIFI SSID
const char* password = "d9-48Z11";   //WIFI password
WifiServer server(80);
void setup() {
  Serial.begin(9600);
  delay(10);
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  pinMode(ldr, INPUT);
  digitalWrite(led1, 0);
  digitalWrite(led2, 0);
  digitalWrite(led3, 0);

  Serial.println();
  Serial.println();
  Serial.print("Conectando com...");
  Serial.println(ssid);

  Wifi.config(ip,dns,gateway,subnet); // para deixar mais rápida a conexão....
  Wifi.begin(ssid, password);

  while (Wifi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");

  }

  Serial.print("");
  Serial.println("Wifi conectado");

  server.begin();
  Serial.print("Servidor iniciado em...");

  Serial.println(Wifi.localIP());
}




void loop() {
  WifiClient client = erver.available();

  if (!client) {
    return;
  }

  Serial.println("Cliente encontrado");
  while (!client.available()) {
    delay(1);
  }

  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush();
  /*
    int val;

    if (req.indexOf("/gpio/0") != -1)
      val = 0;
    else if (request.indexOf * ("/gpio/1") != -1)
      val = 1;

    else {
    serial.println("invalid request");
    client.stop();
    return;
    }

    digitalWrite(14,val);
    client.flush();*/

  if (request.indexOf("led1") != -1) {
    digitalWrite(led1, !digitalRead(led1));
  }

  if (request.indexOf("led2") != -1) {
    digitalWrite(led2, !digitalRead(led2));
  }

  if (request.indexOf("led3") != -1) {
    digitalWrite(led3, !digitalRead(led3));
  }


  if (request.indexOf("todos") != -1) {
    digitalWrite(led1, LOW);
    digitalWrite(led2, LOW);
    digitalWrite(led3, LOW);
  }

  int luminosidade = analogRead(ldr);

  client.println("HTTP/1.1 200 OK");
  client.println("\nContent-Type: txt/html");
  client.println("\r\n\r\n<!DOCTYPE HTML>\r");
  client.println("");

  if (digitalRead(led1)) {
    client.print("l1on");
  } else {
    client.println("l1of");
  }
  client.print(",");


  if (digitalRead(led2)) {
    client.print("l2on");
  } else {
    client.println("l2of");
  }
  client.print(",");

  if (digitalRead(led3)) {
    client.print("l3on");
  } else {
    client.println("l3of");
  }
  client.print(",");

  client.print(luminosidade);

  // client.print(s);
  delay(1);

}
