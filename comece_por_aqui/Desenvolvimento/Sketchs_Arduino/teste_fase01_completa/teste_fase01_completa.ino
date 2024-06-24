// Tutorial do sensor de estacionamento: https://www.youtube.com/watch?v=mMWcB-vFYZs  a perna chanfrada é a perna maior do led.

#define buzina  2
#define ledAmarelo 3
#define ledVermelho 4
#define trig  8
#define eccho 9
#define sensorPir A0
float distancia_cm;
float duracao;
float leituraPir;


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
  Serial.begin(9600);
  Serial.println("Iniciei...");

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
 
void loop() {
  
leituraUltrassom();
  leituraSensorPresenca();
  
  if (leituraPir>0){
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
   
  
    
 
}
