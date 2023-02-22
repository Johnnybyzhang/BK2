#include <WiFi.h>
#include <HTTPClient.h>

const char *ssid = "DKU";
const char *password = "Duk3blu3!";
const int buttonPin = 0;
const int ledPin = 2;

String serverName = "http://10.200.30.38/test";

int modeState = 0;
int buttonState = 0;
int lastButtonState = 0;
int lastModeState = 0;
bool httpState = true;

void setup()
{
  Serial.begin(115200);

  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
  pinMode(buttonPin, INPUT);
  pinMode(ledPin, OUTPUT);
}
void http(){
  if (WiFi.status() == WL_CONNECTED)
  {
    Serial.println(modeState);
    HTTPClient http;
    String serverPath;
    if (httpState) {
      serverPath = serverName + "?state=false";
    } else {
      serverPath = serverName + "?state=true";
    }
    http.begin(serverPath.c_str());
    int httpResponseCode = http.GET();
    if (httpResponseCode > 0)
    {
      Serial.print("HTTP Response code: ");
      Serial.println(httpResponseCode);
      String payload = http.getString();
      Serial.println(payload);
    }
    else
    {
      Serial.print("Error code: ");
      Serial.println(httpResponseCode);
    }
    http.end();
  }
  else
  {
    Serial.println("WiFi Disconnected");
  }
}
void httpHelper(){
  if (lastModeState == modeState) {
    return;
  }
  httpState = modeState;
  lastModeState = modeState;
  http();
}
void loop()
{
  buttonState = digitalRead(buttonPin);
  if (buttonState == LOW && lastButtonState == HIGH){
    modeState = 1- modeState;
    delay(20);
  }
  lastButtonState = buttonState;
  if (modeState == 1){
    digitalWrite(ledPin, HIGH);
  } else {
    digitalWrite(ledPin, LOW);
  }
  httpHelper();
}
