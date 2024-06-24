#include <Wire.h>

//Fonte:  https://www.youtube.com/watch?v=vkiDhCImEtk&t=378s

  int LED = 13;
  int canal = 0;
  int resolucao = 8; // 8 bits
  int dutyCycle = 128; // 8 bits é 256 pra 100% do brilho
  int freq = 1000; // 1000 Hz
  int i;


void setup()
  {
    pinMode(LED, OUTPUT);
    ledcSetup(canal, freq, resolucao);
    ledcAttachPin(LED,canal);
    //ledcWrite(canal, dutyCycle);
  }


  void loop()
     {
   for(i = 0; i< 256; i++)
         {
    ledcWrite(canal,i);
    delay(10);
         }

         for(i = 255; i> 0; i--)
           {
         ledcWrite(canal,i);
         delay(2);
           }
       
     }
