// Tutorial do sensor de estacionamento: https://www.youtube.com/watch?v=mMWcB-vFYZs  a perna chanfrada é a perna maior do led.
int buzina = 2;
int ledAmarelo = 3; 
int ledVermelho = 4;
int trig = 8;
int eccho = 9;
float distancia_cm;
float duracao;


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
 void desligarBuzinaMedio(){
  Serial.println("Buzina desligada");
  noTone(buzina);
  delay(2000);
  }
  void desligarBuzinaGrave(){
  Serial.println("Buzina desligada");
  noTone(buzina);
  delay(5000);
  }

void loop() {
  
  leituraUltrassom();
  if(distancia_cm >10.0){
 Serial.println("Movimento detectado em medio alcance!");
 digitalWrite(ledAmarelo, HIGH);
 delay(4000);
 tone(buzina, 900);
 delay(5000);
 desligarBuzinaMedio();
 digitalWrite(ledAmarelo,LOW);
}

if(distancia_cm<=10.0){
  Serial.println ("Perigo! Movimento detectado muito perto!");
  digitalWrite(ledVermelho, HIGH);
  delay(4000);
  tone(buzina,200);
  delay (5000);
  desligarBuzinaGrave();
  digitalWrite(ledVermelho, LOW);
  }

  }
   
  
    
    
  
