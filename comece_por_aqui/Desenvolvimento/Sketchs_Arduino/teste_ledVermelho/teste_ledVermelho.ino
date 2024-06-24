
// Montagem do led: Na parte esquerda do led coloquei um resistor de 150 ohm; a perna dourada do resistor
// ficou abaixo da perna esquerda do led.Na parte marrom do resistor liguei um jumper direto pro gnd.
// Na perna direita do led (positiva), liguei direto no pino 8.
// Resistor usado: 150 ohm (marrom, verde, marrom, dourado)

int ledVermelho = 8; 

void setup() {
   pinMode (ledVermelho, OUTPUT);
 Serial.begin(9600);
 pinMode (ledVermelho, LOW);
 Serial.println("Iniciei....");
 delay(1000);
}

void loop() {
    Serial.println("Led vermelho vai ser aceso");
  digitalWrite(ledVermelho,HIGH);
   delay(500);
  Serial.println("Led vermelho apagado");
  digitalWrite(ledVermelho,LOW);
  delay(500);
    
  
  
}
