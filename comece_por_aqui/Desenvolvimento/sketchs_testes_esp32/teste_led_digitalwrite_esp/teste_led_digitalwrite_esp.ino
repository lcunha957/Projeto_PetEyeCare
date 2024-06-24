
  int LED = 13;

void setup() {
  // put your setup code here, to run once:
 Serial.begin(115200);
 pinMode(LED, OUTPUT);
 Serial.println("Acordei....");
  }

void loop()
     {
  // put your main code here, to run repeatedly:

  Serial.println("Led ligado...");
  digitalWrite(LED, HIGH);
  delay(5000);
  Serial.println("Led desligado...");
  digitalWrite(LED, LOW);
  delay(5000);
  Serial.println("--------Fim------------");
     }
