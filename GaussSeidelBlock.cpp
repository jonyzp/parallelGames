#include <cstdlib>
#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>

using namespace std;

void blockMatriz(){
  ifstream fiEntrada;
  string frase;
  string salida;
  string matrix;
  int iterations;
  double tol=0;
  int finVal=0;
  int pos=0;
  int cont4=0;
  double value1=0;
  double value2=0;
  int n=0;

  matrix = "./MatricesGeneradas/matrix1000.txt" ;
  salida = "solution.txt";
  tol=0.000001;
  iterations=10000;   
  time_t start = time(0); 
  fiEntrada.open (matrix.c_str());
  getline(fiEntrada, frase);
  fiEntrada.close();

  for (int i = 0; i < frase.length(); i++){
    if (frase[i]==' ') n++;
  }
  double xn[n];
  double xn2[n];
  for (int i = 0; i < n; i++) {
    xn[i]=1;
    xn2[i]=1;
  }
  int val=frase.length();
  double values[val];
  for (int i = 0; i < val; i++) {
    values[i]=0;
  }

double error=tol+100000;
while (error>tol&&cont4<iterations) {
  int sum=0;
  int x=0;
  int p=0;
  int y=0;
  int cont=0;
  int cont2=0;
  int cont3=0;
 fiEntrada.open (matrix.c_str());
  while (!fiEntrada.eof()&&cont3<n) {
    string xmean;
    double xm=0;
    double coef=0;
    string coeficiente;
    value1=0;
    value2=0;
    int m=0;
    int media=0;
    pos=0;


    getline(fiEntrada, frase);

    for (int i = 0; i < frase.length(); i++){
        if (sum>0) {
          if (frase[i]==' ') p++;
          if (frase[i]==' '&&p==sum&&sum<n){
              cont2=i+1;
            }
        }
    }
    for (int i = 0; i < frase.length(); i++){
      if (frase[i]==' ') m++;
      if (frase[i]==' '&&m==(n/2)){
        media=i+1;
      }
      if (frase[i]==' '&&m==n){
        finVal=i;
        for (int j = i+1; j < frase.length(); j++) {
          coeficiente+=frase[j];
        }
        coef=atof(coeficiente.c_str());
      }
    }
    for (int i = 0; i < media; i++) {
      string str1="";
      if (pos==sum) {
        pos++;
      }

      if (cont2!=i) {
        x=i;

        while (frase[x]!=' ') {
          str1+=frase[x];
          x++;
        }
        value1+=xn[pos]*atof(str1.c_str());

        i=x;
        pos++;
      }else{
        x=i;
        while (frase[x]!=' ') {
          xmean+=frase[x];
          x++;
        }
        xm=atof(xmean.c_str());
        i=x;
      }
    }

    for (int i = media; i < finVal; i++) {
      string str2="";
      if (pos==sum) {
        pos++;
      }
      if (cont2!=i) {
        y=i;
        while (frase[y]!=' ') {
          str2+=frase[y];
          y++;
        }
        i=y;
        value2+=xn[pos]*atof(str2.c_str());
          pos++;


      }else{
        y=i;
        while (frase[y]!=' ') {
          xmean+=frase[y];
          y++;
        }
        xm=atof(xmean.c_str());
        i=y;
      }
    }
    xn2[cont3]=xn[cont3];
    xn[cont3]=(coef-(value1+value2))/xm;
    cont3++;
    p=0;
    sum++;
  }
  cont4++;
  fiEntrada.close();
  for (int i = 0; i < n; i++) {
    if ((abs (xn[i]-xn2[i]))<error) {
      error=abs(xn[i]-xn2[i]);
    }
  }

}
 ofstream write;
 write.open(salida.c_str(),ios::trunc);
 if (cont4<iterations) {
   for (int i = 0; i < n; i++) {
     write <<xn[i]<< '\n';
   }
 }else{
   cout << "fallo por numero de iteraciones" << '\n';
 }
  write.close();

}

int main(int argc, char const *argv[]) {
  blockMatriz();
  return 0;
}
