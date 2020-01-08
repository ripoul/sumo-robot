#define enB 3
#define in3 4
#define in4 7

void setup() {
  pinMode(enB, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
  digitalWrite(in3, LOW);
  digitalWrite(in4, HIGH);
}

void loop() {
  digitalWrite(in3, LOW);
  digitalWrite(in4, HIGH);
  analogWrite(enB, 200);
  delay(500);
  digitalWrite(in3, HIGH);
  digitalWrite(in4, LOW);
  delay(500);
}
