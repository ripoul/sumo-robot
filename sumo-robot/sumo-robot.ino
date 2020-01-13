//moteur 2
#define enB 3
#define in3 4
#define in4 7

//moteur 1
#define enA 10
#define in1 12
#define in2 13

byte serialA;

void setup() {
  Serial.begin(9600);
  //moteur 2 - sens 1
  pinMode(enB, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
  digitalWrite(in3, LOW);
  digitalWrite(in4, HIGH);

  //moteur 1 - sens 1
  pinMode(enA, OUTPUT);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  digitalWrite(in1, LOW);
  digitalWrite(in2, HIGH);
  analogWrite(enA, 0);
  analogWrite(enB, 0);
}

void loop() {
  
  if (Serial.available() > 0) {serialA = Serial.read();Serial.println(serialA);}
  digitalWrite(in3, LOW);
  digitalWrite(in4, HIGH);
  digitalWrite(in1, LOW);
  digitalWrite(in2, HIGH);
  delay(500);
  digitalWrite(in3, HIGH);
  digitalWrite(in4, LOW);
  digitalWrite(in1, HIGH);
  digitalWrite(in2, LOW);
  delay(500);

  
}
