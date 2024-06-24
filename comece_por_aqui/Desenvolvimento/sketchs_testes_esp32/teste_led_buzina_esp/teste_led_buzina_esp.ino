#include <Tone32.h>


int LED = 13;
int buzina = 15;
int canal = 1;
int frequencia = 600; 
int duracao = 100;

//void tone(uint8_t pin, unsigned int frequency, unsigned long duration = 0, uint8_t channel = TONE_CHANNEL);
//void noTone(uint8_t pin, uint8_t channel = TONE_CHANNEL);


void setup()
  {
    pinMode(LED, OUTPUT);
    pinMode(buzina,OUTPUT);
    Serial.begin(115200);
    Serial.println("Iniciei.,,,");
  }


  void loop()
  
    {
     Serial.println("Led e buzina ligados");
     digitalWrite(LED, HIGH);
     tone(buzina, frequencia, duracao, canal);
     delay(500);
     Serial.println("Led e buzina desligados");
     digitalWrite(LED, LOW);
     noTone(buzina, canal);
     delay(500);
      
    }
