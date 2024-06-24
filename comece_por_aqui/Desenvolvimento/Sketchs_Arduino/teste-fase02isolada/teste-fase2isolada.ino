int redLed = 12; 
int greenLed = 11; 
int buzzer = 10;
int smokeA0 = A5;

 
// Your threshold value int sensorThres = 400;
void setup() {
pinMode(redLed, OUTPUT); 
pinMode(greenLed, OUTPUT);
pinMode(buzzer, OUTPUT); 
pinMode(smokeA0, INPUT); 
Serial.begin(9600);
}
void loop() {
int analogSensor = analogRead(smokeA0);
Serial.print("Pin A0: ");
Serial.println(analogSensor);
Serial.println("--------------");
delay(5000);
// Checks if it has reached the threshold value if (analogSensor > sensorThres)
if (analogSensor>300){

{
digitalWrite(redLed, HIGH); 
digitalWrite(greenLed, LOW); 
tone(buzzer, 1000, 200);
delay(1000); 
noTone(buzzer);             
}
}

else {
digitalWrite(redLed, LOW); 
digitalWrite(greenLed, HIGH); 
noTone(buzzer);
delay(1000);
}
delay(5000);
}
