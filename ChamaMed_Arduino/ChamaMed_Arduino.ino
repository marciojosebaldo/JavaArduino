int porta = 2;
int led = 13;  // Pino exclusivo para o LED

void setup() {
  Serial.begin(9600);

  pinMode(led, OUTPUT);
  digitalWrite(led, LOW);

  pinMode(porta, INPUT);
}

void loop() {
    if (digitalRead(porta) == HIGH) {
      int leituraPorta = 1;
      Serial.write(leituraPorta);
      digitalWrite(led, HIGH);
    } else {
      int leituraPorta = 0;
      Serial.write(leituraPorta);
      digitalWrite(led, LOW);
    }

    if(!Serial.available()){
      return;
    }
}